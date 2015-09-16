package tp.pr5.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;

import tp.pr5.Constants;
import tp.pr5.Room;
import tp.pr5.RoomInfo;

public class MapCell extends JButton{

	

	private static final long serialVersionUID = 1L;
private Room roomButton;
private boolean isVisited;
public int i,j;
private JTextArea description;
	
	
	public MapCell(Room room, JTextArea DescriptionRoom){
		this.roomButton = room;
		this.description = DescriptionRoom;
		if (room == null) {
			this.isVisited = false;
		} else {
			this.isVisited = true;
			this.setText(roomButton.getNombre());
			this.setBackground(Constants.Colores.VERDE);
		}
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MapCellActionPerformed(evt);
			}
		});
	}


	public Room getRoomButton() {
		return roomButton;
	}


	public void setRoomButton(Room roomButton) {
		this.roomButton = roomButton;
	}


	public boolean isVisited() {
		return isVisited;
	}

	public void setColor(Room roomButton){
		if (this.isVisited)
			this.setBackground(Constants.Colores.VERDE);
		else
		if (!this.isVisited && roomButton != null)
			this.setBackground(Constants.Colores.GRIS);
		else
			this.setBackground(null);
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	protected void MapCellActionPerformed(ActionEvent evt) {
		if (this.getRoomButton()!=null)
			
			description.setText(roomButton.getDescription());
			//System.out.println(this.i + " - " + this.j);
	}
	
	public void setDescription(JTextArea DescriptionRoom) {
		this.description = DescriptionRoom;
	}
	
	public void enter(RoomInfo targetRoom){
		this.description.setText(targetRoom.getDescription());
		this.setColor(roomButton);
	}
	
	public void left(){
		
		this.setColor(roomButton);
	}
	
}
