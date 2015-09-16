package tp.pr5.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import tp.pr5.Constants;
import tp.pr5.PlayerObserver;
import tp.pr5.items.Item;



public class PlayerPanel implements PlayerObserver{
	
	private JPanel PlayerPanel;
	private JPanel HealthPlayer;
	private JPanel InventoryPanel;
	private JLabel HealthLabel;
	private JLabel ScoreLabel;
	private JLabel HealthValor;
	private JLabel ScoreValue;
	private JTable Inventario;
	private String auxiliar;
	private JTextField objeto;
	private	JOptionPane aviso;

	


	

 	public JPanel  getPlayerPanel(){
		return this.PlayerPanel;
	}
	
	
	public JOptionPane getAviso() {
		return aviso;
	}


	public void setAviso(JOptionPane aviso) {
		this.aviso = aviso;
	}


	public PlayerPanel(JTextField InventoryItem){
		

			PlayerPanel = new JPanel();
			GridBagLayout PlayerPanelLayout = new GridBagLayout();
			Border borde = BorderFactory.createLineBorder(Constants.Colores.AZUL);
			String titulo = "Player info";
			TitledBorder title = BorderFactory.createTitledBorder(borde,titulo);
			title.setTitleJustification(TitledBorder.LEFT);
			PlayerPanel.setBorder(title);
			PlayerPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			PlayerPanelLayout.rowHeights = new int[] {7, 7, 7, 7};
			PlayerPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			PlayerPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
			PlayerPanel.setLayout(PlayerPanelLayout);
			{
				HealthPlayer = new JPanel();
				PlayerPanel.add(HealthPlayer, new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					HealthLabel = new JLabel();
					HealthPlayer.add(HealthLabel);
					HealthLabel.setText("Health");
				}
				{
					HealthValor = new JLabel();
					HealthPlayer.add(HealthValor);
					HealthValor.setText("100");
				}
				{
					ScoreLabel = new JLabel();
					HealthPlayer.add(ScoreLabel);
					ScoreLabel.setText("Score");
				}
				{
					ScoreValue = new JLabel();
					HealthPlayer.add(ScoreValue);
					ScoreValue.setText("00");
				}
			}
			{
				InventoryPanel = new JPanel();
				PlayerPanel.add(InventoryPanel, new GridBagConstraints(0, 1, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					DefaultTableModel InventarioModel = new DefaultTableModel(
							new String[][]{},
							new String[] { "id", "description", "CanBeUsed"}
							);
						/*	new DefaultTableModel(
									new String[][] { { "One", "Two" }, { "Three", "Four" } },
									new String[] { "Column 1", "Column 2", "Column 3" });
				*/	//DefaultTableModel modelo = new DefaultTableModel();
					Inventario = new JTable(InventarioModel);
					InventoryPanel.add(Inventario);
					//Inventario = new JTable();
					Inventario.setAutoCreateRowSorter(true);
					JScrollPane scrollPane = new JScrollPane(Inventario);
					InventoryPanel.add(scrollPane);
					scrollPane.setPreferredSize(new java.awt.Dimension(453, 115));
					Inventario.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							scrollPaneMouseClicked(evt);
						}
					});
					Inventario.setModel(InventarioModel);
					Inventario.setPreferredSize(new java.awt.Dimension(450, 150));
					Inventario.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					Inventario.addMouseListener(new MouseAdapter() 
					   {

						
					   });
				}
			}
				this.objeto = InventoryItem;
		}

	public JTable getInventario() {
		return Inventario;
	}


	public void setInventario(JTable inventario) {
		this.Inventario = inventario;
	}
	
	public void playerUpdate(int newHealth, int newScore){
		this.HealthValor.setText(Integer.toString(newHealth));
		this.ScoreValue.setText(Integer.toString(newScore));
	}
	
	public void updatePlayerScore(int playerScore){
		this.ScoreValue.setText((Integer.toString(playerScore)));
	}
	
	public void inventoryUpdate(java.util.List<Item> inventory){
		
		String[][] elementos = new String[inventory.size()][3];
		for (int i=0; i<inventory.size(); i++) {
			Item aux = inventory.get(i);
			elementos[i][0] = aux.getId();
			elementos[i][1] = aux.getDescription();
			if (aux.canBeUsed()) {
				elementos[i][2] = "true";
			} else  {
				elementos[i][2] = "false";
			}
			//System.out.println(elementos[i][0]);
		}
		
		//System.out.println(((DefaultTableModel)Inventario.getModel()).getRowCount());
		
		int tamano = ((DefaultTableModel)Inventario.getModel()).getRowCount();
		for (int j=0; j<tamano; j++) {
			//System.out.println(j);
			((DefaultTableModel)Inventario.getModel()).removeRow(0);
		}
		
		Inventario.repaint();
		
		for (int k=0; k<inventory.size(); k++) {
			((DefaultTableModel)Inventario.getModel()).addRow(new Object[]{
	    		elementos[k][0],
	    		elementos[k][1],
	    		elementos[k][2]
	    	    });
		}
			
		Inventario.repaint();
	}
	
	public java.lang.String getSelectedItem(){
		return auxiliar;
	}
	
	private void scrollPaneMouseClicked(MouseEvent evt) {
		//System.out.println("scrollPane.mouseClicked, event="+evt);

		 int fila = Inventario.rowAtPoint(evt.getPoint());
         int columna = Inventario.columnAtPoint(evt.getPoint());
         if ((fila > -1) && (columna > -1)){
        	 auxiliar = Inventario.getValueAt(fila,0).toString();
        	 this.objeto.setText(auxiliar);
         }
        }

	@Override
	public void itemEmpty(String itemName) {
		JOptionPane.showMessageDialog(null,"The "+itemName+" is empty. It´s deletes from the inventory");
	}
		
	
	@Override
	public void itemLooked(String description) {
		
	}


	@Override
	public void itemUsed(String itemName) {

		JOptionPane.showMessageDialog(null,"Something changes ...");

	}


	@Override
	public void playerDead() {
	
		JOptionPane.showMessageDialog(null,Constants.MUERTO);

	}


	@Override
	public void playerLookedInventory(List<Item> inventory) {

		
	}
}
