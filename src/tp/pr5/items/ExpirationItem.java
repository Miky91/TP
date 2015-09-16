package tp.pr5.items;

public abstract class ExpirationItem extends Item {
	
	private int times;
	
	
	public ExpirationItem(String id, String description, int times) {
		super(id, description);
		this.times = times;
		
	}
	public int getTimes() {
		return times;
	}
	public String toString() {
		return "--item[" + this.id + "]=" + this.description + "//" + this.times;
	}
	
	protected void decrement()
	{
		this.times--;
	}
	protected void increment(){
		this.times++;
	}
	public boolean canBeUsed(){
		if (this.times >=1)
			return true;
		else
			return false;
		
	}
}
