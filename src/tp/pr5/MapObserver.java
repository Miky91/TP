package tp.pr5;

/**
 * Interfaz de los observadores que deseen ser notificados de los
 * acontecimientos relativos a las habitaciones. M�s precisamente, el mapa avisa
 * cuando el jugador entra en una habitaci�n o cuando el jugador pidi� la
 * descripci�n de la habitaci�n.
 * 
 * @author Ivan Mart�n Herrero & Miguel de Andr�s Herrero
 * 
 */
public interface MapObserver {

	/**
	 * Notifica que el jugador ha examinado una habitaci�n
	 * 
	 * @param r
	 */
	public void playerHasExaminedRoom(RoomInfo r);

	/**
	 * Notifica que el jugador ha entrado en una habitaci�n desde la direccion
	 * direction
	 * 
	 * @param direction
	 * @param targetRoom
	 */
	public void roomEntered(Directions direction, RoomInfo targetRoom);

}
