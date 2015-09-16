package tp.pr5;

import tp.pr5.items.Item;

/**
 * Interfaz de los observadores que quieran ser notificado sobre los eventos
 * relacionados con el jugador (la salud, la puntuación y la muerte), el
 * inventario del jugador, y los elementos manipulados por el jugador
 * 
 * @author Ivan Martin Herrero & Miguel de Andrés Herrero
 * 
 */
public interface PlayerObserver {

	/**
	 * Notifica que el inventario del jugador ha sido modificado
	 * @param inventory
	 */
	public void inventoryUpdate(java.util.List<Item> inventory);

	/**
	 * Notifica que un objeto está vacio.
	 * @param itemName
	 */
	public void itemEmpty(java.lang.String itemName);

	/**
	 * Notifica que el jugador quiere examinar un objeto
	 * @param description
	 */
	public void itemLooked(java.lang.String description);

	/**
	 * Notifica que un objeto ha sido usado.
	 * @param itemName
	 */
	public void itemUsed(java.lang.String itemName);

	/**
	 * Notifica la muerte del jugador
	 */
	public void playerDead();

	/**
	 * Notifica que el jugador ha mirado el inventario
	 * @param inventory
	 */
	public void playerLookedInventory(java.util.List<Item> inventory);

	/**
	 * Notifica que los atributos del jugador han cambiado.
	 * @param newHealth
	 * @param newScore
	 */
	public void playerUpdate(int newHealth, int newScore);

}
