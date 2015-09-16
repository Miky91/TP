package tp.pr5.items;

import tp.pr5.Player;
import tp.pr5.Room;

public class Valuable extends ExpirationItem {
	

	private int score;
	
	public Valuable(java.lang.String id,java.lang.String description,int score){
		super(id, description,1);
		this.score=score;
	}
	public boolean use(Player who,Room where){
		if(canBeUsed()){
			who.addPoints(this.score);
			who.requestPlayerUpdate();
			decrement();
			return true;
		}
		else
			return false;
		
	}
	@Override
	public boolean desuse(Player who, Room where) {
		// TODO Auto-generated method stub
		who.addPoints(-this.score);
		increment();
		return true;
	}
	

}