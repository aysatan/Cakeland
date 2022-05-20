package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import assets.ColorPalette;
import assets.FontFamily;
import components.Button;
import components.ComboBox;
import components.Frame;
import components.HeadingLabel4;
import components.Panel;
import components.Table;
import components.TextField;
import database.ManageMenuQuery;
import model.Cake;
import model.User;

public class ManageMenu implements ActionListener{
// Data
	private User user;
	private Random r = new Random();
	public static int selected=-1;
	public static String selectedCode;
	private Object header[] = new Object[]{
				"Cake ID",
				"Cake Name",
				"Cake Price",
				"Cake Size",
				"Cake Shape",
		  };
	private Vector<String>shapes = new Vector<>(Arrays.asList("Oval", "Rectangle"));
	private Vector<String>ovalSizes = new Vector<>(Arrays.asList("15 cm", "20 cm", "25 cm"));
	private Vector<String>rectangleSizes = new Vector<>(Arrays.asList("10 x 10 cm", "20 x 20 cm", "30 x 30 cm"));
	private Vector<Cake> cakes= new Vector<>();
//	Component
	private Frame frame = new Frame("CakeLAnd");
	private Panel pnlHeading = new Panel();
	private Panel pnlTitle = new Panel();
	private Panel pnlDummy1 = new Panel();
	private Panel pnlDummy2 = new Panel();
	private Panel pnlDummy3 = new Panel();
	private Panel pnlDummy4 = new Panel();
	private Panel pnlDummy5 = new Panel();
	private Panel pnlBottomLeft = new Panel(new GridBagLayout());
	private Panel pnlBottomRight = new Panel(new GridBagLayout());
	private Button btnMenu = new Button("Back to Main Menu", FontFamily.btnFont2, true);
	private Button btnRemove = new Button("Remove Cake");
	private Button btnAdd = new Button("Add Cake");
	private HeadingLabel4 lblHeading = new HeadingLabel4("CakeLAnd");
	private HeadingLabel4 lblTitle = new HeadingLabel4("Cake List", ColorPalette.navy);
	private JLabel lblName = new JLabel("Cake Name");
	private JLabel lblPrice = new JLabel("Cake Price"); 
	private JLabel lblShape = new JLabel("Cake Shape"); 
	private JLabel lblOvalSize = new JLabel("Oval Size"); 
	private JLabel lblRectangleSize = new JLabel("Rectangle Size");
	private TextField txtName = new TextField();
	private TextField txtPrice = new TextField();
	private ComboBox CBShape = new ComboBox(shapes);
	private ComboBox CBOvalSize = new ComboBox(ovalSizes);
	private ComboBox CBRectangleSize = new ComboBox(rectangleSizes);
	private Table table = new Table(header);
	private GridBagConstraints gbc = new GridBagConstraints();
	
	int randomInt(int min, int max){
		//min = 0
		//max = 9
		return r.nextInt(max-min+1)+min;
	}
	
	public ManageMenu(User user) {
		this.user = user;
		initTable();
		addToFrame();
		
		CBRectangleSize.getComboBox().setEnabled(false);
		
		CBShape.getComboBox().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				
				if(CBShape.getComboBox().getSelectedItem().toString().equals("Oval")){
					CBOvalSize.getComboBox().setEnabled(true);
					CBRectangleSize.getComboBox().setEnabled(false);
				}
				
				if(CBShape.getComboBox().getSelectedItem().toString().equals("Rectangle")){
					CBRectangleSize.getComboBox().setEnabled(true);
					CBOvalSize.getComboBox().setEnabled(false);
				}
			}
		});
		
		btnAdd.addActionListener(this);
		
		table.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				selected = table.getTable().getSelectedRow();
				txtName.setText(table.getTable().getValueAt(selected, 1).toString());
				txtPrice.setText(table.getTable().getValueAt(selected, 2).toString());
				CBShape.getComboBox().setSelectedItem(table.getTable().getValueAt(selected, 4));
				
				if(cakes.get(selected).getCakeShape().equals("Oval")){
					CBOvalSize.getComboBox().setSelectedItem(table.getTable().getValueAt(selected, 3));
					CBOvalSize.getComboBox().setEnabled(true);
					CBRectangleSize.getComboBox().setEnabled(false);
				}
				
				if(cakes.get(selected).getCakeShape().equals("Rectangle")){
					CBRectangleSize.getComboBox().setSelectedItem(table.getTable().getValueAt(selected, 3));
					CBRectangleSize.getComboBox().setEnabled(true);
					CBOvalSize.getComboBox().setEnabled(false);
				}
				
				selectedCode = table.getTable().getValueAt(selected, 0).toString();
			}
		});
		
		btnRemove.addActionListener(this);
		
		btnMenu.addActionListener(this);
		
		frame.Window();
//		setSize(900,800);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
//		setVisible(true);
	}
	
	void initTable(){
		table.eraseTable();
		cakes = ManageMenuQuery.getCakeList();
		for (Cake c : cakes) {
			Object cake[] = new Object[]{
				c.getCakeID(),
				c.getCakeName(),
				c.getCakePrice(),
				c.getCakeSize(),
				c.getCakeShape()
			};
			table.addTable(cake);
		}
	}
	
	private void insert(){
		
		char shape, size;
		String cakeID="";
		String cakeName = txtName.getText();
		Integer cakePrice = Integer.parseInt(txtPrice.getText().toString());
		String cakeShape = CBShape.getComboBox().getSelectedItem().toString();
		String cakeSize;
		
		if(CBShape.getComboBox().getSelectedItem().toString().equals("Oval")){
			cakeSize = CBOvalSize.getComboBox().getSelectedItem().toString();
			shape = 'O';
			
			if(CBOvalSize.getComboBox().getSelectedItem().toString().equals("15 cm")){
				size = 'F';
			} else if(CBOvalSize.getComboBox().getSelectedItem().toString().equals("20 cm")){
				size = 'N';
			} else{
				size = 'V';
			}
			
		} else {
			cakeSize = CBRectangleSize.getComboBox().getSelectedItem().toString();
			shape = 'R';
			
			if(CBRectangleSize.getComboBox().getSelectedItem().toString().equals("10 x 10 cm")){
				size = 'T';
			} else if(CBRectangleSize.getComboBox().getSelectedItem().toString().equals("20 x 20 cm")){
				size = 'W';
			} else{
				size = 'H';
			}
		}
		
		cakeID = "C" + shape + size + (randomInt(0,9)) + (randomInt(0,9)) + (randomInt(0,9)) + (randomInt(0,9));
		
		ManageMenuQuery.insertCake(cakeID, cakeName, cakePrice, cakeSize, cakeShape);
		initTable();
	}
	
	private void remove(){
		ManageMenuQuery.removeCake();
		initTable();
		selected = -1;
		selectedCode = null;
		txtName.setText("");
		txtPrice.setText("");
		CBShape.getComboBox().setSelectedIndex(0);
		CBOvalSize.getComboBox().setSelectedIndex(0);
		CBRectangleSize.getComboBox().setSelectedIndex(0);
	}
	
	void addToFrame(){
		frame.setLayout(new GridBagLayout());
		
		setGridBagLayout();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		frame.add(pnlDummy1, gbc);
		
		gbc.gridx = 1;
		frame.add(pnlDummy2, gbc);
		
		gbc.gridx = 2;
		frame.add(pnlDummy3, gbc);
		
		gbc.gridx = 3;
		frame.add(pnlDummy4, gbc);
		
		gbc.gridx = 4;
		frame.add(pnlDummy5, gbc);
		
		gbc.gridx = 5;
		gbc.gridwidth = 2;
		frame.add(btnMenu, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 7;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0,10,0,10);
		pnlHeading.add(lblHeading);
		frame.add(pnlHeading, gbc);
		
		gbc.gridy = 2;
		pnlTitle.add(lblTitle);
		frame.add(pnlTitle, gbc);
		
		gbc.gridy = 3;
		gbc.gridheight = 7;
		gbc.weighty = 1;
		frame.add(table, gbc);
		
		setPnlBottomLeft();
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 6;
		gbc.gridheight = 1;
		gbc.weighty = 0.2;
		gbc.insets = new Insets(0,10,0,10);
		frame.add(pnlBottomLeft, gbc);
		
		setPnlBottomRight();
		gbc.gridx = 6;
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0.2;
		gbc.insets = new Insets(0,0,0,10);
		frame.add(pnlBottomRight, gbc);
	}
	
	private void setGridBagLayout() {
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10,10,0,10);
	}
	
	private void setPnlBottomLeft() {
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10,10,0,10);
		pnlBottomLeft.add(lblName, gbc);
		
		gbc.gridx = 1;
		pnlBottomLeft.add(txtName, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		pnlBottomLeft.add(lblPrice, gbc);
		
		gbc.gridx = 1;
		pnlBottomLeft.add(txtPrice, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 0;
		pnlBottomLeft.add(lblShape, gbc);
		
		gbc.gridx = 1;
		pnlBottomLeft.add(CBShape.getComboBox(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		pnlBottomLeft.add(lblOvalSize, gbc);
		
		gbc.gridx = 1;
		pnlBottomLeft.add(CBOvalSize.getComboBox(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(10,10,10,10);
		pnlBottomLeft.add(lblRectangleSize, gbc);
		
		gbc.gridx = 1;
		pnlBottomLeft.add(CBRectangleSize.getComboBox(), gbc);
	}
	
	private void setPnlBottomRight() {
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10,10,0,0);
		pnlBottomRight.add(btnAdd, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(10,10,10,0);
		pnlBottomRight.add(btnRemove, gbc);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		UIManager UI=new UIManager();
		UI.put("OptionPane.background", ColorPalette.lightPink);
		
		if(e.getSource() == btnAdd){
			if(!txtName.getText().toString().endsWith(" Cake")){
				JOptionPane.showMessageDialog(frame, "Cake name must ends with 'Cake'", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if(Integer.parseInt(txtPrice.getText().toString())<100000 || Integer.valueOf(txtPrice.getText().toString())>500000){
				JOptionPane.showMessageDialog(frame, "Cake price must be between 100000-50000", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			insert();
			JOptionPane.showMessageDialog(frame, "Cake successfully inputted to the database!", "Information", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		else if(e.getSource() == btnRemove){
			if(selected<0){
				JOptionPane.showMessageDialog(frame, "Select cake you want to remove!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else{
				remove();
				JOptionPane.showMessageDialog(frame, "Cake Successfully Removed!", "Information", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		
		else if(e.getSource() == btnMenu){
			frame.setVisible(false);
			new MainForm(this.user);
		}

	}
	
}
