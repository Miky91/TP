package tp.pr5.commands;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Terna;
import tp.pr5.commands.Command;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

import java.util.StringTokenizer;



/**
 * Este comando suelta un objeto del inventario del jugador en la habitación
 * actual. Este comando funciona si el jugador escribe DROP|SOLTAR <<id>>
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero
 * 
 */
public class DropCommand implements Command {

	private Game game;
	private Player player;
	private String item;
	private Map map;

	/**
	 * Constructor por defecto
	 */
	public DropCommand() {
		player = null;
		item = null;
	}

	/**
	 * Constructor de un DropCommand valido.
	 * 
	 * @param player
	 * @param currentRoom
	 * @param item
	 */
	public DropCommand( String item) {
		this.item = item;

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

	public Game getGame() {
		return game;
	}

	public String getItem() {
		return this.item;
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
		this.map = m;
		this.player = p;
	}

	/**
	 * La ejecución intenta soltar el objeto del inventario en la habitación
	 * actual.
	 */
	public void execute() throws CommandExecutionException {
		if (this.player.existItem(this.item.toUpperCase())) {
			if (this.game.getCurrentRoom().existsItem(this.item)&&(this.game.getCurrentMap().getMapPanel() ==null))
				throw new CommandExecutionException("> ");
			else {
				this.game.getCurrentRoom().addItem(this.player.getItem(this.item
						.toUpperCase()));
				this.player.removeItem(this.item.toUpperCase());
				this.player.requestInventoryUpdate();
				
			}

		} else
			throw new CommandExecutionException("You do not have any "
					+ this.item + ".\n ");
	}

	/**
	 * Devuelve la descripción de la sintaxis del comando.
	 */
	public String getHelp() {
		String ayuda;
		ayuda = Constants.AYUDADROP;
		return ayuda;
	}

	/**
	 * La ejecución intenta soltar el objeto en la habitación actual
	 */
	/**
	 * Parsea la cadena devolviendo un DropCommand o lanzando una excepción.
	 */
	public Command parse(String cad)

			throws WrongCommandFormatException {
		this.configureContext(this.game, this.map, this.player);
		String words[] = cad.split(" ");
		if (words.length < 2 || words.length > 2) {
			throw new WrongCommandFormatException();
		} else if (words[0].equalsIgnoreCase("DROP")
				|| words[0].equalsIgnoreCase("soltar")) {
			return new DropCommand(words[1]);
		} else
			throw new WrongCommandFormatException();

	}

	/**
	 * Deshace la acción DropCommand anterior.
	 */
	public void undoExecute(Terna undo) throws CommandExecutionException {
		String cadena = undo.getCadena();
		String verbo = undo.getCadena();
		String id = undo.getCadena();
		StringTokenizer tk = new StringTokenizer(cadena);
		if (tk.countTokens() != 1)
			verbo = tk.nextToken();
		id = tk.nextToken();
		Command deshacer = new PickCommand(verbo, id, undo.getJuegoanterior());
		deshacer.configureContext(this.game, this.map, this.player);
		deshacer.execute();
		}
	}

