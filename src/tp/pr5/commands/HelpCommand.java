package tp.pr5.commands;

import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Terna;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.console.*;

/**
 * Muestra la ayuda del juego con todos los comandos que el usuario puede
 * ejecutar. Este comando funciona si el usuario escribe HELP | AYUDA
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero
 * 
 */
public class HelpCommand implements Command {

	private String verbo;
	private Game game;
	private Map map;
	private Player player;
	private Console console;

	/**
	 * Constructor por defecto
	 */
	public HelpCommand() {

	}

	/**
	 * Constructor de un HelpCommand valido.
	 * 
	 * @param player
	 * @param currentRoom
	 * @param item
	 */
	public HelpCommand(String cadena) {
		this.verbo = cadena;
	}

	/*
	 * Inicio de Getters & Setters
	 */
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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
	 * Muestra la ayuda del juego
	 */
	public void execute() throws CommandExecutionException {
		if (this.verbo.equalsIgnoreCase("HELP")
				|| this.verbo.equalsIgnoreCase("AYUDA")) {
			this.game.requestHelp();
		}

		else
			throw new CommandExecutionException();
	}

	/**
	 * Devuelve la descripción de la sintaxis del comando.
	 */
	public String getHelp() {
		return ("HELP|AYUDA ");
	}

	/**
	 * Parsea la cadena devolviendo un HelpCommand o lanzando una excepción.
	 */

	public Command parse(String cad)
			throws WrongCommandFormatException {
		String words[] = cad.split(" ");
		if (words.length != 1) {
			throw new WrongCommandFormatException();
		} else if (words[0].toUpperCase().equalsIgnoreCase("HELP")
				|| words[0].toUpperCase().equalsIgnoreCase("AYUDA"))
			return new HelpCommand(cad);
		else
			throw new WrongCommandFormatException();
	}

	/**
	 * Deshace el ultimo movimiento hecho
	 */
	public void undoExecute(Terna undo) throws CommandExecutionException {
		this.game = undo.getJuegoanterior();
		this.game.getPila().desapila();
		if (!this.game.getPila().PilaVacia())
			this.game.getPila().desapila();
	}

}
