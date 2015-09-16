package tp.pr5.items;

import tp.pr5.Player;
import tp.pr5.Room;

public abstract class Item{
protected String id;
protected String description;
protected boolean used;

public boolean isUsed() {
	return used;
}

protected Item(java.lang.String id, java.lang.String description){
		this.id = id;
		this.description = description;
		this.used = false;
		
	}

public java.lang.String getId() {
	return this.id;
}
public void setUsed(boolean used) {
	this.used = used;
}

public String getDescription(){
	return this.description;
}
public String toString() {
	return "--item[" + this.id + "]=" + this.description;
}

public abstract boolean canBeUsed();

public abstract boolean use(Player who, Room where);

public abstract boolean desuse(Player who, Room where);


}
