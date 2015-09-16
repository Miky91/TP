package tp.pr5;

import tp.pr5.commands.*;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

/**
 * El analizador está a cargo de analizar la entrada del usuario con el fin de
 * generar un comando para el juego. En realidad, los delegados del analizador
 * objetos de comando para analizar y generar un comando correcto de acuerdo a
 * la entrada del usuario. Hasta ahora, los comandos válidos son:
 * EXAMINE|EXAMINAR 
 * GO|IR { NORTH|EAST|SOUTH|WEST } 
 * HELP|AYUDA 
 * LOOK|MIRA [<<id>>] 
 * PICK|COGER <<id>> 
 * DROP|SOLTAR <<id>>
 * QUIT|SALIR 
 * USE|USAR <<id>>
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero.
 * 
 */
public class Parser {
	
	/**
	 * Lista de Comandos.
	 */
	static private Command[] commandList = { new GoCommand(),
			new DropCommand(), new ExamineCommand(), new HelpCommand(),
			new LookCommand(), new PickCommand(), new QuitCommand(),
			new UseCommand(), new UndoCommand() };
	
	/**
	 * Devuelve la información acerca de todos los comandos disponibles.
	 * @return ayuda
	 */
	public static String getHelp() {
		return Constants.HELP;
	}

	/**
	 * Parsea el siguiente comando de acuerdo con la entrada del usuario.
	 * @param line
	 * @param executionContext
	 * @return
	 * @throws WrongCommandFormatException
	 */
	public static Command parseCommand(java.lang.String line/* Game executionContext*/) throws WrongCommandFormatException {
		for (Command c : commandList) {
			try {
				return c.parse(line/*, executionContext*/);
			} catch (Exception e) {
			}
		}
		throw new WrongCommandFormatException(Constants.WRONGCOMMAND);
	}

	
}
