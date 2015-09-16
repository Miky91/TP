package tp.pr5.maploader;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.Door;
import tp.pr5.Map;
import tp.pr5.Room;
import tp.pr5.items.Food;
import tp.pr5.items.Key;
import tp.pr5.items.Valuable;
import tp.pr5.maploader.exceptions.WrongMapFormatException;

public class MapLoaderFromTxtFile {
	
	StreamTokenizer Streamtk;
	InputStream entrada;
	Map map;
	Room room;
	Door door;
	ArrayList<Room> habitaciones;
	ArrayList<Door> puertas;
	String e = Constants.MAPEXCEPTION;
	
	    public MapLoaderFromTxtFile() {
	}


    public Map loadMap(java.io.InputStream file) throws java.io.IOException{
		String descripcion = "";
    	this.habitaciones = new ArrayList<Room>();
    	this.puertas = new ArrayList<Door>();
    	entrada = file;
    	Streamtk = new StreamTokenizer(new BufferedReader(new InputStreamReader(file)));
    	Streamtk.nextToken();
    	comprobarTokenPalabra(Streamtk);
    	if (comprobarTokenPalabra(Streamtk).equalsIgnoreCase("BeginMap")){
    		Streamtk.nextToken();
    		while(!comprobarTokenPalabra(Streamtk).equalsIgnoreCase("EndMap") && Streamtk.ttype != StreamTokenizer.TT_EOF){
    			llenarArrayHabitaciones(Streamtk,descripcion);
    			this.map = new Map(this.habitaciones.get(0));
    			Streamtk.nextToken();
    			llenarArrayPuertas(Streamtk);
    			Streamtk.nextToken();
    			llenarArrayItems(Streamtk);
    		}
    		
    		}else
    			throw new WrongMapFormatException();
    		
    		return map;
    		}
    
    private void llenarArrayItems(StreamTokenizer tk) throws IOException{
    	int i = -1;
		if(comprobarTokenPalabra(tk).equalsIgnoreCase("BeginItems")){
			Streamtk.nextToken();
			while(!comprobarTokenPalabra(Streamtk).equalsIgnoreCase("EndItems")){	
				i++;
				addItems(Streamtk,i);
		}
	tk.nextToken();

	}
		else
			throw new WrongMapFormatException();
		}	
    	
	private void llenarArrayPuertas(StreamTokenizer tk) throws IOException{
		if(comprobarTokenPalabra(tk).equalsIgnoreCase("BeginDoors")){
			Streamtk.nextToken();
			while(!comprobarTokenPalabra(Streamtk).equalsIgnoreCase("EndDoors")){	
				addDoors(Streamtk);
		}

	}
		else
			throw new WrongMapFormatException();
		}	
    	
		
	private void llenarArrayHabitaciones(StreamTokenizer tk, String descripcion) throws IOException{
		if(comprobarTokenPalabra(tk).equalsIgnoreCase("BeginRooms")){
			Streamtk.nextToken();
			while(!comprobarTokenPalabra(Streamtk).equalsIgnoreCase("EndRooms")){	
				addRooms(Streamtk,descripcion);
		}

	}
		else
			throw new WrongMapFormatException();
		}
	
   private String descrip(StreamTokenizer tk) throws IOException{
	   String descripcion = "";
	   Boolean puerta = false;
		while(tk.ttype!= -2 && !puerta){
			if(tk.ttype == 95)
				tk.nextToken();
			else{
				if (tk.ttype == 44){
					descripcion = descripcion +  ",";	
					tk.nextToken();
				}
				else{
					if(comprobarTokenPalabra(tk).equalsIgnoreCase("door")){
						puerta = true;
					}
					else{
						descripcion = descripcion +" "+ tk.sval;	
						tk.nextToken();
					}
				}
			}
			}
		return descripcion.substring(1);
   }
    private void addItems(StreamTokenizer tk,int i) throws IOException {
    	String descripcion ="";
    	String id ="";
    	int health,times,indice,indiceh;
    	if(tk.sval.equalsIgnoreCase("food")){
			tk.nextToken();
			if (comprobarTokenNumero(tk) == i){
				tk.nextToken();
				i++;
			}
			else
				throw new WrongMapFormatException();
			id = tk.sval;
			tk.nextToken();
			descripcion =descrip(tk);
			health = comprobarTokenNumero(tk);
			tk.nextToken();
				times = comprobarTokenNumero(tk);
			tk.nextToken();
			
			indice = leerHab();
			Food it = new Food(id,descripcion,health,times);
			if(indice < this.habitaciones.size()){
			this.habitaciones.get(indice).addItem(it);
			tk.nextToken();
			}
			else
				throw new WrongMapFormatException();
			
	}else
		if (comprobarTokenPalabra(tk).equalsIgnoreCase("key")){
			tk.nextToken();
			if (comprobarTokenNumero(tk) == i){
				tk.nextToken();
			}
			else
				throw new WrongMapFormatException();
			id = tk.sval;
			tk.nextToken();
			descripcion = descrip(tk);
			indice = leerPuerta();
				tk.nextToken();
				indiceh = leerHab();
			if(indice < this.map.getSize()&& indiceh< this.habitaciones.size()){
			Key it = new Key(id,descripcion,this.map.getDoorArray(indice));
			this.habitaciones.get(indiceh).addItem(it);
			tk.nextToken();
			}
			else
				throw new WrongMapFormatException();
		}else
			if(comprobarTokenPalabra(tk).equalsIgnoreCase("valuable")){
				tk.nextToken();
				if (comprobarTokenNumero(tk) == i){
					tk.nextToken();
				}
				else
					throw new WrongMapFormatException();
				id = tk.sval;
				tk.nextToken();
				descripcion = descrip(tk);
					health = comprobarTokenNumero(tk);				
				tk.nextToken();
				indiceh = leerHab();
					Valuable it = new Valuable(id,descripcion,health);
					if(indiceh<this.habitaciones.size()){
					this.habitaciones.get(indiceh).addItem(it);
					tk.nextToken();
					}
					else
						throw new WrongMapFormatException();
				
			}else
				throw new WrongMapFormatException();
    }

	private void addDoors(StreamTokenizer tk) throws IOException {
    	boolean bi = false;
    	boolean abierto = false;
    	int indice1= 0;
    	int indice2 =-1;
    	if(tk.sval.equalsIgnoreCase("door")){
			tk.nextToken();
			if (comprobarTokenNumero(tk) == this.map.getSize()){
				tk.nextToken();
				if (comprobarTokenPalabra(tk).equalsIgnoreCase("Bidirectional")){
					bi = true;
				}
				tk.nextToken();
				indice1 = leerHab();
				Streamtk.nextToken();
				Directions direccion = Directions.UNKNOWN;
				Directions[] dir = Directions.values();
				if(!(Streamtk.ttype ==-1)){
				for(int j = 0; j< dir.length;j++){
					if(Streamtk.sval.equalsIgnoreCase(dir[j].toString()))
						direccion = dir[j];
				}
			}else
				throw new WrongMapFormatException();
				Streamtk.nextToken();
				indice2 = leerHab();
				Streamtk.nextToken();
				if(!(Streamtk.ttype == -1)){
				if(comprobarTokenPalabra(tk).equalsIgnoreCase("open"))
					abierto = true;
				}else
					throw new WrongMapFormatException();
				if((indice1<this.habitaciones.size() && indice2 < this.habitaciones.size())){
				Door d= new Door(this.habitaciones.get(indice1),direccion,this.habitaciones.get(indice2),bi,abierto);
				this.map.addDoor(d);
				}
				else
					throw new WrongMapFormatException();
				
				Streamtk.nextToken();
			}
			else 
				throw new WrongMapFormatException();
			}
			else 
				throw new WrongMapFormatException();
    	
	}
	
	private int leerPuerta() throws IOException,
	WrongMapFormatException {
			if(comprobarTokenPalabra(Streamtk).equalsIgnoreCase("door")){
				Streamtk.nextToken();
				return comprobarTokenNumero(Streamtk);
			}
				else
					throw new WrongMapFormatException();
} 

	private int leerHab() throws IOException,
			WrongMapFormatException {
		if(comprobarTokenPalabra(Streamtk).equalsIgnoreCase("Room")){
			Streamtk.nextToken();
			return comprobarTokenNumero(Streamtk);
		}
			else
				throw new WrongMapFormatException();
		
	} 


	protected void addRooms(StreamTokenizer tk, String descripcion) throws IOException  {
		IndexOutOfBoundsException e = new IndexOutOfBoundsException("Error in line " + Streamtk.lineno()+": " + this.habitaciones.size()+ 
				" expected");
    	boolean salida = false;
    	if(tk.sval.equalsIgnoreCase("room")){
			tk.nextToken();
			if (comprobarTokenNumero(tk) == this.habitaciones.size()){
				tk.nextToken();
				String nombre = tk.sval;
				tk.nextToken();
				if (!(tk.ttype ==-1) && !(tk.sval.equalsIgnoreCase("") )){
				while(!tk.sval.equalsIgnoreCase("noexit") && !tk.sval.equalsIgnoreCase("exit") && tk.ttype!=95){
					descripcion = descripcion + tk.sval + " ";
					tk.nextToken();
					if (tk.ttype== 34)
						tk.sval = "noexit";
					if(tk.ttype ==95)
						tk.nextToken();
					if (tk.ttype == 33){
						descripcion = descripcion + "! ";
						tk.nextToken();
					}
						
					if ((tk.ttype ==-1) || (tk.sval.equalsIgnoreCase("") )){
						throw new WrongMapFormatException();
					}
				
				}
				if(tk.sval.equalsIgnoreCase("noexit")){
					salida = false;
					tk.nextToken();
					
				}
				else{
					salida = true;
					tk.nextToken();
					
				}
				
				String[] des = descripcion.split("_");
				descripcion = "";
				for (int j = 0; j < des.length;j++ )
				descripcion= descripcion +  des[j] + " ";
				room = new Room(salida,descripcion,nombre);
    			this.habitaciones.add(room);
		
			}
			else 
				throw new WrongMapFormatException(e);
			}
			else 
				throw new WrongMapFormatException(e);
    	}
    	else
    		throw new WrongMapFormatException(e);
    }
    

  public int comprobarTokenNumero(StreamTokenizer tk) throws WrongMapFormatException{
	  
		   if(tk.ttype!=95)
		    if(tk.ttype == StreamTokenizer.TT_NUMBER){
		    	int d = (int) tk.nval;
		    	return d;
		    }
		    else
		    	throw new WrongMapFormatException();
		   else
		    	throw new WrongMapFormatException();
	   }
public String comprobarTokenPalabra(StreamTokenizer tk) throws WrongMapFormatException{
	  
	   if(tk.ttype!=95)
	    if(tk.ttype == StreamTokenizer.TT_WORD || tk.ttype ==34){
	    	return tk.sval;
	    }
	    else
	    	throw new WrongMapFormatException();
	   else
	    	throw new WrongMapFormatException();
}
}
    
    
