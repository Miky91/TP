package tp.pr5.gui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;


import javax.swing.BorderFactory;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.Game;
import tp.pr5.MapObserver;
import tp.pr5.Room;
import tp.pr5.RoomInfo;




/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MapPanel implements MapObserver {

	private JPanel MapPanel;
	private JPanel ConsolePanel;
	private JTextArea DescriptionRoom;
	private JPanel auxPanel;
	private Room Room;
	private MapCell [][] Mapa = new MapCell[11][11];
	private int i = 5;
	private int j = 5;
	private MapCell mapCell;
	private PlayerPanel playerPanel;
	private Game game;
	private InfoPanel infoPanel;
	
	
	//private JScrollBar DescriptionRoomBarr;
	public MapPanel(Room CurrentRoom,Game g){
		this.game = g;
		auxPanel = new JPanel();
		GridBagLayout auxPanelLayout = new GridBagLayout();
		auxPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
		auxPanelLayout.rowHeights = new int[] {7, 7, 7, 7};
		auxPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
		auxPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
		auxPanel.setLayout(auxPanelLayout);
		auxPanel.setPreferredSize(new java.awt.Dimension(467, 404));

		{
			ConsolePanel = new JPanel();
			GridBagLayout ConsolePanelLayout = new GridBagLayout();
			auxPanel.add(ConsolePanel, new GridBagConstraints(0, 3, 5, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			Border borde = BorderFactory.createLineBorder(Constants.Colores.AZUL);
			String titulo = "Room";
			TitledBorder title = BorderFactory.createTitledBorder(borde,titulo);
			title.setTitleJustification(TitledBorder.LEFT);
			ConsolePanel.setBorder(title);
			ConsolePanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			ConsolePanelLayout.rowHeights = new int[] {30, 30, 30, 7};
			ConsolePanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			ConsolePanelLayout.columnWidths = new int[] {7, 7, 7, 7};
			ConsolePanel.setLayout(ConsolePanelLayout);
			{
				DescriptionRoom = new JTextArea();
				ConsolePanel.add(DescriptionRoom);
				
				DescriptionRoom.setPreferredSize(new java.awt.Dimension(1300, 80));
				JScrollPane DescriptionRoomBarr = new JScrollPane(DescriptionRoom,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				ConsolePanel.add(DescriptionRoomBarr, new GridBagConstraints(0, 0, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				infoPanel = new InfoPanel(this.game);
				ConsolePanel.add(infoPanel, new GridBagConstraints(0, 3, 5, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				
			}
		}

		{
			MapPanel = new JPanel();
			GridLayout MapPanelLayout = new GridLayout(11, 11);
			MapPanelLayout.setHgap(5);
			MapPanelLayout.setVgap(5);
			MapPanelLayout.setColumns(11);
			MapPanel.setLayout(MapPanelLayout);
			auxPanel.add(MapPanel, new GridBagConstraints(0, 0, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			Border borde = BorderFactory.createLineBorder(Constants.Colores.AZUL);
			String titulo = "Mapa";
			TitledBorder title = BorderFactory.createTitledBorder(borde,titulo);
			title.setTitleJustification(TitledBorder.LEFT);
			MapPanel.setBorder(title);
			
			for (int i = 0; i <11; i++){
				for (int j = 0; j <11;j++){
					
				//	private JScrollBar DescriptionRoomBarr;
					// final MapCell MapCell;
					if ((i == 5) && (j == 5)){
						mapCell = new MapCell(CurrentRoom, DescriptionRoom);
						Room = CurrentRoom;
						DescriptionRoom.setText(CurrentRoom.getDescription());
					}else
						mapCell = new MapCell(null, DescriptionRoom);
					mapCell.i = i;
					mapCell.j = j;
					MapPanel.add(mapCell);
					Mapa[i][j]= mapCell;
					
				}
			
		
		}
		
		}
				
}
	


	public InfoPanel getInfoPanel() {
		return this.infoPanel;
	}


	public void setInfoPanel(InfoPanel infoPanel) {
	}


	public MapCell[][] getMapa() {
		return Mapa;
	}
	



public int getI() {
		return i;
	}


	public void setI(int i) {
		this.i = i;
	}


	public int getJ() {
		return j;
	}


	public void setJ(int j) {
		this.j = j;
	}




	public Room getRoom() {
		return Room;
	}


	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}



	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
	}



	public void setRoom(Room room) {
		Room = room;
	}


	public JPanel getConsolePanel() {
		return ConsolePanel;
	}


	public void setConsolePanel(JPanel consolePanel) {
		ConsolePanel = consolePanel;
	}


	public Game getGame() {
		return game;
	}



	public void setGame(Game game) {
		this.game = game;
	}



	public JTextArea getDescriptionRoom() {
		return DescriptionRoom;
	}


	public void setDescriptionRoom(JTextArea descriptionRoom) {
		DescriptionRoom = descriptionRoom;
	}


	protected void MapCellActionPerformed(ActionEvent evt, MapCell mapCell) {
		if (mapCell.getRoomButton()!=null)
			DescriptionRoom.setText(mapCell.getRoomButton().getDescription());
			mapCell.setBackground(Constants.Colores.GRIS);
			//System.out.println(mapCell.i + " - " + mapCell.j);
	}

public MapCell getCelda(int i, int j){
	return this.Mapa[i][j];
}

public void setCelda(MapCell celda, int i, int j){
	
	this.Mapa[i][j] = celda;
	
}

	
public void setNewRoom(Directions direction/*, Room room*/){
		
		if(direction==Directions.NORTH && this.i !=0)
			this.i--;
		
		if(direction==Directions.SOUTH && this.i!=10)
			this.i++;
		
		if(direction==Directions.EAST && this.j!=10)
			this.j++;
			
		if(direction==Directions.WEST && this.j!=0)
			this.j--;
		
		
	}
	
	public JPanel getMapPanel() {
		return this.auxPanel;
	}
	
	public void setMapa(MapCell celda, Room currentRoom) {
		celda.setRoomButton(currentRoom);
		celda.setVisited(true);
		celda.setText(currentRoom.getNombre());
		celda.setColor(currentRoom);
		celda.repaint();
	
		
	}
	public void eliminateCell(MapCell celda){
		celda.setRoomButton(null);
		celda.setVisited(false);
		celda.setText("");
		celda.setColor(null);
		celda.repaint();
	}


	@Override
	public void playerHasExaminedRoom(RoomInfo r) {
		this.DescriptionRoom.setText(r.getDescription());
	}


	@Override
	public void roomEntered(Directions direction, RoomInfo targetRoom) {
		this.infoPanel.setGame(this.game);
		this.infoPanel.roomEntered(direction, targetRoom);
		this.mapCell.enter(targetRoom);
		this.mapCell.left();
		JOptionPane.showMessageDialog(null, "...moving to "+direction.name());
		this.DescriptionRoom.setText(targetRoom.getDescription());
	}
}
	
	