package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.ManageCartQuery;
import model.CakeOnCart;
import model.User;

public class ManageCart extends JFrame implements ActionListener {
	
	
	Color bgPink = new Color(255, 175, 175);
	Color lightPink = new Color(255, 217, 229);
	Color midPink = new Color(250, 177, 202);
	Color maroon = new Color(130, 70, 77);
	Color navy = new Color(27, 44, 99);
	Color grey = new Color(194, 184, 194);
	
	Font heading1 = new Font("DIZHITL BLACK REGULAR", Font.BOLD, 40);
	Font heading2 = new Font("DIZHITL BLACK REGULAR", Font.ITALIC + Font.BOLD, 15);
	Font heading3 = new Font("VERDANA", Font.BOLD, 14);
	Font btnFont = new Font("DIZHITL BLACK REGULAR", Font.BOLD, 20);
	
	JPanel pnlTop, pnlBottom, pnlBack, pnlRightBack, pnlTitle, pnlTable, pnlAction, pnlQty, pnlLblQty, pnlBtnRemove, pnlBtnUpdate, pnlBtnCheckOut, pnlBtnView;

	JButton btnBack, btnRemove, btnUpdate, btnCheckOut, btnView;
	JLabel lblHeading1, lblHeading2, lblQty;
	DefaultTableModel dtm;
	JTable cakeListTable = new JTable();
	JScrollPane cakeListScroll  = new JScrollPane(cakeListTable);
	public JSpinner spinnerQty;
	
	int spinnerMin = 1;
	int spinnerMax = 100;
	int spinnerStep = 1;
	int i = 1;
	SpinnerModel spinnerValue = new SpinnerNumberModel(i, spinnerMin, spinnerMax, spinnerStep);
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	Object header[] =  new Object[] {
			"Cake Name",
			"Cake Shape",
			"Cake Size",
			"Cake Price",
			"Quantity"
	};
	
	private User user;
	
	Vector<CakeOnCart> cakesOnCart = new Vector<>();
	
	public static int selected = -1;
	public static String selectedID;
	
	public ManageCart(User user) {
		this.user = user;
		initialize();
		panelSetLayout();
		addItemsToPnlTop();
		addItemsToPnlBottom();
		add(pnlTop, BorderLayout.NORTH);
		add(pnlBottom, BorderLayout.SOUTH);
		setUpBorder();
		design();
		addListeners();
		
		setSize(1000, 550);
		setTitle("CakeLAnd");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void addListeners() {
		btnBack.addActionListener(this);
		btnRemove.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnCheckOut.addActionListener(this);
		btnView.addActionListener(this);
		
		cakeListTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selected = cakeListTable.getSelectedRow();
				selectedID = cakesOnCart.get(selected).getCakeID();
				System.out.println(selectedID);
			}
		});		
	}

	private void design() {
//		background color
		getContentPane().setBackground(bgPink);
		pnlTop.setBackground(bgPink);
		pnlBottom.setBackground(bgPink);
		pnlBack.setBackground(bgPink);
		pnlRightBack.setBackground(bgPink);
		pnlTitle.setBackground(bgPink);
		pnlTable.setBackground(bgPink);
		pnlAction.setBackground(bgPink);
		pnlQty.setBackground(bgPink);
		pnlLblQty.setBackground(bgPink);
		pnlBtnRemove.setBackground(bgPink);
		pnlBtnUpdate.setBackground(bgPink);
		pnlBtnCheckOut.setBackground(bgPink);
		pnlBtnView.setBackground(bgPink);
		
//		font
		lblHeading1.setFont(heading1);
		lblHeading2.setFont(heading1);
		lblQty.setFont(heading3);
		
		lblHeading1.setHorizontalAlignment(JLabel.CENTER);
		lblHeading2.setHorizontalAlignment(JLabel.CENTER);
		
		lblHeading1.setForeground(maroon);
		lblHeading2.setForeground(navy);
		lblQty.setForeground(navy);
		
		btnRemove.setFont(btnFont);
		btnUpdate.setFont(btnFont);
		btnCheckOut.setFont(btnFont);
		btnView.setFont(btnFont);
		
//		buttons
		btnRemove.setBackground(lightPink);
		btnUpdate.setBackground(lightPink);
		btnCheckOut.setBackground(lightPink);
		btnView.setBackground(lightPink);
		btnBack.setBackground(lightPink);
		
		btnRemove.setForeground(navy);
		btnUpdate.setForeground(navy);
		btnCheckOut.setForeground(navy);
		btnView.setForeground(navy);
		btnBack.setForeground(navy);
		
		btnRemove.setBorderPainted(false);
		btnUpdate.setBorderPainted(false);
		btnCheckOut.setBorderPainted(false);
		btnView.setBorderPainted(false);
		
//		spinner
		spinnerQty.getEditor().getComponent(0).setBackground(lightPink);
		spinnerQty.setForeground(navy);
		
//		table
		cakeListTable.setBackground(lightPink);
		cakeListTable.getTableHeader().setBackground(midPink);
		cakeListTable.setForeground(maroon);
		cakeListTable.getTableHeader().setForeground(maroon);
		cakeListTable.getTableHeader().setFont(heading3);
	}

	private void setUpBorder() {
		pnlRightBack.setBorder(new EmptyBorder(10, 0, 0, 50));
		
		pnlAction.setBorder(new EmptyBorder(0, 0, 0, 80));
		pnlTable.setBorder(new EmptyBorder(0, 50, 20, 0));
		
		pnlLblQty.setBorder(new EmptyBorder(0, 0, 0, 20));
		pnlQty.setBorder(new EmptyBorder(0, 0, 20, 0));
		cakeListScroll.setPreferredSize(new Dimension(550,280));
		spinnerQty.setPreferredSize(new Dimension(120, 25));
		
		btnRemove.setPreferredSize(new Dimension(250, 40));
		btnUpdate.setPreferredSize(new Dimension(250, 40));
		btnCheckOut.setPreferredSize(new Dimension(250, 40));
		btnView.setPreferredSize(new Dimension(250, 40));
		btnBack.setPreferredSize(new Dimension(150, 30));
	}

	private void addItemsToPnlTop() {
		pnlTop.add(pnlBack, BorderLayout.NORTH);
		pnlTop.add(pnlTitle, BorderLayout.CENTER);
		
		pnlBack.add(pnlRightBack, BorderLayout.EAST);
		pnlRightBack.add(btnBack);
		
		pnlTitle.add(lblHeading1);
		pnlTitle.add(lblHeading2);	
	}

	private void addItemsToPnlBottom() {
		pnlBottom.add(pnlTable, BorderLayout.WEST);
		pnlBottom.add(pnlAction, BorderLayout.EAST);
		
		setUpTable();
		
		pnlLblQty.add(lblQty, BorderLayout.EAST);
		pnlQty.add(pnlLblQty);
		pnlQty.add(spinnerQty);
		
		pnlBtnRemove.add(btnRemove);
		pnlBtnUpdate.add(btnUpdate);
		pnlBtnCheckOut.add(btnCheckOut);
		pnlBtnView.add(btnView);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		pnlAction.add(pnlQty, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		pnlAction.add(pnlBtnRemove, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		pnlAction.add(pnlBtnUpdate, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		pnlAction.add(pnlBtnCheckOut, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		pnlAction.add(pnlBtnView, gbc);
	}

	private void setUpTable() {
		dtm = new DefaultTableModel(header, 0);
		cakeListTable.setModel(dtm);
		
		pnlTable.add(cakeListScroll);
		
		dtm.setRowCount(0);
		
		cakesOnCart = ManageCartQuery.getCakeOnCartList(user.getUserID());
		
		if (cakesOnCart.isEmpty()) {
			System.out.println("There is no data!");
		} else {
			for (CakeOnCart cake : cakesOnCart) {
				Object c[] = new Object[] {
					cake.getCakeName(),
					cake.getCakeShape(),
					cake.getCakeSize(),
					cake.getCakePrice(),
					cake.getQuantity()
				};
				dtm.addRow(c);
			}
		}
	}

	private void panelSetLayout() {
		setLayout(new BorderLayout());
		pnlTop.setLayout(new BorderLayout());
		pnlBottom.setLayout(new BorderLayout());
		pnlBack.setLayout(new BorderLayout());
		pnlTitle.setLayout(new GridLayout(2,1));
		pnlQty.setLayout(new GridLayout(1,2));
		pnlAction.setLayout(new GridBagLayout());
		pnlLblQty.setLayout(new BorderLayout());
	}

	private void initialize() {
		pnlTop = new JPanel();
		pnlBottom = new JPanel();
		pnlBack = new JPanel();
		pnlRightBack = new JPanel();
		pnlTitle = new JPanel();
		pnlTable = new JPanel();
		pnlAction = new JPanel();
		pnlQty = new JPanel();
		pnlLblQty = new JPanel();
		pnlBtnRemove = new JPanel();
		pnlBtnUpdate = new JPanel();
		pnlBtnCheckOut = new JPanel();
		pnlBtnView = new JPanel();
		
		lblHeading1 = new JLabel("CakeLAnd");
		lblHeading2 = new JLabel("Your Cart");
		lblQty = new JLabel("Quantity");
		
		btnBack = new JButton("Back to Main Menu");
		btnRemove = new JButton("Remove From Cart");
		btnUpdate = new JButton("Update Cake Order");
		btnCheckOut = new JButton("Check Out");
		btnView = new JButton("View All Menu");
		
		spinnerQty = new JSpinner(spinnerValue);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnRemove) {
			if (selected < 0) {
				JOptionPane.showMessageDialog(this, "Select cake you want to remove!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} 
			else {
				System.out.println(user.getUserID());
				System.out.println(selectedID);
				
				remove();
				JOptionPane.showMessageDialog(this, "Cake Successfully removed from your cart!", "Success", JOptionPane.INFORMATION_MESSAGE);
			} 
			
		} else if (e.getSource() == btnUpdate) {
			if (selected < 0) {
				JOptionPane.showMessageDialog(this, "Select cake you want to update!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} 
			else {
				System.out.println(user.getUserID());
				System.out.println(selectedID);
				System.out.println(Integer.parseInt(spinnerQty.getValue().toString()));
				
				update();
				JOptionPane.showMessageDialog(this, "Cake Successfully updated from your cart!", "Success", JOptionPane.INFORMATION_MESSAGE);
			} 
			
		} else if (e.getSource() == btnCheckOut) {
			
			if (cakesOnCart.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Your cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				setVisible(false);
				new ConfirmCheckOut(user);
			}
			
		} else if (e.getSource() == btnView) {
			setVisible(false);
			new ViewAllMenu(user);
		} else if (e.getSource() == btnBack) {
			setVisible(false);
			new MainForm(user);
		}
	}

	private void update() {
		String userId = user.getUserID();
		int spinnerQtyValue = Integer.parseInt(spinnerQty.getValue().toString());
		
		ManageCartQuery.updateCake(userId, spinnerQtyValue);
		setUpTable();
		selected = -1;
		selectedID = null;
	}

	private void remove() {
		ManageCartQuery.removeCake(user.getUserID());
		setUpTable();
		selected = -1;
		selectedID = null;
	}

}