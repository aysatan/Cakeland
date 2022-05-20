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
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.ConfirmCheckOutQuery;
import model.CakeOnCart;
import model.User;

public class ConfirmCheckOut extends JFrame implements ActionListener {
	
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
	
	JPanel pnlTop, pnlBottom, pnlTitle, pnlTable, pnlAction, pnlTotal, pnlPickUpDate, pnlLblTotal, pnlLblPickUpDate, pnlBtnCheckOut, pnlBtnBack;

	JLabel lblHeading1, lblHeading2, lblTotal, lblPickUpDate;
	JTextField txtTotal, txtPickUpDate;
	JButton btnCheckOut, btnBack;
	
	DefaultTableModel dtm;
	JTable cakeListTable;
	JScrollPane cakeListScroll;
	
	Vector<CakeOnCart> cakes = new Vector<>();
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	Object header[] =  new Object[] {
			"Cake Name",
			"Cake Shape",
			"Cake Size",
			"Cake Price",
			"Quantity"
	};
	
	private User user;
	
	public ConfirmCheckOut(User user) {
		this.user = user;
		initialize();
		panelSetLayout();
		addItemsToPnlTop();
		addItemsToPnlBottom();
		add(pnlTop, BorderLayout.NORTH);
		add(pnlBottom, BorderLayout.SOUTH);
		addActionListener();
		setUpBorder();
		design();
		
		setSize(950, 550);
		setTitle("CakeLAnd");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void addActionListener() {
		btnBack.addActionListener(this);
		btnCheckOut.addActionListener(this);
	}

	private void design() {
//		background color
		getContentPane().setBackground(bgPink);		
		pnlTop.setBackground(bgPink);
		pnlBottom.setBackground(bgPink);
		pnlTitle.setBackground(bgPink);
		pnlTable.setBackground(bgPink);
		pnlAction.setBackground(bgPink);
		pnlTotal.setBackground(bgPink);
		pnlPickUpDate.setBackground(bgPink);
		pnlLblTotal.setBackground(bgPink);
		pnlLblPickUpDate.setBackground(bgPink);
		pnlBtnCheckOut.setBackground(bgPink);
		pnlBtnBack.setBackground(bgPink);
		
//		font
		lblHeading1.setFont(heading1);
		lblHeading2.setFont(heading1);
		lblTotal.setFont(heading3);
		lblPickUpDate.setFont(heading3);
		
		lblHeading1.setHorizontalAlignment(JLabel.CENTER);
		lblHeading2.setHorizontalAlignment(JLabel.CENTER);
		
		lblHeading1.setForeground(maroon);
		lblHeading2.setForeground(navy);
		lblTotal.setForeground(navy);
		lblPickUpDate.setForeground(navy);
		
		btnCheckOut.setFont(btnFont);
		btnBack.setFont(btnFont);
		
//		buttons
		btnCheckOut.setBackground(lightPink);
		btnBack.setBackground(lightPink);
		
		btnCheckOut.setForeground(navy);
		btnBack.setForeground(navy);
		
		btnCheckOut.setBorderPainted(false);
		btnBack.setBorderPainted(false);
			
//		table
		cakeListTable.setBackground(lightPink);
		cakeListTable.getTableHeader().setBackground(midPink);
		cakeListTable.setForeground(maroon);
		cakeListTable.getTableHeader().setForeground(maroon);
		cakeListTable.getTableHeader().setFont(heading3);
		
		
//		TextField
		txtTotal.setBackground(lightPink);
		txtPickUpDate.setBackground(lightPink);
		
		txtTotal.setForeground(navy);
		txtPickUpDate.setForeground(navy);
		
		txtTotal.setFont(heading3);
		txtPickUpDate.setFont(heading3);
	}

	private void setUpBorder() {
		lblHeading1.setBorder(new EmptyBorder(20, 0, 0, 0));
		
		pnlAction.setBorder(new EmptyBorder(0, 0, 0, 50));
		pnlTable.setBorder(new EmptyBorder(0, 50, 20, 0));
		
		pnlLblTotal.setBorder(new EmptyBorder(0, 0, 0, 10));
		pnlLblPickUpDate.setBorder(new EmptyBorder(0, 0, 0, 10));

		pnlTotal.setBorder(new EmptyBorder(0, 0, 5, 0));
		pnlPickUpDate.setBorder(new EmptyBorder(0, 0, 5, 0));
		
		cakeListScroll.setPreferredSize(new Dimension(500,300));
		
		btnCheckOut.setPreferredSize(new Dimension(160, 50));
		btnBack.setPreferredSize(new Dimension(240, 50));
		
		txtTotal.setPreferredSize(new Dimension(150, 30));
		txtPickUpDate.setPreferredSize(new Dimension(150, 30));
	}

	private void addItemsToPnlBottom() {
		pnlBottom.add(pnlTable, BorderLayout.WEST);
		pnlBottom.add(pnlAction, BorderLayout.EAST);
		
		setUpTable();
		txtTotal.setText(calculateTotal());
		
		pnlLblTotal.add(lblTotal, BorderLayout.WEST);
		pnlTotal.add(pnlLblTotal);
		pnlTotal.add(txtTotal);
		
		pnlLblPickUpDate.add(lblPickUpDate, BorderLayout.WEST);
		pnlPickUpDate.add(pnlLblPickUpDate);
		pnlPickUpDate.add(txtPickUpDate);
		
		pnlBtnCheckOut.add(btnCheckOut);
		pnlBtnBack.add(btnBack);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		pnlAction.add(pnlTotal, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		pnlAction.add(pnlPickUpDate, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		pnlAction.add(pnlBtnCheckOut, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		pnlAction.add(pnlBtnBack, gbc);
	}

	private void setUpTable() {
		cakeListTable = new JTable();
		cakeListScroll = new JScrollPane(cakeListTable);
		dtm = new DefaultTableModel(header, 0);
		cakeListTable.setModel(dtm);
		
		pnlTable.add(cakeListScroll);
		
		dtm.setRowCount(0);

		cakes = ConfirmCheckOutQuery.getCakeOnCartList(this.user.getUserID());
		
		if (cakes == null) {
			System.out.println("There is no data!");
		} else {
			for (CakeOnCart cake : cakes) {
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

	private void addItemsToPnlTop() {
		pnlTop.add(pnlTitle, BorderLayout.CENTER);
		
		pnlTitle.add(lblHeading1);
		pnlTitle.add(lblHeading2);
	}

	private void panelSetLayout() {
		setLayout(new BorderLayout());
		pnlTop.setLayout(new BorderLayout());
		pnlBottom.setLayout(new BorderLayout());
		pnlTitle.setLayout(new GridLayout(2,1));
		pnlTotal.setLayout(new GridLayout(1,2));
		pnlPickUpDate.setLayout(new GridLayout(1,2));
		pnlAction.setLayout(new GridBagLayout());
		pnlLblTotal.setLayout(new BorderLayout());
		pnlLblPickUpDate.setLayout(new BorderLayout());
	}

	private void initialize() {
		pnlTop = new JPanel();
		pnlBottom = new JPanel();
		pnlTitle = new JPanel();
		pnlTable = new JPanel();
		pnlAction = new JPanel();
		pnlTotal = new JPanel();
		pnlPickUpDate = new JPanel();
		pnlLblTotal = new JPanel();
		pnlLblPickUpDate = new JPanel();
		pnlBtnCheckOut = new JPanel();
		pnlBtnBack = new JPanel();
		
		lblHeading1 = new JLabel("CakeLAnd");
		lblHeading2 = new JLabel("Your Order");
		lblTotal = new JLabel("Total");
		lblPickUpDate = new JLabel("Pick Up Date");
		
		btnCheckOut = new JButton("Check Out");
		btnBack = new JButton("Back to Main Menu");
		
		txtTotal = new JTextField();
		txtPickUpDate = new JTextField();
		
		txtTotal.setText(calculateTotal());
		txtPickUpDate.setText(getPickUpDate());
	}
	
	private String getPickUpDate() {
	
		Date date = new Date();
		String dateString = String.valueOf(java.time.LocalDate.now());
		
		return dateString;
	}

	private String calculateTotal() {
		int price, qty, total = 0;
		String totalValue;
		
		for (int i = 0; i < cakes.size(); i++) {
			price = cakes.get(i).getCakePrice();
			qty = cakes.get(i).getQuantity();
			
			total += price * qty;
		}
		
		totalValue = String.valueOf(total);

		return totalValue;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnCheckOut) {
			delete();
			JOptionPane.showMessageDialog(this, "Transaction Successful! Remember to pick up your order! :)", "Success", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			new MainForm(this.user);
		} else if (e.getSource() == btnBack) {
			setVisible(false);
			new MainForm(this.user);
		}
	}

	private void delete() {
		Random rand = new Random();
		int randomuserid;
		do {
			randomuserid = rand.nextInt(9999);
		}while(randomuserid < 1000);
		String transactionID = "T" + randomuserid;
		ConfirmCheckOutQuery.addTransactionHistoryHeader(transactionID, user.getUserID(), txtPickUpDate.getText(), getPickUpDate());
		ConfirmCheckOutQuery.addTransactionHistoryDetail(transactionID, cakes);
		ConfirmCheckOutQuery.removeAllCake(this.user.getUserID());
		setUpTable();
	}

}
