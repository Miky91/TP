package tp.pr5;

/**
 * Interfaz que reduce la interfaz de la sala. Con esta interfaz s�lo los
 * m�todos que no modifican la sala est�n expuestos a los observadores.
 * 
 * @author Ivan Martin Herero & Miguel de Andr�s Herrero
 * 
 */
public interface RoomInfo {

	/**
	 * Devuelve la descripcion de la habitaci�n y una lista con las id's de los
	 * objetso y su descripcion, que pertenecen a esta habitacion.
	 */
	public java.lang.String getDescription();

	/**
	 * Devuelve el nombre de la habitaci�n.
	 */
	public java.lang.String getName();

	/**
	 * Devuelve true si la habitaci�n es de salida.
	 */
	public boolean isExit();

}
