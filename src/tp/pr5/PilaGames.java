/**
 * 
 */
package tp.pr5;

import java.util.ArrayList;

import tp.pr5.commands.Command;
import tp.pr5.commands.DropCommand;
import tp.pr5.commands.ExamineCommand;
import tp.pr5.commands.GoCommand;
import tp.pr5.commands.HelpCommand;
import tp.pr5.commands.LookCommand;
import tp.pr5.commands.PickCommand;
import tp.pr5.commands.QuitCommand;
import tp.pr5.commands.UndoCommand;
import tp.pr5.commands.UseCommand;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.pila.exception.ExceptionPila;

/**
 * Es la pila donde se va a guardar la ejecución del programa.
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero
 * 
 */
public class PilaGames {

	private ArrayList<Terna> pila;
	static private Command[] commandList = { new GoCommand(),
			new DropCommand(), new ExamineCommand(), new HelpCommand(),
			new LookCommand(), new PickCommand(), new QuitCommand(),
			new UseCommand(), new UndoCommand() };

	/**
	 * Constructor por defecto
	 */
	public PilaGames() {
		this.pila = new ArrayList<Terna>();
	}

	/*
	 * Getters & Setters
	 */
	public static Command[] getCommandList() {
		return commandList;
	}

	public static void setCommandList(Command[] commandList) {
		PilaGames.commandList = commandList;
	}

	/*
	 * Métodos públicos
	 */
	/**
	 * Añade una terna a la pila
	 * 
	 * @param terna
	 * @throws WrongCommandFormatException
	 */
	public void apila(Terna terna) throws WrongCommandFormatException {

		Terna informacion = terna;
		pila.add(informacion);
	}

	/**
	 * Quita la primera terna de la pila
	 * 
	 * @throws ExceptionPila
	 */
	public void desapila() throws ExceptionPila {
		String e = "";
		if (!this.PilaVacia())
			pila.remove(pila.size() - 1);
		else
			throw new ExceptionPila(e);
	}

	/**
	 * Devuelve true si la pila esta vacia
	 * 
	 * @return
	 */
	public boolean PilaVacia() {
		return pila.size() == 0;

	}

	public int getSize(){
		return this.pila.size();
	}
	
	/**
	 * Devuelve la terna en la cima de la pila
	 * 
	 * @return terna
	 * @throws ExceptionPila
	 */
	public Terna Cima() throws ExceptionPila {

		String e = "Imposible deshacer mas\n";
		if (!this.PilaVacia())
			return this.pila.get(this.pila.size() - 1);
		else
			throw new ExceptionPila(e);

	}

}
