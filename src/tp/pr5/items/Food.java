package tp.pr5.items;

import tp.pr5.Player;
import tp.pr5.Room;

/**
 * Representa un alimento. Cualquier alimento tiene un valor que representa la
 * cantidad de salud que se obtiene cuando se utiliza este alimento. Tengamos en
 * cuenta que el alimento puede ser podrido. En este caso, el jugador pierde la
 * salud. Los alimentos pueden utilizarse más de una vez (por ejemplo una
 * botella de agua), pero tiene un número limitado de usos.
 * 
 * @author Ivan Martin Herrero & Miguel de Andrés Herrero
 * 
 */
public class Food extends ExpirationItem {

	private int health;

	/**
	 * Constructor de Food
	 * @param name
	 * @param description
	 * @param health
	 * @param times
	 */
	public Food(java.lang.String name, java.lang.String description,
			int health, int times) {
		super(name, description, times);
		this.health = health;
	}

	/**
	 * Constructor de comida de un solo uso
	 * @param name
	 * @param description
	 * @param health
	 */
	public Food(java.lang.String name, java.lang.String description, int health) {
		super(name, description, 1);
		this.health = health;
	}
	/*
	 * Inicio de Getters & Setters
	 */
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}


	/*
	 * fin de getters y setters
	 */

	/*
	 * inicio de los metodos públicos
	 */
	/**
	 * Devuelve verdadero si el jugador puede usar este objeto en esta sala.
	 */
	public boolean use(Player who, Room where) {
		if (canBeUsed()) {
			who.addHealth(this.health);
			who.requestPlayerUpdate();
			decrement();
			return true;
		} else
			return false;

	}
	/**
	 * Devuelve verdadero si se ha deshecho su uso.
	 */
	public boolean desuse(Player who, Room where) {
		who.addHealth(-this.health);
		increment();
		return true;
	}
/*
 * Fin de los métodos públicos
 */
}
