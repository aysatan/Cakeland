package gui;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import assets.ColorPalette;
import components.Frame;
import components.HeadingLabel1;
import components.HeadingLabel4;
import components.Panel;
import model.User;
import components.MenuBar;

public class MainForm implements ActionListener{
//	Data
	private User user;
//	Components
	private Frame frame = new Frame("CakeLAnd");
	private MenuBar menuBar = new MenuBar();
	private JMenu mManageAccount, mTransaction, mCakeMenu;
	private JMenuItem mtProfile, mtLogOff, mtViewAllMenu, mtManageCart, 
			  mtViewTransactionHistory, mtManageMenu;
	
	public MainForm(User user) {
		this.user = user;
		JLabel lbHello;
		HeadingLabel1 lbWelcome = new HeadingLabel1("Welcome to CakeLAnd");
		Panel panel = new Panel();
		
		frame.setLayout(new GridBagLayout());
		setMenuBar();
		frame.setJMenuBar(menuBar);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		if (this.user.getUserRole().equals("User")) {
			lbHello = new HeadingLabel4("Hello " + user.getUserName() + "!", ColorPalette.navy);
			panel.add(lbHello);
		}
		
		panel.add(lbWelcome);
		
		frame.add(panel);
		frame.Window();
	}
	
	public void setMenuBar() {
		mManageAccount = new JMenu("Manage Account");
		mtProfile = new JMenuItem("Profile");
		mtLogOff = new JMenuItem("Log Off");
		
		mtProfile.addActionListener(this);
		mtLogOff.addActionListener(this);
		
		mManageAccount.add(mtProfile);
		mManageAccount.add(mtLogOff);
		
		menuBar.add(mManageAccount);
		
		switch(this.user.getUserRole()) {
		case "User":
			mTransaction = new JMenu("Transaction");
			mtViewAllMenu = new JMenuItem("View All Menu");
			mtManageCart = new JMenuItem("Manage Cart");
			mtViewTransactionHistory = new JMenuItem("View Transaction History");
			
			mtViewAllMenu.addActionListener(this);
			mtManageCart.addActionListener(this);
			mtViewTransactionHistory.addActionListener(this);
			
			mTransaction.add(mtViewAllMenu);
			mTransaction.add(mtManageCart);
			mTransaction.add(mtViewTransactionHistory);
			
			menuBar.add(mTransaction);
			break;
		case "Admin":
			mCakeMenu = new JMenu("Cake Menu");
			mtManageMenu = new JMenuItem("Manage Menu");
			
			mtManageMenu.addActionListener(this);
			
			mCakeMenu.add(mtManageMenu);
			
			menuBar.add(mCakeMenu);
			break;
		}
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(mtProfile)){
			new UpdateProfile(user);
			frame.setVisible(false);
		} 
		
		if(e.getSource().equals(mtLogOff)){
			new Login();
			frame.setVisible(false);
		} 
		
		if(e.getSource().equals(mtViewAllMenu)) {
			new ViewAllMenu(user);
			frame.setVisible(false);
		} 
		
		if(e.getSource().equals(mtManageCart)) {
			new ManageCart(user);
			frame.setVisible(false);
		} 
		
		if(e.getSource().equals(mtViewTransactionHistory)) {
			new TransactionHistory(user);
			frame.setVisible(false);
		} 
		
		if(e.getSource().equals(mtManageMenu)) {
			new ManageMenu(user);
			frame.setVisible(false);
		}
		
	}

}
