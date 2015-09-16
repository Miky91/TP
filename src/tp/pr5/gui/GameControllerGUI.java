package tp.pr5.gui;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.Game;
import tp.pr5.GameController;
import tp.pr5.GameObserver;
import tp.pr5.MapObserver;
import tp.pr5.PlayerObserver;
import tp.pr5.Terna;
import tp.pr5.commands.Command;
import tp.pr5.commands.DropCommand;
import tp.pr5.commands.PickCommand;
import tp.pr5.commands.QuitCommand;
import tp.pr5.commands.UseCommand;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.console.GameControllerConsole;
import tp.pr5.items.Item;

/**
 * El controlador utiliza cuando la aplicación está configurada como una
 * aplicación de Swing. Es responsable de solicitar el inicio del juego y
 * vuelve a dirigir las acciones realizadas por el usuario en la ventana para el
 * juego.
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero
 * 
 */
public class GameControllerGUI extends GameController {

	private Command command;
	private GameObserver gameObserver;
	private MapObserver mapObserver;
	private PlayerObserver playerObserver;
	private String mode;
	private GameControllerConsole console;

	public GameControllerConsole getConsole() {
		return console;
	}

	public void setConsole(GameControllerConsole console) {
		this.console = console;
	}

	/**
	 * Constructor que usa el modelo.
	 * @param game
	 */
	public GameControllerGUI(Game game) {
		super(game);
		this.mode = game.getMode();
		this.gameObserver = new InfoPanel(this.game);
		this.mapObserver = new MapPanel(this.game.getCurrentRoom(),this.game);
		this.playerObserver = new InfoPanel(this.game);
		this.registerGameObserver(this.gameObserver);
		this.registerMapObserver(this.mapObserver);
		this.registerPlayerObserver(this.playerObserver);
		
	}

	/*
	 * Inidio de los métodos públicos.
	 */
	/**
	 * Ejecuta un DropCommand
	 * @param item
	 */
	public void executeDropAction(java.lang.String item) {
		String object = item;
		ArrayList<Item> inventory = new ArrayList<Item>();
		command = new DropCommand(item);
		boolean valido = true;
		if (!this.game.getJug().existItem(object))
			valido  = false;
		this.game.executeCommand(command);
		try {
			if (valido){
				Terna terna = new Terna(command, item, this.game);
				this.game.getPila().apila(terna);
			}
		} catch (WrongCommandFormatException e) {
			e.printStackTrace();
		}
		Iterator<Item> iterator = (this.game.getPlayer()).getInventory()
				.values().iterator();
		while (iterator.hasNext()) {
			inventory.add(iterator.next());
		}
		this.game.getPlayer().getPlayerPanel().inventoryUpdate(inventory);
		this.game.getPlayer().requestPlayerUpdate();
		/*this.game
				.getPlayer()
				.getPlayerPanel()
				.playerUpdate(this.game.getPlayer().getHealth(),
						this.game.getPlayer().getPoints());*/
		this.game.getCurrentMap().getMapPanel().getDescriptionRoom()
				.setText(this.game.getCurrentRoom().getDescription());
		this.game
				.getCurrentMap()
				.getMapPanel()
				.getInfoPanel()
				.getInfoLabel()
				.setText(
						"The inventory now contains " + +inventory.size()
								+ " elements");

	}
	

	/**
	 * Ejecuta un PickCommand.
	 * @param item
	 */
	public void executePickAction(java.lang.String item) {

		String object = item;
		String aux = "PICK" + " " + object;
		ArrayList<Item> inventory = new ArrayList<Item>();
		command = new PickCommand("PICK", object, this.game);
		boolean valido = true;
		if (this.game.getJug().existItem(object))
			valido  = false;
		this.game.executeCommand(command);
		Terna terna = new Terna(command, aux, this.game);
		try {
			if (valido)
				this.game.getPila().apila(terna);
		} catch (WrongCommandFormatException e) {
			e.printStackTrace();
		}
		this.game.getCurrentMap().getMapPanel().getDescriptionRoom()
				.setText(this.game.getCurrentRoom().getDescription());

		Iterator<Item> iterator = (this.game.getPlayer()).getInventory()
				.values().iterator();
		while (iterator.hasNext()) {
			inventory.add(iterator.next());
		}
		this.game.getPlayer().getPlayerPanel().inventoryUpdate(inventory);
		this.game.getCurrentMap().getMapPanel().getInfoPanel().repaint();
		this.game
				.getCurrentMap()
				.getMapPanel()
				.getInfoPanel()
				.getInfoLabel()
				.setText(
						"The inventory now contains " + +inventory.size()
								+ " elements");
	}

	/**
	 * Ejecuta un UseAction
	 * @param item
	 */
	public void executeUseAction(java.lang.String item) {

		String object = item;
		String aux = "USE" + " " + object;
		ArrayList<Item> inventory = new ArrayList<Item>();
		command = new UseCommand("USE", this.game.getPlayer(), object,
				this.game);
		this.game.executeCommand(command);
		Terna terna = new Terna(command, aux, this.game);
		try {
			this.game.getPila().apila(terna);
		} catch (WrongCommandFormatException e) {
			
			e.printStackTrace();
		}
		Iterator<Item> iterator = (this.game.getPlayer()).getInventory()
				.values().iterator();
		while (iterator.hasNext()) {
			inventory.add(iterator.next());
		}
		this.game.getPlayer().getPlayerPanel().inventoryUpdate(inventory);
		this.game.getPlayer().requestPlayerUpdate();
		this.game
				.getPlayer()
				.getPlayerPanel()
				.playerUpdate(this.game.getPlayer().getHealth(),
						this.game.getPlayer().getPoints());
		this.game.getCurrentMap().getMapPanel().getDescriptionRoom()
				.setText(this.game.getCurrentRoom().getDescription());
		this.game.getCurrentMap().getMapPanel().getInfoPanel().getInfoLabel()
				.setText("You have used " + item);

	}
	
	/**
	 * Ejecuta un QuitCommand.
	 */
	public void executeQuitAction() {
		command = new QuitCommand("QUIT", this.game);
		this.game.executeCommand(command);
		
	}
	

	/**
	 * Este metodo solo pide al juego empezar.
	 */
	public void runGame() {
			if (this.mode.equalsIgnoreCase("both")){
			setConsole(new GameControllerConsole(this.game));
			}
			this.game.requestStart();
		

	}

}
