package tp.pr5.gui;
import java.awt.BorderLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.GameObserver;

import tp.pr5.RoomInfo;
import tp.pr5.Terna;
import tp.pr5.commands.Command;

import tp.pr5.commands.GoCommand;
import tp.pr5.commands.UndoCommand;

import tp.pr5.commands.exceptions.WrongCommandFormatException;

import tp.pr5.items.Item;




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
public class MainWindow extends javax.swing.JFrame  implements GameObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar MenuBarr;
	private JMenu File;
	private JMenu jMenu;
	private JPanel PrincipalPanel;
	private JLabel HealthLabel;
	private JPanel InventoryPanel;
	private JTextField InventoryItem;
	private PlayerPanel playerPanel2;
	private PlayerPanel playerPanel1;
	private JPanel jPanel1;
	private JPanel Panel;
	private JTextArea DescriptionRoom;
	private JButton UndoButton;
	private JButton UseButton;
	private JComboBox Directions;
	private JButton QuitButton;
	private JButton DropButton;
	private JButton PickButton;
	private JButton GoButton;
	private JLabel ScoreValue;
	private JTable Inventario;
	private JLabel ScoreLabel;
	private JLabel HealthValor;
	private JPanel HealthPlayer;
	private JPanel ConsolePanel;
	private tp.pr5.gui.MapPanel MapPanel;
	private JPanel CommandPanel;
	private JMenuItem jMenuItem1;
	private tp.pr5.gui.PlayerPanel PlayerPanel;
	private MapPanel auxPanel;
	private String[] direccion = new String[] { "NORTH", "SOUTH", "EAST", "WEST" };
	private static Game game;
	private Command command;
	private GameControllerGUI controlador;
	
	public JMenuBar getMenuBarr() {
		return MenuBarr;
	}

	public void setMenuBarr(JMenuBar menuBarr) {
		MenuBarr = menuBarr;
	}

	public JMenu getFile() {
		return File;
	}

	public void setFile(JMenu file) {
		File = file;
	}

	public JMenu getjMenu() {
		return jMenu;
	}

	public void setjMenu(JMenu jMenu) {
		this.jMenu = jMenu;
	}

	public JPanel getPrincipalPanel() {
		return PrincipalPanel;
	}

	public void setPrincipalPanel(JPanel principalPanel) {
		PrincipalPanel = principalPanel;
	}

	public JLabel getHealthLabel() {
		return HealthLabel;
	}

	public void setHealthLabel(JLabel healthLabel) {
		HealthLabel = healthLabel;
	}

	public JPanel getInventoryPanel() {
		return InventoryPanel;
	}

	public void setInventoryPanel(JPanel inventoryPanel) {
		InventoryPanel = inventoryPanel;
	}

	public JTextField getInventoryItem() {
		return InventoryItem;
	}

	public void setInventoryItem(JTextField inventoryItem) {
		InventoryItem = inventoryItem;
	}

	public PlayerPanel getPlayerPanel2() {
		return playerPanel2;
	}

	public void setPlayerPanel2(PlayerPanel playerPanel2) {
		this.playerPanel2 = playerPanel2;
	}

	public PlayerPanel getPlayerPanel1() {
		return playerPanel1;
	}

	public void setPlayerPanel1(PlayerPanel playerPanel1) {
		this.playerPanel1 = playerPanel1;
	}

	public JPanel getjPanel1() {
		return jPanel1;
	}

	public void setjPanel1(JPanel jPanel1) {
		this.jPanel1 = jPanel1;
	}

	public JPanel getPanel() {
		return Panel;
	}

	public void setPanel(JPanel panel) {
		Panel = panel;
	}

	public JTextArea getDescriptionRoom() {
		return DescriptionRoom;
	}

	public void setDescriptionRoom(JTextArea descriptionRoom) {
		DescriptionRoom = descriptionRoom;
	}

	public JButton getUndoButton() {
		return UndoButton;
	}

	public void setUndoButton(JButton undoButton) {
		UndoButton = undoButton;
	}

	public JButton getUseButton() {
		return UseButton;
	}

	public void setUseButton(JButton useButton) {
		UseButton = useButton;
	}

	public JComboBox getDirections() {
		return Directions;
	}

	public void setDirections(JComboBox directions) {
		Directions = directions;
	}

	public JButton getQuitButton() {
		return QuitButton;
	}

	public void setQuitButton(JButton quitButton) {
		QuitButton = quitButton;
	}

	public JButton getDropButton() {
		return DropButton;
	}

	public void setDropButton(JButton dropButton) {
		DropButton = dropButton;
	}

	public JButton getPickButton() {
		return PickButton;
	}

	public void setPickButton(JButton pickButton) {
		PickButton = pickButton;
	}

	public JButton getGoButton() {
		return GoButton;
	}

	public void setGoButton(JButton goButton) {
		GoButton = goButton;
	}

	public JLabel getScoreValue() {
		return ScoreValue;
	}

	public void setScoreValue(JLabel scoreValue) {
		ScoreValue = scoreValue;
	}

	public JTable getInventario() {
		return Inventario;
	}

	public void setInventario(JTable inventario) {
		Inventario = inventario;
	}

	public JLabel getScoreLabel() {
		return ScoreLabel;
	}

	public void setScoreLabel(JLabel scoreLabel) {
		ScoreLabel = scoreLabel;
	}

	public JLabel getHealthValor() {
		return HealthValor;
	}

	public void setHealthValor(JLabel healthValor) {
		HealthValor = healthValor;
	}

	public JPanel getHealthPlayer() {
		return HealthPlayer;
	}

	public void setHealthPlayer(JPanel healthPlayer) {
		HealthPlayer = healthPlayer;
	}

	public JPanel getConsolePanel() {
		return ConsolePanel;
	}

	public void setConsolePanel(JPanel consolePanel) {
		ConsolePanel = consolePanel;
	}

	public tp.pr5.gui.MapPanel getMapPanel() {
		return MapPanel;
	}

	public void setMapPanel(tp.pr5.gui.MapPanel mapPanel) {
		MapPanel = mapPanel;
	}

	public JPanel getCommandPanel() {
		return CommandPanel;
	}

	public void setCommandPanel(JPanel commandPanel) {
		CommandPanel = commandPanel;
	}

	public JMenuItem getjMenuItem1() {
		return jMenuItem1;
	}

	public void setjMenuItem1(JMenuItem jMenuItem1) {
		this.jMenuItem1 = jMenuItem1;
	}

	public tp.pr5.gui.PlayerPanel getPlayerPanel() {
		return PlayerPanel;
	}

	public void setPlayerPanel(tp.pr5.gui.PlayerPanel playerPanel) {
		PlayerPanel = playerPanel;
	}

	public MapPanel getAuxPanel() {
		return auxPanel;
	}

	public void setAuxPanel(MapPanel auxPanel) {
		this.auxPanel = auxPanel;
	}

	public String[] getDireccion() {
		return direccion;
	}

	public void setDireccion(String[] direccion) {
		this.direccion = direccion;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public GameControllerGUI getControlador() {
		return controlador;
	}

	public void setControlador(GameControllerGUI controlador) {
		this.controlador = controlador;
	}

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				MainWindow inst = new MainWindow(game);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public MainWindow(Game game) {
		super();
		MainWindow.game = game;
		this.controlador = new GameControllerGUI(MainWindow.game);
		initGUI();
		
	}
	
	private void initGUI() {
		try {MainWindow.game.createPila();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				PrincipalPanel = new JPanel();
				GridBagLayout PrincipalPanelLayout = new GridBagLayout();
				getContentPane().add(PrincipalPanel, BorderLayout.CENTER);
				PrincipalPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				PrincipalPanelLayout.rowHeights = new int[] {7, 7, 7, 7};
				PrincipalPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				PrincipalPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
				PrincipalPanel.setLayout(PrincipalPanelLayout);
				PrincipalPanel.setPreferredSize(new java.awt.Dimension(1024, 768));
				{
					CommandPanel = new JPanel();
					GridLayout CommandPanelLayout = new GridLayout(4, 2);
					CommandPanelLayout.setHgap(5);
					CommandPanelLayout.setVgap(5);
					CommandPanelLayout.setColumns(2);
					CommandPanel.setLayout(CommandPanelLayout);
					PrincipalPanel.add(CommandPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 0, 0, 0), 0, 0));
					CommandPanel.setSize(10, 10);
					Border borde = BorderFactory.createLineBorder(Constants.Colores.AZUL);
					String titulo = "Actions";
					TitledBorder title = BorderFactory.createTitledBorder(borde,titulo);
					title.setTitleJustification(TitledBorder.LEFT);
					CommandPanel.setBorder(title);
					{
						GoButton = new JButton();
					CommandPanel.add(GoButton);
						GoButton.setText("GO");
						
						GoButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								GoButtonActionPerformed(evt);
							}
						});
					}
					{
						ComboBoxModel DirectionsModel = 
								new DefaultComboBoxModel(
										this.direccion);
						Directions = new JComboBox();

						CommandPanel.add(Directions);
						Directions.setModel(DirectionsModel);
					}
					{
						PickButton = new JButton();
						CommandPanel.add(PickButton);
						PickButton.setText("PICK");
						PickButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								PickButtonActionPerformed(evt);
							}


						});
					}
					{
						InventoryItem = new JTextField();
						CommandPanel.add(InventoryItem);
					}
					{
						UseButton = new JButton();
						CommandPanel.add(UseButton);
						UseButton.setText("USE");
						UseButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								UseButtonActionPerformed(evt);
							}
						});
					}
					{
						DropButton = new JButton();
						CommandPanel.add(DropButton);
						DropButton.setText("DROP");
						DropButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								DropButtonActionPerformed(evt);
							}
						});
					}
					{
						QuitButton = new JButton();
						CommandPanel.add(QuitButton);
						QuitButton.setText("QUIT");
						QuitButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								QuitButtonActionPerformed(evt);
							}
						});
					}
					{
						UndoButton = new JButton();
						CommandPanel.add(UndoButton);
						UndoButton.setText("UNDO");
						UndoButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								UndoButtonActionPerformed(evt);
							}
						});
					}
				}
				

				{
					PlayerPanel = new PlayerPanel(InventoryItem);
					PrincipalPanel.add(PlayerPanel.getPlayerPanel(), new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					MainWindow.game.getPlayer().setPlayerPanel(PlayerPanel);
					{
						MapPanel = new MapPanel(MainWindow.game.getCurrentRoom(),MainWindow.game);
						PrincipalPanel.add(MapPanel.getMapPanel(), new GridBagConstraints(0, 1, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						MainWindow.game.getCurrentMap().setMapPanel(MapPanel);
					}
				
				
			{
				MenuBarr = new JMenuBar();
				setJMenuBar(MenuBarr);
				{
					File = new JMenu();
					MenuBarr.add(File);
					File.setText("File");
					{
						jMenu = new JMenu();
						File.add(jMenu);
						jMenu.setText("Menu");
						{
							jMenuItem1 = new JMenuItem();
							jMenu.add(jMenuItem1);
							jMenuItem1.setText("Exit");
							jMenuItem1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									jMenuItem1ActionPerformed(evt);
								}
							});
						}
					}
				}
			}
			pack();
			this.setSize(533, 406);
		}}} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	
	}	
	
	
	
	
	protected void PickButtonActionPerformed(ActionEvent evt) {
		//System.out.println("PickButton.actionPerformed, event="+evt);
		String object = InventoryItem.getText();
		this.controlador.executePickAction(object);
		
	}

	private void GoButtonActionPerformed(ActionEvent evt) {
		//System.out.println("GoButton.actionPerformed, event="+evt);
		int indice = Directions.getSelectedIndex();
		String dir = this.direccion[indice];
		String aux = "GO"+" "+dir;
		command = new GoCommand("GO",dir,MainWindow.game,this.MapPanel,this.PlayerPanel);
		Terna terna = new Terna(command,aux,MainWindow.game );
		try {
			MainWindow.game.getPila().apila(terna);
		} catch (WrongCommandFormatException e) {
			e.printStackTrace();
		}
		MainWindow.game.executeCommand(command);
		this.PlayerPanel.playerUpdate(MainWindow.game.getPlayer().getHealth(),MainWindow.game.getPlayer().getPoints());
	}
	
	public void setGame(Game game){
		MainWindow.game = game;
	}
	
	public Game getGame(){
		return MainWindow.game;
	}
	
	private void UseButtonActionPerformed(ActionEvent evt) {
		//System.out.println("UseButton.actionPerformed, event="+evt);
		String object = InventoryItem.getText();
		this.controlador.executeUseAction(object);
		
	}
	
	
	private void DropButtonActionPerformed(ActionEvent evt) {
		
		
			InventoryItem.setText(this.PlayerPanel.getSelectedItem());	
			//System.out.println("DropButton.actionPerformed, event="+evt);
			String item = InventoryItem.getText();
			this.controlador.executeDropAction(item);
	
		
	}
	
	private void QuitButtonActionPerformed(ActionEvent evt) {
		this.controlador.executeQuitAction();

	}
	
	private void UndoButtonActionPerformed(ActionEvent evt) {
		//System.out.println("UndoButton.actionPerformed, event="+evt);
		
		command = new UndoCommand("UNDO", MainWindow.game);
		MainWindow.game.executeCommand(command);
		ArrayList <Item> inventory = new ArrayList<Item>();
		Iterator<Item> iterator = (MainWindow.game.getPlayer()).getInventory().values().iterator();
		while (iterator.hasNext()) {
			inventory.add(iterator.next());
		}
		this.PlayerPanel.inventoryUpdate(inventory);
		this.PlayerPanel.playerUpdate(MainWindow.game.getPlayer().getHealth(),MainWindow.game.getPlayer().getPoints());
		this.MapPanel.getDescriptionRoom().setText(MainWindow.game.getCurrentRoom().getDescription());
	}
	
	private void jMenuItem1ActionPerformed(ActionEvent evt) {
		//System.out.println("jMenuItem1.actionPerformed, event="+evt);
		System.exit(2);
	
	}

	@Override
	public void gameError(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	@Override
	public void gameHelp() {
		
	}

	@Override
	public void gameOver(boolean win) {

		if (win) {
		 	JOptionPane.showMessageDialog(null,Constants.SALIDA + "HEALTH: " +MainWindow.game.getPlayer().getHealth()+
				 " SCORE: "+ MainWindow.game.getPlayer().getPoints());
		 System.exit(1);
		}
		else{
			 JOptionPane.showMessageDialog(null, "You are dead.\n GAME OVER \n Thank you for playing, goodbye. \n" +
			 		" HEALTH = 0, SCORE ="+MainWindow.game.getPlayer().getPoints());

		}
			
	}

	@Override
	public void gameStart(RoomInfo initialRoom, int playerPoints,
			int playerHealth) {
		this.HealthValor.setText(Integer.toString(playerHealth));
		this.ScoreValue.setText(Integer.toString(playerPoints));
		this.DescriptionRoom.setText(initialRoom.getDescription());
		
	}

	@Override
	public void gameQuit() {
		JOptionPane.showMessageDialog(null, Constants.MUERTO);
		
	}


}
