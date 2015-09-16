package tp.pr5;

import tp.pr5.commands.Command;

/**
 * Se encarga de almacenar la informaci�n dle juego en un momento dado.
 * 
 * @author Ivan Mart�n Herrero & Miguel de Andr�s Herrero.
 * 
 */
public class Terna {
	private Command command;
	private String cadena;
	private Game juegoanterior;

	/**
	 * Constructor de Terna
	 * @param command
	 * @param cadena
	 * @param juego
	 */
	public Terna(Command command, String cadena, Game juego) {
		this.command = command;
		this.cadena = cadena;
		this.juegoanterior = juego;
	}

	/*
	 * Getters & Setters
	 */
	public Command getCommand() {
		return command;
	}

	public String getCadena() {
		return cadena;
	}

	public Game getJuegoanterior() {
		return juegoanterior;
	}

}
