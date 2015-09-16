package tp.pr5.commands;

import java.util.StringTokenizer;

import tp.pr5.*;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

/**
 * El comando usa un objeto. Este comando funciona si el usuario escribe USE |
 * USAR <<id>>
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero
 * 
 */
public class UseCommand implements Command {

	private Player p;
	private String idItem;
	private Game game;
	private Map map;
	private String verbo;
	private String verbo2;

	/**
	 * Constructor por defecto
	 */
	public UseCommand() {
		this.p = null;
		this.idItem = "";
		this.game = null;
	}

	/**
	 * Constructor de un UseCommand valido
	 * @param verbo
	 * @param p
	 * @param id
	 * @param game
	 */
	public UseCommand(String verbo, Player p, String id, Game game) {
		this.p = p;
		this.idItem = id;
		this.game = game;
	}

	/*
	 * Getters & Setters
	 */
	public String getVerbo() {
		return verbo;
	}

	public String getVerbo2() {
		return verbo2;
	}

	public void setVerbo2(String verbo2) {
		this.verbo2 = verbo2;
	}

	public void setVerbo(String verbo) {
		this.verbo = verbo;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	public java.lang.String getIdItem() {
		return this.idItem;
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
	 * El jugador usa el objeto deseado
	 */
	public void execute() throws CommandExecutionException {
		if (this.p.existItem((this.idItem.toUpperCase()))) {
			if (this.p.getItem(this.idItem).use(this.p,
					this.game.getCurrentRoom())) {
				this.p.itemUsed(this.idItem);
				this.p.getItem(this.idItem).setUsed(true);
				if (!this.p.getItem(this.idItem).canBeUsed()) {
					this.game.getObjUsados().add(this.p.getItem(this.idItem));
					this.p.removeItem(this.idItem);
					this.game.getPlayer().itemEmpty(this.idItem);

				} else
					System.out.print("> ");
			} else
				throw new CommandExecutionException(Constants.NOTUSE + "\n> ");
		} else
			this.game.requestError(Constants.STOLEN + this.idItem
					+ "\n> ");
	}

	/**
	 * Devuelve la sintaxis del comando
	 */
	public String getHelp() {
		return Constants.AYUDAUSE;
	}

	/**
	 * Ejecución gráfica
	 */
	/*public void graphicExecute() {
		if (this.p.existItem((this.idItem.toUpperCase()))) {
			if (this.p.getItem(this.idItem).use(this.p,
					this.game.getCurrentRoom())) {
				this.p.itemUsed(this.idItem);
				
				if (!this.p.getItem(this.idItem).canBeUsed()) {
					this.game.getObjUsados().add(this.p.getItem(this.idItem));
					this.p.removeItem(this.idItem);
					this.p.itemEmpty("The " + this.idItem.toLowerCase()
							+ " is empty. It is deleted from the inventory.");
				}
			} else {
				this.game.requestError(Constants.NOTUSE);
			}
		} else {
			this.game.requestError(Constants.STOLEN + this.idItem);
		}
	}
*/
	/**
	 * Parsea la cadena y devuelve un UseCommand o una excepción
	 */
	public Command parse(String cad)
			throws WrongCommandFormatException {
		String words[] = cad.split(" ");
		if (words.length != 2) {
			throw new WrongCommandFormatException();
		} else if (words[0].toUpperCase().equalsIgnoreCase("USE")
				|| words[0].toUpperCase().equalsIgnoreCase("usar"))
			return new UseCommand(words[0], this.p, words[1],
					this.game);
		else
			throw new WrongCommandFormatException();

	}

	/**
	 * Deshace la ultima acción
	 */
	public void undoExecute(Terna undo) throws CommandExecutionException {
		String cadena = undo.getCadena();
		setVerbo2(undo.getCadena());
		String id = undo.getCadena();
		StringTokenizer tk = new StringTokenizer(cadena);
		if (tk.countTokens() != 1)
			setVerbo2(tk.nextToken());
		id = tk.nextToken();
		if (this.p.existItem(id)){
			this.p.getItem(id).desuse(this.p, this.game.getCurrentRoom());
			//this.game.getPila().desapila();
		}
		else {
			if (this.game.getObject(id) != null)
				this.game.getObject(id).desuse(this.p,
						this.game.getCurrentRoom());
			this.p.addItem(this.game.getObject(id));
		}
		System.out.print("> ");
	}

}