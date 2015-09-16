package tp.pr5;

/**
 * Clase abstracta que representa un controlador. El controlador contiene una
 * referencia al punto de entrada al modelo (el juego) y se ejecuta el juego.
 * Adicionalmente, el controlador puede registrar diferentes tipos de
 * observadores.
 * 
 * @author Ivan Martin Herrero & Miguel de Andrés Herrero
 * 
 */
public abstract class GameController {

	protected Game game;
	
	/**
	 * Constructor que usa el modelo.
	 * 
	 * @param game
	 */
	public GameController(Game game) {
		this.game = game;
	}
	/*
	 * Inicio de los Metodos
	 */
	
	/**
	 * Registra un GameObserver en el modelo
	 * 
	 * @param gameObserver
	 */
	public void registerGameObserver(GameObserver gameObserver) {
		this.game.addGameObserver(gameObserver);
	}
	
	/**
	 * Registra un MapObserver en el modelo
	 * 
	 * @param mapObserver
	 */
	public void registerMapObserver(MapObserver mapObserver) {
		this.game.addMapObserver(mapObserver);
	}
	
	/**
	 * Registra un PlayerObserver en el modelo
	 * 
	 * @param playerObserver
	 */
	public void registerPlayerObserver(PlayerObserver playerObserver) {
		this.game.addPlayerObserver(playerObserver);
	}
	
	/**
	 * Método abstracto que ejecuta el juego.
	 */
	public abstract void runGame();

}
