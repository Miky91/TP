package tp.pr5.commands;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Terna;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.pila.exception.ExceptionPila;

/**
 * Deshace el último comando en la pila. Este comando funciona si el usuario
 * escribe UNDO | DESHACER
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero
 * 
 */
public class UndoCommand implements Command {

	private Game juego;
	private String comando;
	private Game game;
	private Map map;
	private Player player;

	/**
	 * Constructor por defecto
	 */
	public UndoCommand() {
		this.juego = null;
		this.comando = "";
	}

	/**
	 * Constructor de un UndoCommand valido
	 * @param comando
	 * @param juego
	 */
	public UndoCommand(String comando, Game juego) {
		this.juego = juego;
		this.comando = comando;

	}

	/*
	 * Getters & Setters
	 */
	public String getLine() {
		return this.comando;
	}

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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	/*
	 * Métodos públicos
	 */
	/**
	 * Establece el contexto de ejecución
	 */
	public void configureContext(Game g, Map m, Player p) {
		this.setGame(g);
		this.setMap(m);
		this.setPlayer(p);
	}

	/**
	 * Ejecuta la acción de deshacer el último comando
	 */
	public void execute() throws CommandExecutionException {
		Terna terna = this.juego.getPila().Cima();
		if (!this.juego.getPila().PilaVacia()) {
			terna.getCommand().undoExecute(terna);
			this.game.getPila().desapila();
		} else
			throw new CommandExecutionException("Imposible deshacer mas");

	}

	/**
	 * Devuelve la sintaxis del comando
	 */
	public String getHelp() {
		return Constants.AYUDAUNDO;
	}

	/**
	 * Ejecución gráfica
	 */
	public void graphicExecute() {
		Terna terna = null;
		try {
			terna = this.juego.getPila().Cima();
		} catch (ExceptionPila e1) {
			e1.printStackTrace();
		}
		if (!this.juego.getPila().PilaVacia()) {
			try {
				terna.getCommand().undoExecute(terna);
				this.juego.getPila().desapila();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Parsea la cadena y devuelve un UndoCommand o una excepción.
	 */
	public Command parse(String cad)
			throws WrongCommandFormatException {

		String words[] = cad.split(" ");
		if (words.length != 1) {
			throw new WrongCommandFormatException();
		} else if (words[0].equalsIgnoreCase("UNDO")
				|| words[0].equalsIgnoreCase("DESHACER"))
			return new UndoCommand(cad, this.game);
		else
			throw new WrongCommandFormatException();

	}

	
	public void undoExecute(Terna command) throws CommandExecutionException {

	}


}
