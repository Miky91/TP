package tp.pr5.commands;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.Door;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.Terna;
import tp.pr5.commands.exceptions.*;
import tp.pr5.console.Console;
import tp.pr5.gui.InfoPanel;
import tp.pr5.gui.MapPanel;
import tp.pr5.gui.PlayerPanel;

/**
 * Su ejecución mueve al jugador de una habitación a la siguiente en una
 * dirección. Este comando funciona si el usuario escribe GO | IR {NORTH | EAST
 * | SOUTH | WEST}
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero
 * 
 */

public class GoCommand implements Command {
	private Game game;
	private String direction;
	private MapPanel mapPanel;
	private PlayerPanel playerPanel;
	private Map map;
	private Player player;
	private Console console;
	private InfoPanel infoPanel;

	/**
	 * Constructor por defecto
	 */
	public GoCommand() {
		this.direction = null;

	}

	/**
	 * Constructor de un GoCommand valido.
	 * 
	 * @param player
	 * @param currentRoom
	 * @param item
	 */
	public GoCommand(String words, String words2, Game game, MapPanel mapPanel,
			PlayerPanel playerPanel) {
		this.direction = words2;
		this.game = game;
		this.mapPanel = mapPanel;
		this.setPlayerPanel(playerPanel);
		this.console = new Console();
		this.setInfoPanel(new InfoPanel(this.game));
	}

	/**
	 * Constructor de un GoCommand valido.
	 * 
	 * @param player
	 * @param currentRoom
	 * @param item
	 */
	public GoCommand(String words, String words2, Game game) {
		this.direction = words2;
		this.game = game;
		this.mapPanel = null;
		this.console = new Console();
	

	}

	/*
	 * Inicio de Getters & Setters
	 */
	public Map getMap() {
		return map;
	}

	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}

	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Game getGame() {
		return game;
	}

	/*
	 * Fin de Getters & Setters
	 */

	/*
	 * Inicio de los métodos públicos
	 */
	/**
	 * Establece el contexto de ejecución
	 */
	public void configureContext(Game g, Map m, Player p) {
		this.game = g;
		this.setMap(m);
		this.setPlayer(p);
	}

	/**
	 * Mueve al jugador de la habitación a la siguiente habitación en la
	 * direccion dadad
	 */
	public void execute() throws CommandExecutionException {
		Directions direction = stringToDirection(this.direction);
		boolean valido;
		if (this.direction.equalsIgnoreCase(Directions.UNKNOWN.toString()))
			valido = false;
		else
			valido = true;
		if (valido) {//if ((this.map.getDoor(this.game.getCurrentRoom(), direction) != null)&&this.map.getDoor(this.game.getCurrentRoom(), direction).isOpen()){
			this.game.getCurrentMap().setPreviousRoom(this.game.getCurrentRoom());
			this.game.getCurrentMap().setCurrentRoom((changeRoom(direction)));
			this.game.getCurrentRoom().setVisited(true);
		//}
			if (this.game.getCurrentMap().getCurrentRoom().isExit()) {
				this.game.requestGameOver();
				this.game.requestQuit();
				this.player.requestPlayerUpdate();
				this.game.setSalida(true);
			} else if (this.game.getPlayer().dead()) {
				this.game.requestGameOver();
			} else {
				this.console.playerUpdate(this.game.getJug().getHealth(),
						this.game.getJug().getPoints());
				System.out.print("> ");
			}
		} else
			throw new CommandExecutionException();
	}

	/**
	 * Devuelve la descripción de la sintaxis del comando.
	 */
	public String getHelp() {
		String ayuda;
		ayuda = Constants.AYUDAGO;
		return ayuda;
	}

	/**
	 * La ejecución intenta mover al jugadro de una habitación a otra
	 */


	/**
	 * Parsea la cadena devolviendo un GoCommand o lanzando una excepción.
	 */

	public Command parse(String cad)
			throws WrongCommandFormatException {
		int i = 0;
		boolean encontrado = false;
		String words[] = cad.split(" ");

		if (words.length < 2 || words.length > 2) {
			throw new WrongCommandFormatException();
		} else if (words[0].equalsIgnoreCase("GO")
				|| words[0].equalsIgnoreCase("ir")) {
			new GoCommand(words[0], words[1], this.game);
			Directions[] buenadireccion = Directions.values();
			while ((i < buenadireccion.length) && !encontrado) {
				if (buenadireccion[i].toString().equalsIgnoreCase(words[1])) {
					encontrado = true;
				} else
					i++;
			}
			if (!encontrado)
				throw new WrongCommandFormatException();
			else
				return new GoCommand(words[0], words[1], this.game);
		} else
			throw new WrongCommandFormatException();

	}

	/**
	 * Deshace el ultimo movimiento hecho
	 */
	public void undoExecute(Terna undo) throws CommandExecutionException {
		this.game.getCurrentMap().setPreviousRoom(sourceRoom(this.game.getCurrentRoom()));
		this.game.setCurrentRoom(this.game.getCurrentMap().getPreviousRoom());
		
		this.game.getPlayer().addHealth(Constants.VIDAS_PUERTA);
		if (this.mapPanel != null){
			Directions direction = stringToDirection(this.direction);
			int auxI;
			int auxJ;
			this.game.getCurrentMap().getMapPanel()
					.setNewRoom(direction.getOposite());
			auxI = this.game.getCurrentMap().getMapPanel().getI();
			auxJ = this.game.getCurrentMap().getMapPanel().getJ();
			this.game.setCurrentRoom(this.game.getCurrentMap().getPreviousRoom());
			this.game
					.getCurrentMap()
					.getMapPanel()
					.setMapa(this.mapPanel.getMapa()[auxI][auxJ],
							this.game.getCurrentRoom());
			this.game.getCurrentMap().getMapPanel().setNewRoom(direction);
			auxI = this.game.getCurrentMap().getMapPanel().getI();
			auxJ = this.game.getCurrentMap().getMapPanel().getJ();
			this.game.getCurrentMap().getMapPanel()
					.eliminateCell(this.mapPanel.getMapa()[auxI][auxJ]);
			this.game.getCurrentMap().getMapPanel()
					.setNewRoom(direction.getOposite());
			auxI = this.game.getCurrentMap().getMapPanel().getI();
			auxJ = this.game.getCurrentMap().getMapPanel().getJ();
			}

		//}
			
	}

	/**
	 * Deshace el ultimo movimiento hecho
	 */
	
	/*
	 * Fin de los métodos públicos
	 */
	/*
	 * Inicio de los metodos privados
	 */
	/**
	 * Devuelve el enumerado de direccion correspondiente a una cadena
	 * 
	 * @param cad
	 * @return
	 */
	private Directions stringToDirection(String cad) {
		Directions[] direc = Directions.values();
		int i = 0;
		while (i < direc.length) {
			if (cad.equalsIgnoreCase(direc[i].toString()))
				return direc[i];
			else
				i++;
		}
		return null;
	}
	/**
	 * Se encarga de hacer el cambio de habitacion.
	 * @param direction
	 * @return
	 * @throws CommandExecutionException
	 */
	protected Room changeRoom(Directions direction)
			throws CommandExecutionException {
		Door puerta = this.game.getCurrentMap().getDoor(
				this.game.getCurrentRoom(), direction);
		if (puerta != null) {
			if (puerta.isOpen()) {
				if (puerta.getBidirectional()
						&& direction == puerta.getDirection().getOposite()) {
					this.game.setCurrentRoom(puerta.getSource());
					this.game.getPlayer().addHealth(-Constants.VIDAS_PUERTA);
					this.map.enter();
				} else {
					if (direction == puerta.getDirection()) {
						this.game.setCurrentRoom(puerta.getTarget());
						this.game.getMap().setPreviousRoom(puerta.getSource());
						this.game.getMap().setCurrentRoom(puerta.getTarget());
						this.game.getPlayer()
								.addHealth(-Constants.VIDAS_PUERTA);
						this.map.enter();
					} else {
						throw new CommandExecutionException(
								"Impossible to go through the door from this side.\n> ");
					}

				}
			} else {

				throw new CommandExecutionException("There is a door in the "
						+ direction.name() + ", but it is closed.\n> ");
			}
		} else {

			throw new CommandExecutionException(
					"What the hell am I supposed to do going to "
							+ direction.name() + " ?\n> ");
		}
		return this.game.getCurrentRoom();
	}
	/*
	 * Fin de métodos privados.
	 */

	public InfoPanel getInfoPanel() {
		return infoPanel;
	}

	public void setInfoPanel(InfoPanel infoPanel) {
		this.infoPanel = infoPanel;
	}
	
	private Room sourceRoom(Room hab){
		for(int i = 0; i < this.game.getMap().getMapaPuertas().size(); i++)
		if(this.game.getMap().getMapaPuertas().get(i).getTarget() == hab)
			return this.game.getMap().getMapaPuertas().get(i).getSource();
		return null;
	}
}
