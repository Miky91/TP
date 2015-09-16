package tp.pr5;

/**
 * Interfaz de los observadores que quieran ser notificado sobre los eventos
 * relacionados con el juego. Más precisamente, el juego notifica cuando el
 * juego comienza y termina, cuando los jugadores piden ayuda y salir y cuando
 * un error ocurre (un comando no puede ser ejecutado)
 * 
 * @author Ivan Martín Herrero & Miguel de Andres Herrero
 * 
 */
public interface GameObserver {
	
	/**
	 * Notifica que el juego no puede ejecutar un comando.
	 * @param msg
	 */
	public void gameError(java.lang.String msg);
	
	/**
	 * Notifica que el jugador ha pedido informacion de ayuda.
	 */
	public void gameHelp();

	/**
	 * Notifica que el juego ha finalizado y si el jugador ha ganado o ha perdido
	 * @param win
	 */
	public void gameOver(boolean win);

	/**
	 * Notifica que el jugador quiere salir.
	 */
	public void gameQuit();

	/**
	 * Notifica que el juego empieza.
	 * @param initialRoom
	 * @param playerPoints
	 * @param playerHealth
	 */
	public void gameStart(RoomInfo initialRoom, int playerPoints,
			int playerHealth);

}
