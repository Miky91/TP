package tp.pr5.items;

import tp.pr5.Door;
import tp.pr5.Player;
import tp.pr5.Room;
public class Key extends PersistentItem{


	private Door door;
	

	public Key(java.lang.String id,java.lang.String description,Door door){
		super(id,description);
		this.door=door;
		
	}
	
	public boolean use(Player who,Room where){
		if(where != null && this.door != null && this.door.isInRoom(where)){
			if (this.door.isOpen()){
				this.door.close();
				return true;
			}
			else{
				this.door.open();
				return true;
			}
				
		}
		else
			return false;
	}

	public Door getDoor(){
		return this.door;
	}

	@Override
	public boolean desuse(Player who, Room where) {
		// TODO Auto-generated method stub
		if(where != null && this.door != null && this.door.isInRoom(where)){
			if (this.door.isOpen()){
				this.door.close();
				return true;
			}
			else{
				this.door.open();
				return true;
			}
				
		}
		else
			return false;
	}


	
}
