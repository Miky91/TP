package tp.pr5;

import java.util.ArrayList;

import tp.pr5.items.*;

/**
 * Representa una habitaci�n en el juego de aventuras. Cada habitaci�n dispone
 * de una descripci�n textual de s� misma. Esta descripci�n se muestra cuando el
 * jugador entra en la habitaci�n. Algunas habitaciones son de salida. Cuando el
 * jugador llega a esta sala, el juego termina. Las habitaciones pueden contener
 * elementos. En este caso, el nombre de estos elementos se muestran en la
 * descripci�n de la habitaci�n.
 * 
 * @author Ivan Martin Herrero & Miguel de Andr�s Herrero
 * 
 */
public class Room implements RoomInfo {

	private java.lang.String descripcion;
	private boolean salida;
	private ArrayList<Item> items;
	private String nombre;
	private boolean isVisited;

	/**
	 * Inicialmente, la habitaci�n es creada sin objetos.
	 * 
	 * @param exit
	 * @param description
	 */
	public Room(boolean exit, java.lang.String description) {
		this.salida = exit;
		this.descripcion = description;
		this.items = new ArrayList<Item>();
		this.isVisited = false;
	}

	/**
	 * Habitaci�n creada con un conjunto de elementos.
	 * 
	 * @param exit
	 * @param description
	 * @param items
	 */
	public Room(boolean exit, java.lang.String description, Item[] items) {
		this.salida = exit;
		this.descripcion = description;
		this.items = new ArrayList<Item>();
		this.isVisited = false;
		for (int i = 0; i < items.length; i++) {
			addItem(items[i]);
		}
	}

	/**
	 * Inicialmente la sala se crea sin objetos.
	 * 
	 * @param exit
	 * @param description
	 * @param nombre
	 */
	public Room(boolean exit, java.lang.String description, String nombre) {
		this.salida = exit;
		this.descripcion = description;
		this.items = new ArrayList<Item>();
		this.nombre = nombre;
		this.isVisited = false;
	}

	/*
	 * Inicio de Getters & Setters
	 */
	
	public ArrayList<Item> getItems() {
		return this.items;
	}

	public void setItems(ArrayList<Item> items) {

		this.items = items;

	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public String getNombre() {
		
		return this.nombre;
	}

	
	/*
	 * Fin de Getters & Setters
	 */

	/*
	 * Inicio de los metodos publicos
	 */

	/**
	 * A�ade un objeto it
	 * 
	 * @param it
	 * @return true si lo a�ade, false en c.c.
	 */
	public boolean addItem(Item it) {
		int position = getPosition(it.getId().toUpperCase());
		boolean resultado = true;

		if (position == this.items.size()) {
			this.items.add(it);
		} else {
			resultado = false;
		}
		return resultado;
	}

	/**
	 * Devuelve true si el objeto <<id>> existe.
	 * 
	 * @param id
	 * @return true si existe, false en c.c.
	 */
	public boolean existsItem(java.lang.String id) {
		return this.getPosition(id.toUpperCase()) < this.items.size();

	}

	/**
	 * Devuelve la descripcion de la habitaci�n y una lista con las id's de los
	 * objetso y su descripcion, que pertenecen a esta habitacion.
	 */
	public java.lang.String getDescription() {
		String descripcion;
		descripcion = this.descripcion + "\n" + Constants.CONTAIN;
		if (this.items.size() == 0) {
			descripcion = this.descripcion + Constants.VACIO;
		} else {

			for (int i = 0; i < this.items.size(); i++) {
				descripcion += "\n" + this.items.get(i).toString();
			}
		}

		return descripcion;

	}
	/**
	 * Devuelve el nombre de la habitaci�n.
	 */
	public String getName() {
		return this.nombre;
	}
	
	/**
	 * Devuelve true si la habitaci�n es de salida.
	 */
	public boolean isExit() {
		return this.salida;

	}
	
	/**
	 * Coge un objeto de la habitaci�n y lo a�ade al jugador.
	 * @param who
	 * @param id
	 * @return true si lo hace bien, false en c.c.
	 */
	public boolean pickItem(Player who, java.lang.String id) {
		String Message = "You have another " + id + " in your inventory.";
		int position = this.getPosition(id.toUpperCase());
		if (position < this.items.size()) {

			Item it = this.items.get(position);
			if (!who.addItem(it)) {
				System.out.println(Message);
				return false;
			} else {
				who.addItem(it);
				this.items.remove(position);
				return true;
			}

		} else {
			return false;
		}

	}
	/*
	 * Fin de los m�todos p�blicos.
	 */
	
	/*
	 * Incio de los metodos privados.
	 */
	/**
	 * Devuelve la posicion del objeto con nombre id.
	 * @param id
	 * @return
	 */
	private int getPosition(String id) {
		int i = 0;
		boolean encontrado = false;
		while (i < this.items.size() && !encontrado) {
			encontrado = this.items.get(i).getId().toUpperCase()
					.equals(id.toUpperCase());
			if (!encontrado) {
				i++;
			}
		}
		return i;
	}
	/*
	 * Fin de los metodos privados.
	 */
		
		
	
	

}
