package tp.pr5.items;

public abstract class PersistentItem extends Item {

	
	protected PersistentItem(String id, String description) {
		super(id, description);
		
	}

	public boolean canBeUsed(){
		return true;
	}
}
