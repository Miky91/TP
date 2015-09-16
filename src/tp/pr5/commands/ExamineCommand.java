package tp.pr5.commands;

import java.util.StringTokenizer;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.Terna;
import tp.pr5.commands.Command;
import tp.pr5.commands.exceptions.*;

/**
 * Este comando muestra la descripción de la habitación y de los objetos que hay
 * en ella. Este comando funciona si el jugador escribe EXAMINE|EXAMINAR.
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero
 * 
 */
public class ExamineCommand implements Command {
	Game game;
	Player player;
	Room currentRoom;
	StringTokenizer parser;
	Command command;
	private Map map;

	/**
	 * Constructor por defecto
	 */
	public ExamineCommand() {
		player = null;
		currentRoom = null;
		command = null;
	}


	/*
	 * Inicio de Getters & Setters
	 */

	public Game getGame() {
		return game;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
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
		this.player = p;
	}
	/**
	 * Muestra la descripcion de la habitación.
	 */
	public void execute() {

		System.out.println(this.game.getCurrentRoom().getDescription());
		System.out.print("> ");
	}

		
	/**
	 * Devuelve la descripción de la sintaxis del comando.
	 */
	public String getHelp() {
		String ayuda;
		ayuda = Constants.AYUDAEXAMINE;
		return ayuda;
	}
	/**
	 * Parsea la cadena devolviendo un ExamineCommand o lanzando una excepción.
	 */
	public Command parse(String cad/*, Game exeContext*/)
			throws WrongCommandFormatException {

		String words[] = cad.split(" ");
		if (words.length != 1) {
			throw new WrongCommandFormatException();
		} else if (words[0].equalsIgnoreCase("EXAMINE")
				|| words[0].equalsIgnoreCase("examinar")) {

			return new ExamineCommand();
		} else
			throw new WrongCommandFormatException();
	}
	/**
	 * Deshace la acción ExamineCommand anterior.
	 */
	public void undoExecute(Terna undo) throws CommandExecutionException {
		this.game = undo.getJuegoanterior();
		this.game.getPila().desapila();
		if (!this.game.getPila().PilaVacia())
			this.game.getPila().desapila();
	}

	
	
	
	
	


}
