package tp.pr5;

/**
 * Interfaz que reduce la interfaz de la sala. Con esta interfaz sólo los
 * métodos que no modifican la sala están expuestos a los observadores.
 * 
 * @author Ivan Martin Herero & Miguel de Andrés Herrero
 * 
 */
public interface RoomInfo {

	/**
	 * Devuelve la descripcion de la habitación y una lista con las id's de los
	 * objetso y su descripcion, que pertenecen a esta habitacion.
	 */
	public java.lang.String getDescription();

	/**
	 * Devuelve el nombre de la habitación.
	 */
	public java.lang.String getName();

	/**
	 * Devuelve true si la habitación es de salida.
	 */
	public boolean isExit();

}
