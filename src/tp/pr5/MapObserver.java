package tp.pr5;

/**
 * Interfaz de los observadores que deseen ser notificados de los
 * acontecimientos relativos a las habitaciones. Más precisamente, el mapa avisa
 * cuando el jugador entra en una habitación o cuando el jugador pidió la
 * descripción de la habitación.
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero
 * 
 */
public interface MapObserver {

	/**
	 * Notifica que el jugador ha examinado una habitación
	 * 
	 * @param r
	 */
	public void playerHasExaminedRoom(RoomInfo r);

	/**
	 * Notifica que el jugador ha entrado en una habitación desde la direccion
	 * direction
	 * 
	 * @param direction
	 * @param targetRoom
	 */
	public void roomEntered(Directions direction, RoomInfo targetRoom);

}
