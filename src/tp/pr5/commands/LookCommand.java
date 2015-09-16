package tp.pr5.commands;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Terna;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.console.Console;
import tp.pr5.gui.InfoPanel;
import tp.pr5.items.Item;

/**
 * Su ejecución muestra el inventario al jugador. Este comando funciona si el
 * usuario escribe LOOK | Mira <<id>>
 * 
 * @author Ivan Martin Herrero & Miguel de Andrés Herrero
 * 
 */
public class LookCommand implements Command {

	private String idItem;
	private Player p;
	private Game game;
	private Map map;
	private Console console;
	private InfoPanel infoPanel;

	/**
	 * Constructor por defecto
	 */
	public LookCommand() {
		this.idItem = "";
		this.p = null;
		this.setConsole(new Console());
	}

	/**
	 * Constructor de un LookCommand valido
	 * 
	 * @param verbo
	 * @param id
	 * @param p
	 */
	private LookCommand(String verbo, String id) {
		this.idItem = id;
		this.setConsole(new Console());
	}

	/*
	 * Getters & Setters
	 */
	public java.lang.String GetIdItem() {
		return this.idItem;

	}

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public InfoPanel getInfoPanel() {
		return infoPanel;
	}

	public void setInfoPanel(InfoPanel infoPanel) {
		this.infoPanel = infoPanel;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	/*
	 * Métodos públicos
	 */
	/**
	 * Establece el contexto de ejecución
	 */
	public void configureContext(Game g, Map m, Player p) {
		this.game = g;
		this.setMap(m);
		this.p = p;
	}

	/**
	 * Muestra la descripción de los objetos del inventario o de uno solo
	 */
	public void execute() throws CommandExecutionException {

		ArrayList<Item> inventory = new ArrayList<Item>();
		if (this.idItem.equalsIgnoreCase("")) {
			Iterator<Item> iterator = (this.p.getInventory().values()
					.iterator());
			while (iterator.hasNext()) {
				inventory.add(iterator.next());
			}
			this.p.requestLookInventory();
		} else if (this.p.existItem(this.idItem.toUpperCase())) {
			this.p.requestLookInventory();
		}

		else
			throw new CommandExecutionException("There is no " + this.idItem
					+ " in your inventory.\n> ");

	}

	/**
	 * Muestra la sintaxis del comando.
	 */
	public String getHelp() {
		return Constants.AYUDALOOK + Constants.AYUDALOOKID;
	}



	/**
	 * Parsea la cadena devolviendo un LookCommand o una excepción
	 */
	public Command parse(String cad)
			throws WrongCommandFormatException {
		String words[] = cad.split(" ");
		if (words.length < 1 || words.length > 2) {
			throw new WrongCommandFormatException();
		} else if (words.length == 1
				&& (words[0].equalsIgnoreCase("LOOK") || words[0]
						.equalsIgnoreCase("Mira")))
			return new LookCommand(words[0], "");
		else if (words[0].equalsIgnoreCase("LOOK")
				|| words[0].equalsIgnoreCase("Mira"))
			return new LookCommand(words[0], words[1]);
		else
			throw new WrongCommandFormatException();
	}

	/**
	 * Deshace la ultima accion hecha.
	 */
	public void undoExecute(Terna undo) throws CommandExecutionException {
		this.game = undo.getJuegoanterior();
		this.game.getPila().desapila();
		if (!this.game.getPila().PilaVacia())
			;
		this.game.getPila().desapila();
	}


}
