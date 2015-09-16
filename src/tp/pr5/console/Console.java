package tp.pr5.console;

import java.util.List;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.GameObserver;
import tp.pr5.MapObserver;
import tp.pr5.PlayerObserver;
import tp.pr5.RoomInfo;
import tp.pr5.items.Item;

/**
 * La vista que muestra el juego en la consola. Se implementa todas las
 * interfaces de observadores, a fin de ser notificados acerca de todos los
 * eventos que se producen cuando el juego está en marcha.
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero
 * 
 */
public class Console implements GameObserver, PlayerObserver, MapObserver {

	/**
	 * Constructor por defecto
	 */
	public Console() {

	}

	/*
	 * Inicio de los Metodos publicos.
	 */

	/**
	 * Notifica que el juego no puede ejecutar un comando
	 */
	public void gameError(String msg) {
		System.out.print(msg);
	}

	/**
	 * Notifica que el jugador ha pedido la información de ayuda
	 */
	public void gameHelp() {
		System.out.println(Constants.HELP);
		System.out.print("> ");
	}

	/**
	 * Notifica que el juego ha finalizado y si el jugador ha ganado o ha muerto
	 */
	public void gameOver(boolean win) {
		if (win){
			System.out.println(Constants.SALIDA);
			}
		else this.gameQuit();
	}
	
	/**
	 * Notifica que el jugador quiere salir del juego.
	 */
	public void gameQuit() {
		System.out.println(Constants.MUERTO);
		System.exit(1);
	}
	/**
	 * Notifica que el juego ha empezado
	 */
	public void gameStart(RoomInfo initialRoom, int playerPoints,
			int playerHealth) {
		System.out.print(initialRoom.getDescription() + "\n" + "HEALTH: "
				+ playerHealth + " SCORE: " + playerPoints+"\n> ");
	}

	

	/**
	 * Notifica que el inventario del jugador ha sido modificado
	 */
	public void inventoryUpdate(List<Item> inventory) {
		System.out.print("> ");
	}
	
	/**
	 * Notifica que un objeto está vacio.
	 */
	public void itemEmpty(String itemName) {
		System.out.print("The " + itemName
				+ " is empty. It´s deletes from the inventory\n> ");
	}

	/**
	 * Notifica que el jugador quiere examinar un objeto.
	 */
	public void itemLooked(String description) {
		System.out.println(description);
		System.out.print("> ");
	}

	/**
	 * Notifica que un objeto ha sido usado.
	 */
	public void itemUsed(String itemName) {
		System.out.println("Something changes ...");

	}

	/**
	 * Notifica que el jugador está muerto
	 */
	public void playerDead() {
		System.out.println(Constants.MUERTO);
	}
	
	/**
	 * Notifica que el jugador desea examinar la habitción.
	 */
	public void playerHasExaminedRoom(RoomInfo r) {
		System.out.println(r.getDescription());
	}
	
	/**
	 * Notifica que el jugador ha mirado el inventario.
	 */
	public void playerLookedInventory(List<Item> inventory) {
		if(inventory.isEmpty())
			System.out.println(Constants.POOR);
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(inventory.get(i).toString());
		}
		System.out.print("> ");
	}

	/**
	 * Notifica que los atributos del jugador han cambiado
	 */
	public void playerUpdate(int newHealth, int newScore) {
		System.out.println("HEALTH: " + newHealth + " SCORE: " + newScore);
	}
	
	/**
	 * Notifica que el jugador ha entrado en una habitación.
	 */
	public void roomEntered(Directions direction, RoomInfo targetRoom) {
		System.out.println("...moving to " + direction.name());
		System.out.println(targetRoom.getDescription());
	}

/*
 * Fin de los métodos públicos
 */

}
