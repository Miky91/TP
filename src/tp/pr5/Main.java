package tp.pr5;

import jargs.gnu.CmdLineParser;

import java.io.*;

import javax.sound.sampled.AudioSystem;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import tp.pr5.sonido.Prueba;
import tp.pr5.console.GameControllerConsole;
import tp.pr5.gui.MainWindow;
import tp.pr5.maploader.exceptions.WrongMapFormatException;

public class Main {
	/**
	 * 
	 * @param nombre
	 * @return
	 * @throws IOException
	 */

	

	/**
	 * metodo principal que inicia el juego
	 * 
	 * @param args
	 *            String comprueba si el fichero existe, y si su formato es
	 *            bueno, a través de una estructura try catch si al cargar el
	 *            dormitorion inicial no obtiene null, inicia el bucle principal
	 */
	public static void main(java.lang.String[] args) {

		CmdLineParser parsear = new CmdLineParser();
		CmdLineParser.Option interfaz = parsear.addStringOption('i',
				"interface");

		CmdLineParser.Option fileName = parsear.addStringOption('m', "map");
		try {
			parsear.parse(args);
		} catch (CmdLineParser.OptionException e) {
			System.err.println(e.getMessage());
			System.exit(2);
		}

		String interfazType = (String) parsear.getOptionValue(interfaz);
		String mapFile = (String) parsear.getOptionValue(fileName);
		Map cargar = new Map(null);

		if (interfazType.equalsIgnoreCase("console")) {

			try {
				Map mapa = cargar.cargarFichero(mapFile);
				mapa.getCurrentRoom().setVisited(true);
				mapa.getPreviousRoom().setVisited(true);
				Room r = mapa.getCurrentRoom();
				if (r != null) {
					Game g = new Game(mapa);
					Prueba prueba = new Prueba();
					prueba.getArchivo_de_sonido().start();
					GameControllerConsole consola = new GameControllerConsole(g);
					consola.runGame();
				}
			} catch (FileNotFoundException e) {
				System.out.println(Constants.NOFILE);
			} catch (WrongMapFormatException e) {
				System.out.println(e.getCause().getMessage());
			} catch (IndexOutOfBoundsException e) {
				System.out.println(Constants.NOFILE);
			} catch (IOException e) {
				System.out.println(e.getCause().getMessage());
			}
		} else if (interfazType.equalsIgnoreCase("swing")) {
			try {
				Map mapa = cargar.cargarFichero(mapFile);
				mapa.getCurrentRoom().setVisited(true);
				mapa.getPreviousRoom().setVisited(true);
				Room r = mapa.getCurrentRoom();
				if (r != null) {
					Game g = new Game(mapa);
					Prueba prueba = new Prueba();
					
					Object [] valores = {"Que la fuerza me acompañe","why not?","no tengo opcion :S" }; 
					Icon icono = new ImageIcon("darth vader.jpg");
					String respuesta = (String)JOptionPane.showInputDialog( null,
							"¿Preparado para iniciar la aventura en la facultad?\n Da igual lo que pongas, vas a jugar",
							"why not?", JOptionPane.QUESTION_MESSAGE, icono, valores, valores[0]);
					prueba.getArchivo_de_sonido().start();
					MainWindow inst = new MainWindow(g);
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
					inst.getControlador().runGame();

				}
			} catch (FileNotFoundException e) {
				System.out.println(Constants.NOFILE);
			} catch (WrongMapFormatException e) {
				System.out.println(e.getCause().getMessage());
			} catch (IndexOutOfBoundsException e) {
				System.out.println(Constants.NOFILE);
			} catch (IOException e) {
				System.out.println(e.getCause().getMessage());
			}

		} else  if (interfazType.equalsIgnoreCase("both")){
			try{
				Map mapa = cargar.cargarFichero(mapFile);
				mapa.getCurrentRoom().setVisited(true);
				mapa.getPreviousRoom().setVisited(true);
				Room r = mapa.getCurrentRoom();
				if (r != null) {
					Game g = new Game(mapa);
					g.setMode("both");
					Prueba prueba = new Prueba();
					Object [] valores = {"Que la fuerza me acompañe","why not?","no tengo opcion :S" }; 
					Icon icono = new ImageIcon("darth vader.jpg");
					String respuesta = (String)JOptionPane.showInputDialog( null,
							"¿Preparado para iniciar la aventura en la facultad?\n Da igual lo que pongas, vas a jugar",
							"why not?", JOptionPane.QUESTION_MESSAGE, icono, valores, valores[0]);
					prueba.getArchivo_de_sonido().start();
					MainWindow inst = new MainWindow(g);
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
					inst.getControlador().runGame();
				
					

				}
			} catch (FileNotFoundException e) {
				System.out.println(Constants.NOFILE);
			} catch (WrongMapFormatException e) {
				System.out.println(e.getCause().getMessage());
			} catch (IndexOutOfBoundsException e) {
				System.out.println(Constants.NOFILE);
			} catch (IOException e) {
				System.out.println(e.getCause().getMessage());
			}
		}else System.exit(2);

	}

}
