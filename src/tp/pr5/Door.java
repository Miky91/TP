package tp.pr5;

/**
 * Una puerta une dos habitaciones A y B en una direcci�n. Si esta definida como
 * Door(A,NORTH,B), significa que B est� al Norte de A. Las puertas pueden ser
 * bidireccionales. Por ejemplo: B esta al Norte de A <=> A est� al Sur de B.
 * Las puertas pueden estar abiertas o cerradas. Por defecto, estan cerradas.
 * 
 * @author Ivan Martin Herrero & Miguel de Andr�s Herrero
 * 
 */

public class Door {

	private Room source;
	private Directions direction;
	private Room target;
	private boolean bidirectional;
	private boolean isOpen;

	/**
	 * Constructora de door
	 * 
	 * @param source
	 *            : habitacion en de la que procede player
	 * @param direction
	 *            : enumerado de direccion
	 * @param target
	 *            : habitacion objetivo de la puerta
	 * @param bidirectional
	 *            : booleano que indica si la puerta es o no bidireccional
	 */
	public Door(Room source, Directions direction, Room target,
			boolean bidirectional) {

		this.source = source;
		this.direction = direction;
		this.target = target;
		this.bidirectional = bidirectional;
		this.isOpen = false;
	}

	/**
	 * Constructora de door
	 * 
	 * @param source
	 *            : habitacion en de la que procede player
	 * @param direction
	 *            : enumerado de direccion
	 * @param target
	 *            : habitacion objetivo de la puerta
	 * @param bidirectional
	 *            : booleano que indica si la puerta es o no bidireccional
	 * @param isOpen
	 *            : booleano que indica si la puerta esta abierta o no
	 */
	public Door(Room source, Directions direction, Room target,
			boolean bidirectional, boolean isOpen) {
		this.source = source;
		this.direction = direction;
		this.target = target;
		this.bidirectional = bidirectional;
		this.isOpen = isOpen;

	}

	/*
	 * Inicio de Getters & Setters
	 */
	
	public boolean getBidirectional() {

		return bidirectional;
	}

	public void setSource(Room source) {
		this.source = source;
	}

	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	public void setTarget(Room target) {
		this.target = target;
	}

	public void setBidirectional(boolean bidirectional) {
		this.bidirectional = bidirectional;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Directions getDirection() {
		return direction;
	}

	public Room getSource() {
		return source;
	}

	public Room getTarget() {
		return target;
	}

	/*
	 * fin de getters y setters
	 */

	/*
	 * inicio de los metodos
	 */

	/**
	 * M�todo que cierra una puerta
	 */
	public void close() {
		this.isOpen = false;
	}

	/**
	 * Devuelve True si la puerta une roomA y roomB y podemos ir de roomA a
	 * roomB
	 * 
	 * @param roomA
	 *            room
	 * @param roomB
	 *            room
	 * @return true si est�n conectadas
	 * @return false si no est�n conectadas
	 */
	public boolean connect(Room roomA, Room roomB) {

		if (this.source == roomA && this.target == roomB && this.isOpen) {
			return true;
		} else {
			if (this.source == roomB && this.target == roomA && this.isOpen
					&& this.bidirectional)
				return true;
			else
				return false;
		}
	}

	/**
	 * Comprueba si la puerta est� en la habitaci�n room.
	 * 
	 * @param room
	 * @return true si la puerta est� en la sala
	 * @return false si la puerta no est� en la sala
	 */
	public boolean isInRoom(Room room) {

		if (room == this.source)
			return true;

		else if ((room == this.target) && (this.bidirectional))
			return true;
		else
			return false;
	}

	/**
	 * Comprueba si la puerta est� en la habitaci�n room y en la direccion where
	 * 
	 * @param room
	 *            Room
	 * @param where
	 *            Directions
	 * @return true si est� en la sala
	 * @return false si no est� en la sala
	 */
	public boolean isInRoom(Room room, Directions where) {
		if ((room == this.source && where == this.direction)
				|| (room == this.target && where == this.direction.getOposite()))
			return true;
		else
			return false;
	}

	/**
	 * Comprueba si una puerta est� abierta
	 * 
	 * @return true si la puerta esta abierta(isOpen= true)
	 * @return false si la puerta esta abierta(isOpen= false)
	 */
	public boolean isOpen() {

		return this.isOpen;

	}

	/**
	 * Devuelve la habitaci�n que hay al otro lado de la habitaci�n whereAmI
	 * (incluso si la puerta est� cerrada)
	 * 
	 * @param whereAmI
	 *            Room
	 * @return this.target si te encuentras en la habitacion fuente
	 * @return this.source si te encuentras en la habitacion destino &&
	 *         bidirectional
	 */

	public Room nextRoom(Room whereAmI) {
		if (whereAmI == this.source)
			return this.target;
		else if (whereAmI == this.target && this.bidirectional)
			return this.source;
		else
			return null;
	}

	/**
	 * Abre una puerta
	 * 
	 */
	public void open() {

		this.isOpen = true;
	}

}
