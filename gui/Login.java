package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import assets.ColorPalette;
import assets.FontFamily;
import components.Button;
import components.Frame;
import components.HeadingLabel1;
import components.Panel;
import components.PasswordField;
import components.TextField;
import database.LoginQuery;
import model.User;


public class Login implements ActionListener{
//	Data
	Vector<User> users = new Vector<>();
//Component
	Frame frame = new Frame(420, 600, "Login");
	Panel pnlForm = new Panel(new GridBagLayout());
	Panel pnlTitle = new Panel(); 
	Panel pnlUsername = new Panel(); 
	Panel pnlPassword = new Panel(); 
	Panel pnlBtnLogin = new Panel(); 
	Panel pnlLogin = new Panel(); 
	Panel pnlRegister = new Panel();
	Panel pnlDummy1 = new Panel();
	Panel pnlDummy2 = new Panel();
	Panel pnlDummy3 = new Panel();
	Panel pnlDummy4 = new Panel();
	Panel pnlDummy5 = new Panel();
	Panel pnlDummy6 = new Panel();
	HeadingLabel1 lblTitle = new HeadingLabel1("CakeLAnd");
	JLabel lblUsername = new JLabel("Username");
	JLabel lblPassword = new JLabel("Password");
	TextField txtUsername = new TextField(true, true);
	PasswordField txtPassword = new PasswordField(true, true);
	Button btnLogin = new Button("Login");
	Button btnRegister = new Button(true, "Don't have account? Register", FontFamily.btnFont3, ColorPalette.blue);
	GridBagConstraints gbc = new GridBagConstraints();
	Border blackline = BorderFactory.createLineBorder(Color.black);
	
	public Login() {
		frame.setTitle("Login");
		inisialisasi();
		addToFrame();
		
		frame.Window();
	}
	
	private void loginUser(){
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		boolean valid = false;
		
		users = LoginQuery.getUserList(username, password);
		if (!users.isEmpty()) {
			User user = users.get(0);
			valid = true;
			JOptionPane.showMessageDialog(frame, "Login Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
			
			if(user.getUserRole().equals("Admin")){
				new MainForm(user);
				frame.setVisible(false);
			} else if(user.getUserRole().equals("User")){
				new MainForm(user);
				frame.setVisible(false);
			}
			return;
		}
		
		if(valid == false){
			JOptionPane.showMessageDialog(frame, "Wrong Credentials!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void inisialisasi(){
		txtUsername.setHorizontalAlignment(JTextField.CENTER);
		txtPassword.setHorizontalAlignment(JTextField.CENTER);
		
		btnLogin.addActionListener(this);
		btnRegister.addActionListener(this);
		
	}
	
	private void addToFrame(){
		frame.setLayout(new GridBagLayout());

		setGridBagLayout();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		pnlTitle.add(lblTitle);
		frame.add(pnlTitle, gbc);
		
		gbc.gridy = 1;
		gbc.weighty = 0.1;
		pnlUsername.add(lblUsername);
		frame.add(pnlUsername, gbc);
		
		gbc.gridy = 2;
		frame.add(txtUsername, gbc);
		
		gbc.gridy = 3;
		pnlPassword.add(lblPassword);
		frame.add(pnlPassword, gbc);
		
		gbc.gridy = 4;
		frame.add(txtPassword, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,20,10,0);
		frame.add(pnlDummy1, gbc);
		
		gbc.gridx = 1;
		gbc.insets = new Insets(0,0,10,0);
		frame.add(pnlDummy2, gbc);
		
		gbc.gridx = 2;
		pnlBtnLogin.add(btnLogin);
		gbc.insets = new Insets(0,75,10,0);
		frame.add(pnlBtnLogin, gbc);
		
		gbc.gridx = 3;
		gbc.insets = new Insets(0,0,10,0);
		frame.add(pnlDummy3, gbc);
		
		gbc.gridx = 4;
		gbc.insets = new Insets(0,0,10,20);
		frame.add(pnlDummy4, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(0,20,10,0);
		frame.add(pnlDummy5, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.insets = new Insets(0,0,10,0);
		frame.add(pnlDummy6, gbc);
		
		gbc.gridx = 2;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0,75,10,20);
		frame.add(btnRegister, gbc);
	}
	
	private void setGridBagLayout() {
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,20,10,20);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		UIManager UI=new UIManager();
		UI.put("OptionPane.background", ColorPalette.lightPink);
		UI.put("Panel.background", ColorPalette.lightPink);
		
		if(e.getSource() == btnLogin){
			if(txtUsername.getText().toString().isEmpty()){
				JOptionPane.showMessageDialog(frame, "Username cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(txtPassword.getText().toString().isEmpty()){
				JOptionPane.showMessageDialog(frame, "Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			loginUser();
			
		} else if(e.getSource() == btnRegister){
			frame.setVisible(false);
			new Register();
		}
		
	}

	public static void main(String[] args) {
		new Login();
	}

}