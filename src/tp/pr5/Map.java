package tp.pr5;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import tp.pr5.gui.MapPanel;
import tp.pr5.maploader.MapLoaderFromTxtFile;

/**
 * Implementa un mapa con las habitaciones. Desde una habitación, siguiendo una
 * sola dirección sólo conduce a otra habitación única. En realidad, el mapa
 * almacena las puertas que conectan a las habitaciones y ofrece métodos para
 * navegar entre las habitaciones adyacentes.
 * 
 * El mapa maneja una lista de MapObservers con el fin de informarle sobre los
 * eventos relacionados con las habitaciones.
 * 
 * @author Ivan Martin Herrero & Miguel de Andrés Herrero
 * 
 */
public class Map extends tp.pr5.Observable<MapObserver> {

	private ArrayList<Door> mapaPuertas = new ArrayList<Door>();
	private Room currentRoom;
	private MapPanel mapPanel;
	private Room previousRoom;

	/**
	 * Constructora de map
	 * 
	 * @param initRoom
	 * 
	 */
	public Map(Room initRoom) {

		this.mapaPuertas = new ArrayList<Door>();
		this.currentRoom = initRoom;
		this.previousRoom = initRoom;
	}

	/*
	 * Getters && Setters
	 */
	public ArrayList<Door> getMapaPuertas() {
		return mapaPuertas;
	}
	public int getSize() {
		return this.mapaPuertas.size();
	}

	public Room getPreviousRoom() {
		return previousRoom;
	}

	public void setPreviousRoom(Room previousRoom) {
		this.previousRoom = previousRoom;
	}

	public void setMapPanel(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}

	public MapPanel getMapPanel() {
		return this.mapPanel;
	}

	/*
	 * fin de Getters && Setters
	 */

	/*
	 * Inicio de los metodos.
	 */
	/**
	 * Similar a AddDoor pero crea una puerta que puede ser atravesada en las
	 * dos direcciones
	 * 
	 * @param source
	 * @param direction
	 * @param target
	 * @return door
	 */
	public Door addBidirectionalDoor(Room source, Directions direction,
			Room target) {
		Door door = new Door(source, direction, target, true);
		mapaPuertas.add(door);
		return door;

	}

	/**
	 * Añade una puerta creada al mapa.
	 * 
	 * @param d
	 */
	public void addDoor(Door d) {
		mapaPuertas.add(d);
	}

	/**
	 * Crea una puerta entre las habitaciones pasadas por parametro, la añade al
	 * mapa y la devuelve.
	 * 
	 * @param source
	 * @param direction
	 * @param target
	 * @return door
	 */

	public Door addDoor(Room source, Directions direction, Room target) {
		Door door = new Door(source, direction, target, false);
		mapaPuertas.add(door);
		return door;

	}

	/**
	 * Devuelve una referencia a la habitación donde el jugador esta situado.
	 * 
	 * @return currentRoom
	 */
	public Room getCurrentRoom() {
		return this.currentRoom;
	}

	/**
	 * Devuelve la puerta que esta en la direccion dir de la habitacion room
	 * 
	 * @param room
	 * @param dir
	 * @return auxdoor
	 */
	public Door getDoor(Room room, Directions dir) {
		int i = 0;
		Door auxdoor = null;
		boolean encontrado = false;
		while (i < mapaPuertas.size() && !encontrado) {
			encontrado = mapaPuertas.get(i).isInRoom(room, dir);
			if (encontrado)
				auxdoor = mapaPuertas.get(i);
			i++;
		}

		return auxdoor;

	}

	/**
	 * Pide que el jugador quiere examinar la habitación.
	 */
	public void playerExamineRoom() {
		for (MapObserver obs : observarGame)
			obs.playerHasExaminedRoom(this.currentRoom);
	}

	public void enter() {
		for (MapObserver obs : observarGame)
			obs.roomEntered(this.getDoorHab(), this.currentRoom);
	}

	/**
	 * Establece la habitación actual.
	 * 
	 * @param currentRoom
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	/**
	 * Devuelve la puerta en el indice i del array de puertas
	 * 
	 * @param i
	 * @return this.mapaPuertas.get(i) si existe la puerta
	 * @return null si no existe
	 */
	public Door getDoorArray(int i) {
		if (i < this.mapaPuertas.size())
			return this.mapaPuertas.get(i);
		else
			return null;
	}

	private Directions getDoorHab() {
		int i = 0;
		boolean encontrado = false;
		do {
			if (this.mapaPuertas.get(i).getBidirectional()) {
				if (this.mapaPuertas.get(i).getSource() == this.previousRoom
						&& this.mapaPuertas.get(i).getTarget() == this.currentRoom)
					return this.mapaPuertas.get(i).getDirection();
				else if (this.mapaPuertas.get(i).getSource() == this.currentRoom
						&& this.mapaPuertas.get(i).getTarget() == this.previousRoom)
					return this.mapaPuertas.get(i).getDirection().getOposite();
				else
					i++;
			} else if (this.mapaPuertas.get(i).getSource() == this.previousRoom
					&& this.mapaPuertas.get(i).getTarget() == this.currentRoom)
				return this.mapaPuertas.get(i).getDirection();
			else
				i++;

		} while (i < this.mapaPuertas.size() && !encontrado);
		return null;
	}

	public Map cargarFichero(String nombre) throws IOException {

		/**
		 * metodo privado para cargar el fichero de texto
		 * 
		 * @throws IOException
		 *             si hay fallo de fichero
		 */

		Map mapa = null;
		MapLoaderFromTxtFile m = new MapLoaderFromTxtFile();
		FileInputStream a = null;
		a = new FileInputStream(nombre);
		mapa = m.loadMap(a);
		a.close();
		return mapa;

	}
}
