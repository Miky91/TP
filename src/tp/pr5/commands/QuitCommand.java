package tp.pr5.commands;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Terna;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

/**
 * Su ejecuci�n pide al bucle del juego que termine. Este comando funciona si el
 * usuario escribe QUIT | SALIR
 * 
 * @author Ivan Mart�n Herrero & Miguel de Andr�s Herrero
 * 
 */
public class QuitCommand implements Command {

	private Game game;
	private Map map;
	private Player player;

	/**
	 * Constructor por defecto
	 */
	public QuitCommand() {
		this.game = null;
	}

	/**
	 * Constructor de un QuitCommand valido
	 * @param verbo
	 * @param game
	 */
	public QuitCommand(String verbo, Game game) {
		this.game = game;
	}

	/*
	 * Getters & Setters
	 */
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	/*
	 * M�todos p�blicos
	 */
	/**
	 * Establece el contexto de ejecuci�n
	 */
	public void configureContext(Game g, Map m, Player p) {
		this.game = g;
		this.setMap(m);
		this.setPlayer(p);
	}

	/**
	 * Pide al bucle principal salir.
	 */
	public void execute() throws CommandExecutionException {
		this.game.requestQuit();

	}

	/**
	 * Devuelve la sintaxis del comando
	 */
	public String getHelp() {

		return Constants.AYUDAQUIT;
	}

	/**
	 * Parsea la cadena y devuelve un QuitCommand o una excepci�n
	 */
	public Command parse(String cad)
			throws WrongCommandFormatException {
		String words[] = cad.split(" ");
		if (words.length != 1) {
			throw new WrongCommandFormatException();
		} else if (words[0].toUpperCase().equalsIgnoreCase("Quit")
				|| words[0].toUpperCase().equalsIgnoreCase("SAlir"))
			return new QuitCommand(words[0], this.game);
		else
			throw new WrongCommandFormatException();
	}

	public void undoExecute(Terna undo) throws CommandExecutionException {

	}


}
