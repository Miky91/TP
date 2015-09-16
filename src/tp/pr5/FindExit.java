package tp.pr5;

import jargs.gnu.CmdLineParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
import tp.pr5.items.Food;
import tp.pr5.items.Item;
import tp.pr5.items.Key;
import tp.pr5.maploader.MapLoaderFromTxtFile;
import tp.pr5.maploader.exceptions.WrongMapFormatException;
import tp.pr5.pila.exception.ExceptionPila;

public class FindExit extends Player {
	private Map map;
	private int movs;
	private Directions direction;
	private ArrayList<String> movPinosPinos;
	private Game game;
	private ArrayList <Terna> opciones = new ArrayList();
	private int movshechos;
	private ArrayList <String >movimientos;
	static private Command[] commandList = { new GoCommand(),
		new DropCommand(), new ExamineCommand(), new HelpCommand(),
		new LookCommand(), new PickCommand(), new QuitCommand(),
		new UseCommand(), new UndoCommand() };
	
	public FindExit(Map map, int maxMovs){
		super();
		this.map = map;
		this.movs = maxMovs;
		this.direction = Directions.UNKNOWN;
		this.movPinosPinos = new ArrayList<String>();
		this.game = new Game(this.map);
		this.addPoints(0);
		this.movshechos = 0;
		this.movimientos = new ArrayList();
	}
	
private static Map cargarFichero(String nombre) throws IOException{
		
		/**
		 * metodo privado para cargar el fichero de texto
		 * @throws IOException si hay fallo de fichero
		 */
		
		Map mapa = null;
		MapLoaderFromTxtFile m = new MapLoaderFromTxtFile();
		FileInputStream a = null;
		 a =new FileInputStream(nombre);
		 mapa = m.loadMap(a);
		a.close();
		return mapa;
		
	}
	
	
	public static void main(java.lang.String[] args) {
	CmdLineParser parsear = new CmdLineParser();
		CmdLineParser.Option interfaz = parsear.addIntegerOption('d',

				"max-depth");

		CmdLineParser.Option fileName = parsear.addStringOption('m', "map");
		try {
			parsear.parse(args);
		} catch (CmdLineParser.OptionException e) {
			System.err.println(e.getMessage());
			System.exit(2);
		}

		int mov = (Integer) parsear.getOptionValue(interfaz);
		String mapFile = (String) parsear.getOptionValue(fileName);
		try{
	    	Map mapa = cargarFichero(mapFile);
	    	Room r = mapa.getCurrentRoom();
	    	if (r != null) {
	    		FindExit IA = new FindExit(mapa, mov);
	    		IA.recursivo();
	    		//IA.LetsPlay();
			}
	    	}catch(FileNotFoundException e){
	    		System.out.println(Constants.NOFILE);
	    	}catch(WrongMapFormatException e){
	    		System.out.println(e.getCause().getMessage());
	    	} catch(IndexOutOfBoundsException e){
	    		System.out.println(Constants.NOFILE);
	    	}catch (IOException e) {
	    		System.out.println(e.getCause().getMessage());
			}
	}
	
	
	public void recursivo(){
		int i;
		int j;
		int k;
		Door auxDoor;
		Terna terna;
		UndoCommand deshacer;		
	while(!this.game.getCurrentRoom().isExit()){	
		for(i= 0; i<this.map.getCurrentRoom().getItems().size();i++){
			
			String id = this.game.getCurrentRoom().getItems().get(i).getId();
			PickCommand comandoPick = new PickCommand("PICK",id,this.game);
			this.game.executeCommand(comandoPick);
			this.movshechos++;
			this.addItem(this.game.getJug().getItem(id));
			terna = new Terna(comandoPick,"PICK "+id, this.game);
			try {
				this.game.getPila().apila(terna);
			} catch (WrongCommandFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			recursivo();		
			deshacer = new UndoCommand("UNDO",this.game);
			try {
				this.opciones.add(this.game.getPila().Cima());
			} catch (ExceptionPila e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.game.executeCommand(deshacer);
			this.movshechos--;
			this.setInventory(this.game.getJug().getInventory());
				
		}
		
		for(j= 0; j<(Directions.values().length)&&(Directions.values()[j]!= this.direction.getOposite());j++){
			GoCommand comando = new GoCommand("GO",Directions.values()[j].toString(),this.game);
			auxDoor  = this.map.getDoor(this.map.getCurrentRoom(), Directions.values()[j]);
			if (auxDoor != null &&!auxDoor.getTarget().isVisited()){
				this.game.executeCommand(comando);
				this.movshechos++;
			if ((this.map.getCurrentRoom() != this.map.getPreviousRoom()&&(auxDoor != null)&&(auxDoor.isOpen())/*)&& auxDoor.isInRoom(this.map.getCurrentRoom(), Directions.values()[j])*/)){
				this.game.getCurrentMap().setPreviousRoom(this.map.getPreviousRoom());
				terna = new Terna(comando,"GO "+Directions.values()[j].toString(), this.game);
				try {
					this.game.getPila().apila(terna);
				} catch (WrongCommandFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				recursivo();
				deshacer = new UndoCommand("UNDO",this.game);
				try {
					this.opciones.add(this.game.getPila().Cima());
				} catch (ExceptionPila e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.game.executeCommand(deshacer);
				this.movshechos--;
				this.map.setCurrentRoom(this.game.getCurrentRoom());
				this.map.setPreviousRoom(this.game.getCurrentMap().getPreviousRoom());
			}}
		}
		ArrayList<Item> auxiliar = new ArrayList();
		Iterator<Item> iterator = this.getInventory().values().iterator();
		while (iterator.hasNext()) {
			auxiliar.add(iterator.next());
		}
		for (k = 0;(k<this.getInventory().size());k++){
			
			String id = auxiliar.get(k).getId();
			UseCommand comandoUse = new UseCommand("USE",this,auxiliar.get(k).getId(),this.game);
			
			Item prueba = this.getInventory().get(id.toUpperCase());
			this.game.executeCommand(comandoUse);
			this.movshechos++;
			
			this.setHealth(this.game.getJug().getHealth());
			this.setPoints(this.game.getJug().getPoints());
			this.setInventory(this.game.getJug().getInventory());
			terna = new Terna(comandoUse,"USE "+id, this.game);
			
			try {
				this.game.getPila().apila(terna);
			} catch (WrongCommandFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			recursivo();
			deshacer = new UndoCommand("UNDO",this.game);
			try {
				this.opciones.add(this.game.getPila().Cima());
			} catch (ExceptionPila e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.game.executeCommand(deshacer);
			this.movshechos--;
		}
				
	}

	ArrayList<Item> auxi = new ArrayList();
	Iterator<Item> it = this.getInventory().values().iterator();
	while (it.hasNext()) {
		auxi.add(it.next());
	}
	
	if (((this.map.getMapaPuertas().size()+1)*5)<this.getHealth()){
		for (int e = 0; e <this.game.getObjUsados().size();e++){
			if (this.game.getObjUsados().get(e) instanceof Food){
				ArrayList <String> nombres = new ArrayList();
				String nombre = this.game.getObjUsados().get(e).getId();
				int tamaño = this.game.getPila().getSize();
				for (int v = 0; v<tamaño;v++){
					try {
						this.opciones.add(this.game.getPila().Cima());
						this.game.getPila().desapila();
					} catch (ExceptionPila e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if ((v<auxi.size())&&!auxi.get(v).isUsed()){
						
						String nombre2 = auxi.get(v).getId();
						nombres.add(nombre2);
					}	
				}
								
				for(int l = 0; l<tamaño;l++){
					
					if (this.opciones.get(l).getCadena().contains(nombre)){
						this.opciones.remove(l);
						l--;
						tamaño--;
					}
					for (int ñ = 0; ñ<nombres.size();ñ++){
						if( this.opciones.get(l).getCadena().contains(nombres.get(ñ))){
							this.opciones.remove(l);
							l--;
							tamaño--;
						}
					}
				}
				
				
				
				}
		
	}
	
	}
	System.out.println("Los movimientos a realizar son los siguientes en orden inverso: ");
	for (Terna g: this.opciones){
		System.out.println(g.getCadena());
	}
	System.exit(1);
}	
			
		

	
	public void LetsPlay(){
		int i = 0;
		boolean encontrado =false;
		boolean solucion = false;
		Room auxiliar;
		Door door;
		String llave = "";
		while(i<this.movs){
			if (this.map.getCurrentRoom().isExit())
				break;
			else{
				
			encontrado = false;
			for (int u = 0; u <this.map.getCurrentRoom().getItems().size();u++){
				if ((this.map.getCurrentRoom().getItems().get(u) instanceof Key)){
					this.movimientos.add("Pick "+this.map.getCurrentRoom().getItems().get(u).getId());
					llave = this.map.getCurrentRoom().getItems().get(u).getId();
					this.map.getCurrentRoom().pickItem(this, this.map.getCurrentRoom().getItems().get(u).getId().toUpperCase());	
					i++;
				}
			
				if (((this.map.getMapaPuertas().size()+1)*5)>this.getHealth()){
					while(!this.map.getCurrentRoom().getItems().isEmpty()&&!solucion){
						if ((this.map.getCurrentRoom().getItems().get(u) instanceof Food && (((Food) this.map.getCurrentRoom().getItems().get(u)).getHealth())>0)){
							String name =this.map.getCurrentRoom().getItems().get(u).getId();
							this.movimientos.add("Pick "+this.map.getCurrentRoom().getItems().get(u).getId());
							this.map.getCurrentRoom().pickItem(this, this.map.getCurrentRoom().getItems().get(u).getId().toUpperCase());
							while(this.getInventory().get(name.toUpperCase()).canBeUsed() &&((this.map.getMapaPuertas().size()+1)*5)>this.getHealth()){
								this.movimientos.add("Use "+this.getInventory().get(name.toUpperCase()).getId());
								this.getInventory().get(name.toUpperCase()).use(this, this.game.getCurrentRoom());
								solucion = true;
							}
							i++;
						}
					}
				}
			}
			for(int j = 0; j<(Directions.values().length)&&!encontrado&&(Directions.values()[j]!= this.direction.getOposite());j++){
				auxiliar = this.map.getCurrentRoom();	
				door = this.map.getDoor(this.map.getCurrentRoom(), Directions.values()[j]);
				if (door != null){
					if(door.isOpen()){
						this.map.setCurrentRoom(door.getTarget());
						this.movimientos.add("Go "+Directions.values()[j]);
						i++;
					}
					else{
						door.open();
						this.movimientos.add("Use "+llave);
						i++;
						this.map.setCurrentRoom(door.getTarget());
						i++;
						this.movimientos.add("Go "+Directions.values()[j]);
					}
				
					this.direction = Directions.values()[j];
					if ((this.map.getCurrentRoom() !=auxiliar))
						encontrado = true;
				
				}
				else encontrado = false;
			
			}
		}
					
	}
	if (i>this.movs)	
		System.out.println("El número de movimientos introducidos no es suficiente para recorrer todo el laberinto");
	else if ((i<this.movs)&&(this.map.getCurrentRoom().isExit())){
			System.out.println("Los movimientos a realizar son:\n");
			for (int k = 0; k<this.movimientos.size();k++){
				System.out.println(this.movimientos.get(k));
			}
		}
		

	}
}

	

