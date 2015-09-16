package tp.pr5.console;

import java.util.ArrayList;
import java.util.Scanner;
import tp.pr5.Game;
import tp.pr5.GameController;
import tp.pr5.GameObserver;
import tp.pr5.Map;
import tp.pr5.MapObserver;
import tp.pr5.Parser;
import tp.pr5.PilaGames;
import tp.pr5.Player;
import tp.pr5.PlayerObserver;
import tp.pr5.Room;
import tp.pr5.Terna;
import tp.pr5.commands.Command;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.items.Item;

/**
 * El controlador utiliza cuando la aplicación está configurada como una
 * aplicación de consola. Contiene el bucle de juego que se ejecuta en el juego
 * los comandos escritos por el usuario en la consola ..
 * 
 * @author Ivan Martin Herrero & Miguel de Andrés Herrero
 * 
 */
public class GameControllerConsole extends GameController {

	private boolean salida;
	private Player jug;
	private PilaGames pila;
	private ArrayList <Item> objUsados = new ArrayList<Item>();
	private Map map;
	private Room currentRoom;
	private GameObserver gameObserver;
	private MapObserver mapObserver;
	private PlayerObserver playerObserver;
	private int ejecucion;

	/**
	 * Constructor por defecto
	 * @param game
	 */
	public GameControllerConsole(Game game) {
		super(game);
		this.jug = game.getJug();
		this.map = game.getMap();
		this.pila = game.getPila();
		this.objUsados = game.getObjUsados();
		this.gameObserver = new Console();
		this.mapObserver =new Console();
		this.playerObserver = new Console();
		this.registerGameObserver(this.gameObserver);
		this.registerMapObserver(this.mapObserver);
		this.registerPlayerObserver(this.playerObserver);
		this.ejecucion = 1;

	}
	
	/*
	 * Inicio de Getters & Setters
	 */
	public boolean isSalida() {
		return salida;
	}

	public void setSalida(boolean salida) {
		this.salida = salida;
	}
	public void setCurrentRoom(Room currentRoom) {

		this.currentRoom = currentRoom;

	}

	public ArrayList<Item> getObjUsados() {
		return this.objUsados;
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

	public int getEjecucion() {
		return ejecucion;
	}

	public void setEjecucion(int ejecucion) {
		this.ejecucion = ejecucion;
	}

	/*
	 * Fin de Getters & Setters
	 */
	/**
	 * Esta función implementa el bucle principal del juego
	 */
	public void runGame() {
		
		try {
			this.game.requestStart();
			Scanner sc = new Scanner(System.in);
			while (!this.jug.dead()&& !this.game.isOver()) {
				String line = sc.nextLine();

				try {
					Command auxCommand = Parser.parseCommand(line);
					if ((!line.toUpperCase().equalsIgnoreCase("HELP"))
							&& (!line.toUpperCase().equalsIgnoreCase("EXAMINE"))
							&& (!line.toUpperCase().equalsIgnoreCase("UNDO") && (!line
									.toUpperCase().equalsIgnoreCase("LOOK")))) {
						setEjecucion(getEjecucion() + 1);
					} else if (line.equalsIgnoreCase("UNDO"))
						setEjecucion(getEjecucion() - 1);
					this.game.executeCommand(auxCommand);
					if ((!line.equalsIgnoreCase("HELP"))
							&& (!line.equalsIgnoreCase("EXAMINE"))
							&& (!line.equalsIgnoreCase("UNDO") && (!line
									.equalsIgnoreCase("LOOK")))) {
						Terna terna = new Terna(auxCommand, line, this.game);
						this.pila.apila(terna);
					}

				} catch (WrongCommandFormatException e) {
					System.out.print(e.getMessage());
				}

			}
		} catch (Exception e) {e.printStackTrace();
		}
		;
	}

	

}
