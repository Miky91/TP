package tp.pr5.commands;

import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Terna;
import tp.pr5.commands.exceptions.*;

/**
 * Esta interfaz representa a un comando para la aplicación. Cualquier comando
 * que el jugador pueda realizar lo implementa esta interfaz.Obliga a los
 * comandos para proveer a la aplicación de siete métodos diferentes: 
 * 		Método Parse: intenta analizar la cadena con la información del comando de la clase
 * 			representa. 
 * 		Método Help: devuelve una cadena con una explicación del tipo de
 * 			expresión que el método de análisis apoya 
 * 		Método ConfigureContext: establece el marco necesario para ejecutar el comando 	
 * 		Método Execute: realiza el trabajo real de la orden, la ejecución de la misma. 
 *		Método undoExecute: Deshace la acción realizada. 
 * 
 * El método Execute no tiene ningún parámetro. Por lo tanto el contexto de
 * la ejecución se debe dar al objeto de comando antes de su invocación por el
 * método setContext. Tenga en cuenta que el contexto real depende de la
 * subclase porque la información necesaria varía entre los comandos.
 * 
 * @author Ivan Martin Herrero & Miguel de Andrés Herrero
 * 
 */
public interface Command {

	/**
	 * Establece el contexto de ejecución.
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
	 * Devuelve la descripción de la sintaxis del comando.
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
	 * Deshace el último comando realizado.
	 * @param undo
	 * @throws CommandExecutionException
	 */
	public abstract void undoExecute(Terna undo)
			throws CommandExecutionException;

	
}
