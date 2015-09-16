package tp.pr5.commands;

import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Terna;
import tp.pr5.commands.exceptions.*;

/**
 * Esta interfaz representa a un comando para la aplicaci�n. Cualquier comando
 * que el jugador pueda realizar lo implementa esta interfaz.Obliga a los
 * comandos para proveer a la aplicaci�n de siete m�todos diferentes: 
 * 		M�todo Parse: intenta analizar la cadena con la informaci�n del comando de la clase
 * 			representa. 
 * 		M�todo Help: devuelve una cadena con una explicaci�n del tipo de
 * 			expresi�n que el m�todo de an�lisis apoya 
 * 		M�todo ConfigureContext: establece el marco necesario para ejecutar el comando 	
 * 		M�todo Execute: realiza el trabajo real de la orden, la ejecuci�n de la misma. 
 *		M�todo undoExecute: Deshace la acci�n realizada. 
 * 
 * El m�todo Execute no tiene ning�n par�metro. Por lo tanto el contexto de
 * la ejecuci�n se debe dar al objeto de comando antes de su invocaci�n por el
 * m�todo setContext. Tenga en cuenta que el contexto real depende de la
 * subclase porque la informaci�n necesaria var�a entre los comandos.
 * 
 * @author Ivan Martin Herrero & Miguel de Andr�s Herrero
 * 
 */
public interface Command {

	/**
	 * Establece el contexto de ejecuci�n.
	 * @param g
	 * @param m
	 * @param p
	 */
	public abstract void configureContext(Game g, Map m, Player p);
	
	/**
	 * Ejecuta el comando que debe estar implementado en todas las subclases no abstractas
	 * @throws CommandExecutionException
	 */
	public abstract void execute() throws CommandExecutionException;
	
	/**
	 * Devuelve la descripci�n de la sintaxis del comando.
	 * @return
	 */
	public abstract java.lang.String getHelp();
	
	
	/**
	 * Parsea la cadena devolviendo una instancia correspondiente 
	 * a la subclase si la cadena se ajusta a la sintaxis del comando.
	 * @param cad
	 * @param execContext
	 * @return
	 * @throws WrongCommandFormatException
	 */
	public abstract Command parse(java.lang.String cad/*, Game execContext*/)
			throws WrongCommandFormatException;

	/**
	 * Deshace el �ltimo comando realizado.
	 * @param undo
	 * @throws CommandExecutionException
	 */
	public abstract void undoExecute(Terna undo)
			throws CommandExecutionException;

	
}
