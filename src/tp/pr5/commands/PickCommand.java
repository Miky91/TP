package tp.pr5.commands;

import java.util.StringTokenizer;
import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Terna;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

/**
 * Este comando coge un objeto de la habitación y lo pone en el inventario del
 * jugador. Este comando funciona si el usuario escribe PICK | COGER <<id>>
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero.
 * 
 */
public class PickCommand implements Command {
	private String id;
	private Game game;
	private Map map;
	private Player player;

	/**
	 * Constructor por defecto
	 */
	public PickCommand() {
		this.id = "";
		this.game = null;
	}

	/**
	 * Constructor de un PickCommand valido
	 * 
	 * @param verbo
	 * @param id
	 * @param game
	 */
	public PickCommand(String verbo, String id, Game game) {
		this.id = id;
		this.game = game;
	}

	/*
	 * Getters & Setters
	 */
	public String getId() {
		return id;
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

	/*
	 * Métodos públicos
	 */
	/**
	 * Establece el contexto de ejecución.
	 */
	public void configureContext(Game g, Map m, Player p) {

		this.game = g;
		this.setMap(m);
		this.setPlayer(p);
	}

	/**
	 * El jugador añade un objeto al inventario desde la habitación actual, si
	 * existe el objeto
	 */
	public void execute() throws CommandExecutionException {
		if (this.game.getPlayer().existItem(this.id))
			throw new CommandExecutionException("You have another " + this.id
					+ " in your inventory\n> ");
		else if ((this.game.getCurrentRoom().existsItem(this.id))) {
			
			this.game.getCurrentRoom().pickItem(this.player, this.id);
			this.player.requestInventoryUpdate();
			
		} else {
			throw new CommandExecutionException(
					"Do you get to pick what you dream about?\n> ");
		}
	}

	/**
	 * Devuelve la sintaxis del comando
	 */
	public String getHelp() {
		return Constants.AYUDAPICK;
	}

	
	/**
	 * Parsea la cadena devolviendo un PickCommand o una excepción
	 */
	public Command parse(String cad)
			throws WrongCommandFormatException {
		String words[] = cad.split(" ");
		if (words.length != 2) {
			throw new WrongCommandFormatException();
		} else if (words[0].toUpperCase().equalsIgnoreCase("PICK")
				|| words[0].toUpperCase().equalsIgnoreCase("Coger"))
			return new PickCommand(words[0], words[1], this.game);
		else
			throw new WrongCommandFormatException();
	}

	/**
	 * Deshace la ultima acción hecha.
	 */
	public void undoExecute(Terna undo) throws CommandExecutionException {
		String cadena = undo.getCadena();
		StringTokenizer tk = new StringTokenizer(cadena);
		if (tk.countTokens() != 1)
			cadena = tk.nextToken();
		cadena = tk.nextToken();
		if (!cadena.equalsIgnoreCase("pick")){
		Command deshacer = new DropCommand(cadena);
		deshacer.configureContext(this.game, this.map, this.player);
		deshacer.execute();
		}
	}
	
	
}
