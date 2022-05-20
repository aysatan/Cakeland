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

import database.ViewAllMenuQuery;
import model.Cake;
import model.Cart;
import model.User;

public class ViewAllMenu extends JFrame implements ActionListener {

	Color bgPink = new Color(255, 175, 175);
	Color lightPink = new Color(255, 217, 229);
	Color midPink = new Color(250, 177, 202);
	Color maroon = new Color(130, 70, 77);
	Color navy = new Color(27, 44, 99);
	Color grey = new Color(194, 184, 194);
	
	Font heading1 = new Font("DIZHITL BLACK REGULAR", Font.BOLD, 40);
	Font heading2 = new Font("DIZHITL BLACK REGULAR", Font.ITALIC + Font.BOLD, 15);
	Font heading3 = new Font("VERDANA", Font.BOLD, 16);
	Font btnFont = new Font("DIZHITL BLACK REGULAR", Font.BOLD, 18);
	
	JPanel pnlTop, pnlBottom, pnlBack, pnlRightBack, pnlTitle, pnlTable, pnlAction, pnlQty, pnlLblQty, pnlBtnAdd, pnlBtnView;
	
	JLabel lblHeading1, lblHeading2, lblQty;
	JButton btnBack, btnAdd, btnView;
	DefaultTableModel dtm;
	JTable cakeListTable;
	JScrollPane cakeListScroll;
	JSpinner spinnerQty;
	
	Vector<Cake> cakes = new Vector<>();
	
	int spinnerMin = 1;
	int spinnerMax = 100;
	int spinnerStep = 1;
	int i = 1;
	SpinnerModel spinnerValue = new SpinnerNumberModel(i, spinnerMin, spinnerMax, spinnerStep);
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	Object header[] =  new Object[] {
			"Cake Name",
			"Cake Price",
			"Cake Shape",
			"Cake Size"
	};
	
	private User user;
	
	String cakeName;
	String cakeShape;
	int cakeSize;
	int cakePrice;
	int quantity;
	
	static int selected = -1;
	static String selectedID;
	
	public ViewAllMenu(User user) {
		this.user = user;
		
		initialize();
		panelSetLayout();
		addItemsToPnlTop();
		addItemsToPnlBottom();
		add(pnlTop, BorderLayout.NORTH);
		add(pnlBottom, BorderLayout.SOUTH);
		setUpBorder();
		design();
		addListener();
		
		setSize(900, 600);
		setTitle("CakeLAnd");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void addListener() {
		btnAdd.addActionListener(this);
		btnBack.addActionListener(this);
		btnView.addActionListener(this);
		
		cakeListTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selected = cakeListTable.getSelectedRow();
				selectedID = cakes.get(selected).getCakeID();
				System.out.println(selectedID);
			}
		});		
		
	}

	private void setUpBorder() {
		pnlRightBack.setBorder(new EmptyBorder(10, 0, 0, 50));

		lblHeading1.setBorder(new EmptyBorder(20, 0, 0, 0));
		
		pnlAction.setBorder(new EmptyBorder(0, 0, 0, 80));
		pnlTable.setBorder(new EmptyBorder(0, 50, 20, 0));
		
		pnlLblQty.setBorder(new EmptyBorder(0, 0, 0, 20));
		pnlQty.setBorder(new EmptyBorder(0, 0, 20, 0));
		cakeListScroll.setPreferredSize(new Dimension(450,300));
		spinnerQty.setPreferredSize(new Dimension(120, 25));
		
		btnAdd.setPreferredSize(new Dimension(160, 50));
		btnView.setPreferredSize(new Dimension(160, 50));
		btnBack.setPreferredSize(new Dimension(150, 30));
	}

	private void addItemsToPnlBottom() {
		pnlBottom.add(pnlTable, BorderLayout.WEST);
		pnlBottom.add(pnlAction, BorderLayout.EAST);
		
		setUpTable();
		
		pnlLblQty.add(lblQty, BorderLayout.EAST);
		pnlQty.add(pnlLblQty);
		pnlQty.add(spinnerQty);
		
		pnlBtnAdd.add(btnAdd);
		pnlBtnView.add(btnView);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		pnlAction.add(pnlQty, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		pnlAction.add(pnlBtnAdd, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		pnlAction.add(pnlBtnView, gbc);
	}

	private void addItemsToPnlTop() {
		pnlTop.add(pnlBack, BorderLayout.NORTH);
		pnlTop.add(pnlTitle, BorderLayout.CENTER);
		
		pnlBack.add(pnlRightBack, BorderLayout.EAST);
		pnlRightBack.add(btnBack);
		
		pnlTitle.add(lblHeading1);
		pnlTitle.add(lblHeading2);
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
		pnlBtnAdd.setBackground(bgPink);
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
		
		btnAdd.setFont(btnFont);
		btnView.setFont(btnFont);
		
//		buttons
		btnAdd.setBackground(lightPink);
		btnView.setBackground(lightPink);
		btnBack.setBackground(lightPink);
		
		btnAdd.setForeground(navy);
		btnView.setForeground(navy);
		btnBack.setForeground(navy);
		
		btnAdd.setBorderPainted(false);
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
		cakeListTable.setRowHeight(25);
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
		pnlBtnAdd = new JPanel();
		pnlBtnView = new JPanel();
		
		lblHeading1 = new JLabel("CakeLAnd");
		lblHeading2 = new JLabel("Cake List");
		lblQty = new JLabel("Quantity");
	
		btnBack = new JButton("Back to Main Menu");
		btnAdd = new JButton("Add to Cart");
		btnView = new JButton("View Cart");
	
		spinnerQty = new JSpinner(spinnerValue);
	}


	private void setUpTable() {
		cakeListTable = new JTable();
		cakeListScroll = new JScrollPane(cakeListTable);
		dtm = new DefaultTableModel(header, 0);
		cakeListTable.setModel(dtm);
		
		pnlTable.add(cakeListScroll);
		
		dtm.setRowCount(0);
		
		cakes = ViewAllMenuQuery.getCakeList();
		
		if (cakes == null) {
			System.out.println("There is no data!");
		} else {
			for (Cake cake : cakes) {
				Object c[] = new Object[] {
					cake.getCakeName(),
					cake.getCakePrice(),
					cake.getCakeShape(),
					cake.getCakeSize()
				};
				dtm.addRow(c);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnAdd) {
			
			if (selected < 0) {
				JOptionPane.showMessageDialog(this, "Select Cake to add!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} 
			else if (checkCart()) {
				System.out.println(user.getUserID());
				System.out.println(selectedID);
				System.out.println(Integer.parseInt(spinnerQty.getValue().toString()));
				
				insert();
				JOptionPane.showMessageDialog(this, "Cake Successfully added to Cart!", "Success", JOptionPane.INFORMATION_MESSAGE);
			} else if(checkCart() == false) {
				JOptionPane.showMessageDialog(this, "This cake is already in your cart! If you want to change the quantity, do it on the update page!", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			
		} else if (e.getSource() == btnView) {
			setVisible(false);
			new ManageCart(user);
		} else if (e.getSource() == btnBack) {
			setVisible(false);
			new MainForm(user);
		}
	}
	
	private boolean checkCart() {
		Vector<Cart> vCart = ViewAllMenuQuery.getCartList(user.getUserID());
		
		for (int i = 0; i < vCart.size(); i++) {
			if (vCart.get(i).getCakeID().equals(selectedID)) {
				return false;
			}			
		}
		
		return true;
	}


	private void insert() {
		String userID = user.getUserID();
		String cakeID = selectedID;
		int quantity = Integer.parseInt(spinnerQty.getValue().toString());
		
		Cart cart = new Cart(userID, cakeID, quantity);
		
		ViewAllMenuQuery.addToCart(cart);
	}
	
	
}
