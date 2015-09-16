package tp.pr5;
import java.awt.Color;

/**
 * Clase Interfaz donde se guardan las constantes del programa.
 * Autores: Iván Martín Herrero
 * 			Miguel de Andrés Herrero
 */
public interface Constants {
	

	
	static final int  VIDAS_PUERTA= 5;
	static final String SALIDA = "GAME OVER\n" + " Thank you for playing, goodbye.";
	static final String MUERTO = "You are dead.\n" + "GAME OVER" + " Thank you for playing, goodbye.";
	static final String CONTAIN = "It contains the following items: ";
	static final String VACIO = "\nThis room is empty"; 
	static final String HELP ="You are lost. You are alone. You wander around\n" + "Your command words are:\n" 
	+ "   "+"EXAMINE|EXAMINAR\n"+"   "+"GO|IR { NORTH|EAST|SOUTH|WEST }\n"+"   " +"HELP|AYUDA\n"+
			"   " +"LOOK|MIRA [<<id>>]\n" + "   "+"PICK|COGER <<id>>\n"+ "   "+"DROP|SOLTAR <<id>>\n"+
	"   "+"QUIT|SALIR\n"+ "   "+"USE|USAR <<id>>\n" ;
	static final String AYUDADROP = "DROP | SOLTAR <<id>>\n";
	static final String AYUDAEXAMINE = "EXAMINE | EXAMINAR \n";
	static final String AYUDAGO = "GO | IR {NORTH|EAST|SOUTH|WEST}\n";
	static final String AYUDAUSE = "USE | USAR <<id>>\n";
	static final String AYUDALOOKID = "LOOK | MIRAR [<<id>>]\n";
	static final String AYUDALOOK = "LOOK | MIRAR\n";
	static final String AYUDAQUIT = "QUIT | SALIR\n";
	static final String AYUDAPICK = "PICK | COGER <<id>>\n";
	static final String MAPEXCEPTION = "Error en la lectura del fichero\n";
	static final String WRONGCOMMAND = "What?\n> ";
	static final String NOFILE = "No map file specified.\n\nUsage: tp.pr3.Main <mapFile>\n";
	static final String WRONGFILE = "File not valid\n";
	static final String STOLEN = "Someone stole your ";
	static final String NOTUSE = "I did not go to TP classes last week, I do not know how to use it... ";
	static final String POOR = "You are poor, you have not got any item (yet).";
	static final String AYUDAUNDO = "UNDO | DESHACER";
	public class Colores{
		
		public static final Color AZUL = new Color(60,90,200);
		public static final Color VERDE = new Color (118,190,65);
		public static final Color GRIS = new Color (128,128,128);
	}

}
