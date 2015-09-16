package tp.pr5.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.Game;
import tp.pr5.GameObserver;
import tp.pr5.MapObserver;
import tp.pr5.PlayerObserver;
import tp.pr5.RoomInfo;
import tp.pr5.items.Item;

/**
 * Panel en la parte inferior de la ventana que muestra mensajes sobre los
 * eventos que ocurren durante el juego. Este panel implementa todas las
 * interfaces de los observadores con el fin de ser notificado sobre todos los
 * acontecimientos que se producen cuando el juego se está ejecutando
 * 
 * @author Ivan Martin Herrero & Miguel de Andrés Herrero
 * 
 */
public class InfoPanel extends javax.swing.JPanel implements GameObserver,
		MapObserver, PlayerObserver {

	private static final long serialVersionUID = 1L;
	private JLabel infoLabel;
	private PlayerPanel playerPanel;
	private Game game;

	/**
	 * Contrusctor por defecto
	 * 
	 * @param playerPanel
	 */
	public InfoPanel(Game g) {
		super();
		this.game = g;
		this.playerPanel = g.getJug().getPlayerPanel();
		{
			FlowLayout PanelLabelLayout = new FlowLayout();
			this.setLayout(PanelLabelLayout);
			{
				infoLabel = new JLabel();
				this.add(infoLabel, new GridBagConstraints(0, 0, 4, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				infoLabel.setText("The game is starting now");
			}
		}

	}

	/*
	 * Inicio de los Getters & SEtters
	 */
	public JLabel getInfoLabel() {
		return infoLabel;
	}

	public void setInfoLabel(JLabel infoLabel) {
		this.infoLabel = infoLabel;
	}
	
	/*
	 * Inicio de los métodos públicos
	 */
	/**
	 * Notifica que el jugador ha pedido informacion de ayuda.
	 */
	public void gameHelp() {

	}

	/**
	 * Notifica que el juego ha finalizado y si el jugador ha ganado o ha
	 * perdido
	 * 
	 * @param win
	 */
	public void gameOver(boolean win) {
		if (!win) {		
			JOptionPane.showMessageDialog(null, Constants.MUERTO);
		}
		if (win){
			JOptionPane.showMessageDialog(null, Constants.SALIDA+"\n");

		}

	}

	/**
	 * Notifica que el juego empieza.
	 * 
	 * @param initialRoom
	 * @param playerPoints
	 * @param playerHealth
	 */
	public void gameStart(RoomInfo initialRoom, int playerPoints,
			int playerHealth) {
		this.infoLabel.setText("the new room is "+initialRoom.getName()+"   HEALTH: "+playerHealth+"  SCORE: "+playerPoints);

	}

	/**
	 * Notifica que el jugador quiere salir.
	 */
	public void gameQuit() {
		System.exit(1);
	}

	/**
	 * Notifica que el juego no puede ejecutar un comando.
	 * 
	 * @param msg
	 */
	public void gameError(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	/**
	 * Notifica que el inventario del jugador ha sido modificado
	 * @param inventory
	 */
	public void inventoryUpdate(List<Item> inventory) {
		this.infoLabel.setText(
				"The inventory now contains " + +inventory.size()
						+ " elements");
	}
	/**
	 * Notifica que un objeto está vacio.
	 * @param itemName
	 */
	public void itemEmpty(String itemName) {
		JOptionPane.showMessageDialog(null, "The " + itemName
				+ " is empty. It is deleted from the inventory.");

	}
	/**
	 * Notifica que el jugador quiere examinar un objeto
	 * @param description
	 */
	public void itemLooked(String description) {

	}
	/**
	 * Notifica que un objeto ha sido usado.
	 * @param itemName
	 */
	public void itemUsed(String itemName) {
		JOptionPane.showMessageDialog(null, "Something changes ...");
	}
	/**
	 * Notifica la muerte del jugador
	 */
	public void playerDead() {
		JOptionPane.showMessageDialog(null, Constants.MUERTO);
	}
	/**
	 * Notifica que el jugador ha mirado el inventario
	 * @param inventory
	 */
	public void playerLookedInventory(List<Item> inventory) {
			this.infoLabel.setText("You are looking your inventory");
	}
	/**
	 * Notifica que los atributos del jugador han cambiado.
	 * @param newHealth
	 * @param newScore
	 */
	public void playerUpdate(int newHealth, int newScore) {
		this.playerPanel = this.game.getJug().getPlayerPanel();
		this.playerPanel.playerUpdate(newHealth, newScore);
		this.infoLabel.setText("Player atributes has been updated ("
				+ newHealth + "," + newScore+")");
	}
	/**
	 * Notifica que el jugador ha examinado una habitación
	 * 
	 * @param r
	 */
	public void playerHasExaminedRoom(RoomInfo r) {

	}
	/**
	 * Notifica que el jugador ha entrado en una habitación desde la direccion
	 * direction
	 * 
	 * @param direction
	 * @param targetRoom
	 */
	public void roomEntered(Directions direction, RoomInfo targetRoom) {
		int auxI = this.game.getCurrentMap().getMapPanel().getI();
		int auxJ = this.game.getCurrentMap().getMapPanel().getJ();
		this.game.getCurrentMap().getMapPanel().getCelda(auxI, auxJ).setVisited(false);
		this.game.getCurrentMap().getMapPanel().getCelda(auxI, auxJ).left();
		this.game.getCurrentMap().getMapPanel().setNewRoom(direction);
		auxI = this.game.getCurrentMap().getMapPanel().getI();
		auxJ = this.game.getCurrentMap().getMapPanel().getJ();
		this.game.getCurrentMap().getMapPanel().setMapa(this.game.getCurrentMap().getMapPanel().getMapa()[auxI][auxJ],this.game.getCurrentMap().getCurrentRoom());
		
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}

	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
	}


}
