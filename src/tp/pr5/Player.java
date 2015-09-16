package tp.pr5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import tp.pr5.gui.PlayerPanel;
import tp.pr5.items.*;

/**
 * Un personaje de un videojuego o juego de rol que es controlado por un
 * jugador. Contiene la salud y los puntos de este jugador. Además, almacena los
 * elementos que el jugador recoge desde las habitaciones que atraviesa los
 * jugadores
 * 
 * @author Ivan Martin Herrero & Miguel de Andrés Herrero
 * 
 */
public class Player extends tp.pr5.Observable<PlayerObserver> {
	private int health;
	private int points;
	private HashMap<String, Item> inventory = new HashMap<String, Item>();
	private PlayerPanel playerPanel;

	/**
	 * Constructor de player
	 */
	public Player() {
		this.health = 100;
		this.points = 0;
	}

	/*
	 * Inicio de Getters & Setters
	 */

	public Item setItem(String id) {
		if (this.inventory.containsKey(id.toUpperCase()))
			return this.inventory.get(id);
		else
			return null;
	}

	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
	}

	public HashMap<String, Item> getInventory() {
		return inventory;
	}

	public PlayerPanel getPlayerPanel() {
		return this.playerPanel;
	}

	/*
	 * Fin de Getters & Setters
	 */
	/**
	 * La vida es actualizada
	 * 
	 * @param health
	 */
	public void addHealth(int health) {

		this.health = this.health + health;
	}

	/**
	 * Añade un item al inventario.
	 * 
	 * @param item
	 * @return true si ha sido añadido false en caso contrario
	 */
	public boolean addItem(Item item) {

		if (this.inventory.containsKey(item.getId().toUpperCase()))
			return false;
		else {
			this.inventory.put(item.getId().toUpperCase(), item);
			return true;
		}

	}

	/**
	 * Suma el valor de los puntos.
	 * 
	 * @param points
	 */
	public void addPoints(int points) {

		this.points = this.points + points;
	}

	/**
	 * Comprueba si el jugador esta muerto.
	 * 
	 * @return true si el jugador esta muerto false en caso contrario.
	 */
	public boolean dead() {
		return this.health <= 0;
	}

	/**
	 * Devuelve si un objeto existe en el inventario
	 * 
	 * @param id
	 * @return true si el objeto existe, false en caso contrario
	 */
	public boolean existItem(String id) {
		return this.inventory.containsKey(id.toUpperCase());

	}

	/**
	 * Devuelve la vida del jugador
	 * 
	 * @return health
	 */
	public int getHealth() {
		return this.health;

	}

	/**
	 * Devuelve el item del inventario con el nombre id
	 * 
	 * @param id
	 * @return item
	 */
	public Item getItem(java.lang.String id) {
		return this.inventory.get(id.toUpperCase());
	}

	/**
	 * Devuelve la puntuación del jugador
	 * 
	 * @return points
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * Pide al jugador informar de que un objeto está vacio.
	 * 
	 * @param itemName
	 */
	public void itemEmpty(java.lang.String itemName) {
		for (PlayerObserver obs : this.observarGame)
			obs.itemEmpty(itemName);
	}

	/**
	 * Pide al jugador informar de que un objeto ha sido usado.
	 * 
	 * @param itemName
	 */
	public void itemUsed(java.lang.String itemName) {
		for (PlayerObserver obs : this.observarGame)
			obs.itemUsed(itemName);
	}

	/**
	 * Pide al jugador informar de la descripción de un objeto.
	 * 
	 * @param itemName
	 */
	public void lookItem(java.lang.String itemName) {
		for (PlayerObserver obs : this.observarGame)
			obs.itemLooked(itemName);
	}

	/**
	 * Elimina un objeto dado del inventario.
	 * 
	 * @param id
	 * @return true si el objeto ha sido eliminado, false en caso contrario
	 */
	public boolean removeItem(java.lang.String id) {
		if (!this.inventory.isEmpty()) {
			this.inventory.remove(id.toUpperCase());
			return true;

		} else
			return false;
	}

	/**
	 * Pide al jugador informar de que el inventario ha cambiado.
	 */
	public void requestInventoryUpdate() {
		Iterator<Item> iterator = (this.inventory.values().iterator());
		ArrayList<Item> inventario = new ArrayList<Item>();
		while (iterator.hasNext()) {
			inventario.add(iterator.next());
		}
		for (PlayerObserver obs : this.observarGame)
			obs.inventoryUpdate(inventario);
	}

	public void setInventory(HashMap<String, Item> inventory) {
		this.inventory = inventory;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Pide al jugador informar acerca de la descripcion del inventario.
	 */
	public void requestLookInventory() {
		Iterator<Item> iterator = (this.inventory.values().iterator());
		ArrayList<Item> inventario = new ArrayList<Item>();
		while (iterator.hasNext()) {
			inventario.add(iterator.next());
		}
		for (PlayerObserver obs : this.observarGame)
			obs.playerLookedInventory(inventario);

	}

	/**
	 * Pide al jugador informar de que los atributos del jugador han cambiado.
	 */
	public void requestPlayerUpdate() {
		for (PlayerObserver obs : this.observarGame)
			obs.playerUpdate(this.health, this.points);
	}

	

}
