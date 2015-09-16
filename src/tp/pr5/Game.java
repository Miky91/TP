package tp.pr5;

import java.util.ArrayList;
import tp.pr5.commands.*;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.items.Item;
import tp.pr5.pila.exception.ExceptionPila;

/**
 * Esta clase ejecuta los comandos de la aventura interactiva. El juego se puede
 * jugar con la consola o con una interfaz Swing. El primero utiliza un bucle de
 * juego implementado por runGame con el fin de ejecutar los comandos, mientras
 * que el segundo emplea las interacciones del usuario en la ventana abatible
 * para decidir qué comandos se ejecutará. Se termina cuando el usuario llega a
 * una sala de salida o el jugador está muerto. Los controladores son
 * responsables de hacer saber si el juego ha terminado.
 * 
 * El juego se encarga de registrar y eliminar los observadores del
 * juego, los observadores del mapa y los observadores del jugador. El juego
 * consigue una lista de GameObservers con el fin de informarle sobre los
 * eventos del juego.
 * 
 * Más información:
 * 
 * Las habitaciones no se crean en esta categoría, pero se inician en el mapa.
 * Si el juego se inicia con un mapa nulo la ejecución termina inmediatamente.
 * Un objeto del juego contiene el jugador que está jugando.
 * 
 * @author Ivan Martin Herrero & Miguel de Andrés Herrero
 * 
 */
public class Game extends tp.pr5.Observable<GameObserver> {

	private Map map;
	private Room currentRoom;
	private Player jug;
	private boolean salida;
	private PilaGames pila;
	private Game movAnterior;
	private ArrayList<Item> objUsados = new ArrayList<Item>();
	private int ejecucion;
	private String mode;

	/**
	 * Constructora de map por defecto
	 * 
	 * @param map
	 *            : variable donde guardaremos el mapa
	 * @param currentRoom
	 *            : variable donde guardamos la habitacion actual
	 * @param jug
	 *            : variable donde están almacenados los datos del jugador
	 * @param salida
	 *            : booleano que indica si currenRoom es la habitacion salida
	 *            del juego
	 */

	public Game(Map map) {

		
			this.pila = new PilaGames();
			this.map = map;
			this.jug = new Player();
			
			this.salida = false;
			this.ejecucion = 1;
			this.mode = "";
			try {
			this.currentRoom = map.getCurrentRoom();
			} catch (NullPointerException e) {
				this.salida = true;
			}
		

	}

	/*
	 * Inicio de Getters & Setters
	 */

	public void setCurrentRoom(Room currentRoom) {

		this.currentRoom = currentRoom;

	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public String getMode() {
		return mode;
	}

	public ArrayList<Item> getObjUsados() {
		return this.objUsados;
	}

	public Item getObject(String id) {
		for (int i = 0; i < this.objUsados.size(); i++) {
			if (this.objUsados.get(i).getId().equalsIgnoreCase(id))
				return this.objUsados.get(i);
		}
		return null;
	}

	public void setSalida(boolean salida) {
		this.salida = salida;
	}

	public void createPila() {
		this.pila = new PilaGames();
	}

	public Room getCurrentRoom() {
		return this.currentRoom;
	}

	public Player getPlayer() {
		return this.jug;

	}

	public Map getCurrentMap() {
		return this.map;

	}

	public PilaGames getPila() {
		return this.pila;
	}

	public Game getMovAnterior() {
		return movAnterior;
	}

	public void setMovAnterior(Game movAnterior) {
		this.movAnterior = movAnterior;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setJug(Player jug) {
		this.jug = jug;
	}

	public int getEjecucion() {
		return ejecucion;
	}

	public Map getMap() {
		return map;
	}

	public Player getJug() {
		return jug;
	}

	public void setPila(PilaGames pila) {
		this.pila = pila;
	}

	public void setObjUsados(ArrayList<Item> objUsados) {
		this.objUsados = objUsados;
	}

	public void setEjecucion(int ejecucion) {
		this.ejecucion = ejecucion;
	}

	/*
	 * Fin de Getters & Setters
	 */

	/*
	 * Inicio de los metodos
	 */

	/**
	 * Registra un GameObserver para el módelo.
	 * 
	 * @param observer
	 */
	public void addGameObserver(GameObserver observer) {
		this.addObserver(observer);
	}

	/**
	 * Registra un MapObserver para el mapa contenido en el modelo
	 * 
	 * @param observer
	 */
	public void addMapObserver(MapObserver observer) {
		this.map.addObserver(observer);
	}

	/**
	 * Registra un PlayerObserver para el jugador contenido en el modelo
	 * 
	 * @param observer
	 */
	public void addPlayerObserver(PlayerObserver observer) {
		this.jug.addObserver(observer);
	}

	/**
	 * Ejecuta un comando e informa a los observadores si el programa a
	 * terminado después de ejecutar el comando c.
	 * 
	 * @param c
	 */
	public void executeCommand(Command c) {
		try {
			c.configureContext(this, this.map, this.jug);
			c.execute();
		} catch (CommandExecutionException e) {
			
				if(!this.getPila().PilaVacia())
					try {
						this.getPila().desapila();
					} catch (ExceptionPila e1) {
						e1.printStackTrace();
					}
			this.requestError(e.getMessage());
		}
	}

	/**
	 * Comprueba si el juego ha finalizado.
	 * 
	 * @return true si el juego ha terminado
	 * @return false si el juego no ha terminado.
	 */
	public boolean isOver() {
		return this.salida;
	}

	/**
	 * Elimina un GameObserver adjunto al modelo.
	 * 
	 * @param observer
	 */
	public void removeGameObserver(GameObserver observer) {
		this.removeGameObserver(observer);
	}

	/**
	 * Elimina un MapObserver adjunto al mapa contenido en el modelo.
	 * 
	 * @param observer
	 */
	public void removeMapObserver(MapObserver observer) {
		this.removeMapObserver(observer);
	}

	/**
	 * Elimina un PlayerObserver adjunto al jugador contenido en el modelo.
	 * 
	 * @param observer
	 */
	public void removePlayerObserver(PlayerObserver observer) {
		this.removePlayerObserver(observer);
	}

	/**
	 * Pide al juego informar a los observadores de que un error ha ocurrido.
	 * 
	 * @param msg
	 */
	public void requestError(java.lang.String msg) {
		for (GameObserver go : observarGame)
			go.gameError(msg);
	}

	/**
	 * Pide al juego informar a los observadores acerca de la información de
	 * ayuda.
	 * 
	 */
	public void requestHelp() {
		for (GameObserver go : observarGame)
			go.gameHelp();
	}

	/**
	 * Pide al juego informar a los observadores que salga del juego.
	 * 
	 */
	public void requestQuit() {
		for (GameObserver go : observarGame)
			go.gameQuit();
	}
	public void requestGameOver() {
		for (GameObserver go : observarGame)
			go.gameOver(!this.jug.dead());
	}
	/**
	 * Pide al juego inforar a los observadors que el juego empieza.
	 * 
	 */
	public void requestStart() {
		for (GameObserver o : observarGame)
			o.gameStart(this.currentRoom, this.getPlayer().getPoints(), this
					.getPlayer().getHealth());
	}



}
