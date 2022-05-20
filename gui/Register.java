package gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import assets.ColorPalette;
import assets.FontFamily;
import components.Button;
import components.CheckBox;
import components.Frame;
import components.HeadingLabel1;
import components.HeadingLabel4;
import components.Panel;
import components.PasswordField;
import components.RadioButton;
import components.TextArea;
import components.TextField;
import database.RegisterQuery;
import model.User;

public class Register implements ActionListener{
// Data
	Random rand = new Random();
	String Gender, Role, userID, userEmail, userPassword, userDOB, userPhoneNumber, userAddress, userName;
	boolean done;
	int randomuserid;
	//++
	int tempnumber;
	Vector<User> users = new Vector<>();
// Component
	Frame frame = new Frame(500,700,"Register");
	Panel pnlTitle = new Panel();
	Panel pnlCreate = new Panel();
	Panel pnlFormLeft = new Panel(new GridBagLayout());
	Panel pnlFormRight = new Panel(new GridBagLayout());
	Panel pnlRB = new Panel(new GridBagLayout());
	Panel pnlDash1 = new Panel();
	Panel pnlDash2 = new Panel();
	Panel pnlTnc = new Panel();
	Panel pnlBtnRegister = new Panel();
	Panel pnlBtnLogin = new Panel(new FlowLayout(FlowLayout.RIGHT));
	HeadingLabel1 lblTitle = new HeadingLabel1("CakeLAnd"); 
	HeadingLabel4 lblCreate = new HeadingLabel4("Create your own account!", ColorPalette.navy); 
	JLabel lblUsername = new JLabel("Username"); 
	JLabel lblEmail = new JLabel("Email");  
	JLabel lblGender = new JLabel("Gender"); 
	JLabel lblDob = new JLabel("DOB"); 
	JLabel lblDash1 = new JLabel("-");
	JLabel lblDash2 = new JLabel("-");
	JLabel lblPhone = new JLabel("Phone"); 
	JLabel lblAddress = new JLabel("Address"); 
	JLabel lblPassword = new JLabel("Password"); 
	JLabel lblConfirm = new JLabel("Confirm password"); 
	JLabel lblTnc = new JLabel("Agree to Terms & Condition"); 
	JLabel lblAlready = new JLabel("Already have an account? Login") ;
	TextField txtUsername = new TextField(); 
	TextField txtEmail = new TextField(); 
	TextField txtPhonenumber = new TextField(); 
	TextField txtAddress = new TextField(); 
	TextField txtDob1 = new TextField(); 
	TextField txtDob2 = new TextField(); 
	TextField txtDob3 = new TextField();
	PasswordField pswPassword = new PasswordField();
	PasswordField pswConfirm = new PasswordField();
	Button btnRegister = new Button("Register");
	RadioButton RBMale = new RadioButton("Male");
	RadioButton RBFemale = new RadioButton("Female");
	CheckBox checkbox1 = new CheckBox();
	TextArea TaAddress = new TextArea(5,10);
	ButtonGroup G1 = new ButtonGroup();
	GridBagConstraints gbc = new GridBagConstraints();
	
	public Register() {
		frame.setLayout(new GridBagLayout());
		setGridBagLayout();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		pnlTitle.add(lblTitle);
		frame.add(pnlTitle, gbc);
		
		gbc.gridy = 1;
		pnlCreate.add(lblCreate);
		frame.add(pnlCreate, gbc);
		
		setPnlFormLeft();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weighty = 0.5;
		gbc.insets = new Insets(0,30,0,0);
		frame.add(pnlFormLeft, gbc);
		
		setPnlFormRight();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weighty = 0.5;
		gbc.insets = new Insets(0,0,0,30);
		frame.add(pnlFormRight, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 5;
		gbc.weighty = 0;
		gbc.insets = new Insets(0,30,10,30);
		pnlTnc.add(checkbox1);
		pnlTnc.add(lblTnc);
		frame.add(pnlTnc, gbc);
		
		gbc.gridy = 4;
		pnlBtnRegister.add(btnRegister);
		frame.add(pnlBtnRegister, gbc);
		
		gbc.gridy = 5;
		lblAlready.setFont(FontFamily.btnFont3);
		lblAlready.setForeground(ColorPalette.blue);
		pnlBtnLogin.add(lblAlready);
		frame.add(pnlBtnLogin, gbc);
		
		btnRegister.addActionListener(this);
	
		frame.Window();
	}
	
	private void setGridBagLayout() {
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10,10,0,10);
	}
		
	private void setPnlFormLeft() {
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 0;
		gbc.gridheight = 1;
		gbc.weighty = 0.2;
		gbc.insets = new Insets(0,0,5,0);
		pnlFormLeft.add(lblUsername, gbc);
		
		gbc.gridy = 1;
		pnlFormLeft.add(lblEmail, gbc);
		
		gbc.gridy = 2;
		pnlFormLeft.add(lblGender, gbc);
		
		gbc.gridy = 3;
		pnlFormLeft.add(lblDob, gbc);
		
		gbc.gridy = 4;
		pnlFormLeft.add(lblPhone, gbc);
		
		gbc.gridy = 5;
		gbc.weighty = 0.8;
		pnlFormLeft.add(lblAddress, gbc);
		
		gbc.gridy = 6;
		gbc.weighty = 0.2;
		pnlFormLeft.add(lblPassword, gbc);
		
		gbc.gridy = 7;
		pnlFormLeft.add(lblConfirm, gbc);
	}
	
	private void setPnlFormRight() {
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.weighty = 0.2;
		gbc.insets = new Insets(0,0,5,0);
		pnlFormRight.add(txtUsername, gbc);
		
		gbc.gridy = 1;
		pnlFormRight.add(txtEmail, gbc);
		
		G1.add(RBMale.getRadioButton());
		G1.add(RBFemale.getRadioButton());
		
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,0);
		pnlRB.add(RBMale.getRadioButton(), gbc);
		gbc.gridx = 1;
		pnlRB.add(RBFemale.getRadioButton(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 5;
		gbc.insets = new Insets(0,0,5,0);
		pnlFormRight.add(pnlRB, gbc);
		
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		pnlFormRight.add(txtDob1, gbc);
		
		gbc.gridx = 1;
		pnlDash1.add(lblDash1);
		pnlFormRight.add(pnlDash1, gbc);
		
		gbc.gridx = 2;
		pnlFormRight.add(txtDob2, gbc);
		
		gbc.gridx = 3;
		pnlDash2.add(lblDash2);
		pnlFormRight.add(pnlDash2, gbc);
		
		gbc.gridx = 4;
		pnlFormRight.add(txtDob3, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 5;
		pnlFormRight.add(txtPhonenumber, gbc);
		
		gbc.gridy = 5;
		gbc.weighty = 0.8;
		pnlFormRight.add(txtAddress, gbc);
		
		gbc.gridy = 6;
		gbc.weighty = 0.2;
		pnlFormRight.add(pswPassword, gbc);
		
		gbc.gridy = 7;
		pnlFormRight.add(pswConfirm, gbc);
		
		lblAlready.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	            frame.setVisible(false);
	            new Login();
	         }
	    });
	}
	
	public boolean validation() {
		users = RegisterQuery.getUserListRegister();
		for(int i = 0; i < users.size(); i++) {
			if (txtUsername.getText().equals(users.get(i).getUserName())) {
				JOptionPane.showMessageDialog(frame, "Username already exist!", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			} 
		}
		if(txtUsername.getText().length() < 5 || txtUsername.getText().length() > 15) {
			JOptionPane.showMessageDialog(frame, "Username must between 5 - 15 character", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//Email
		if(txtEmail.getText().startsWith("@") && txtEmail.getText().contains("@") && txtEmail.getText().endsWith(".") &&
				txtEmail.getText().startsWith(".") && txtEmail.getText().endsWith("@") && txtEmail.getText().contains("@.")&&
				!txtEmail.getText().contains("."))
		{
			JOptionPane.showMessageDialog(frame, "Email must be [email]@[provider].[domain]", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//gender
		if (RBMale.getRadioButton().isSelected()) { 
            Gender = "Male";
        } else if (RBFemale.getRadioButton().isSelected()) {
            Gender = "Female";
        } else {
        	JOptionPane.showMessageDialog(frame, "Gender must be selected either “Male” or “Female”.", "Error", JOptionPane.ERROR_MESSAGE);
        	return false;
        }
		//DOB
//		if(!(txtDob1.getText().matches("\\d+") || !(txtDob2.getText().matches("\\d+") || !(txtDob3.getText().matches("\\d+")) ))) {
//			JOptionPane.showMessageDialog(frame, "Date of birthday invalid", "Error", JOptionPane.ERROR_MESSAGE);
//			return false;
//		}
		//Number Phone
		for(int i = 0; i < users.size(); i++) {
			if (txtPhonenumber.getText().equals(users.get(i).getUserPhoneNumber())) {
				JOptionPane.showMessageDialog(frame, "Phone number have been taken", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			} 
		}
		
		
		if(txtPhonenumber.getText().length() < 10 || txtPhonenumber.getText().length() > 12     ) {
			JOptionPane.showMessageDialog(frame, "Phone number must between 10 - 12 digits only", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//Address
		if(!(txtAddress.getText().endsWith("Street")) ) {
			JOptionPane.showMessageDialog(frame, "Address must ends with street", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//Password
		for (int i = 0; i < pswPassword.getText().length(); i++)
        {
            char c = pswPassword.getText().charAt(i);
            if (!(c >= 'A' && c <= 'Z') && (!(c >= 'a' && c <= 'z')) && (!(c >= '0' && c <= '9'))) {
            	done = false;
            }

        }
		System.out.println(done);
		if(done == true) {
			JOptionPane.showMessageDialog(frame, "Password must be alphanumeric" , "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if(pswPassword.getText().length() > 1) {
			
			if(pswPassword.getText().length() < 5 || pswPassword.getText().length() > 30) {
				JOptionPane.showMessageDialog(frame, "Password must between 5 - 30 " , "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		//confirmPassword
			if(!(pswConfirm.getText().equals(pswPassword.getText()))) {
				JOptionPane.showMessageDialog(frame, "Password must be same" , "Error", JOptionPane.ERROR_MESSAGE);
			}
		
		} else {
			JOptionPane.showMessageDialog(frame, "Password must be fill" , "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//checkbox
		if(!(checkbox1.isSelected())) {
			JOptionPane.showMessageDialog(frame, "Agree to Terms and Condition " , "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRegister) {
			if(validation() == true) {
				do {
					randomuserid = rand.nextInt(9999);
				}while(randomuserid < 1000);
				userID = "U" + randomuserid;
				userName = txtUsername.getText();
				userEmail = txtEmail.getText();
				userDOB = txtDob1.getText() +"-" + txtDob2.getText() + "-" + txtDob3.getText();
				userPhoneNumber = txtPhonenumber.getText();
				userAddress = txtAddress.getText();
				userPassword = pswPassword.getText();
				Role = "User";
				User user1 = new User(userID, userName, userEmail, userPassword, Gender, userDOB, userPhoneNumber, userAddress, Role);

				if(RegisterQuery.insertuser(user1)) {
					JOptionPane.showMessageDialog(frame, "Register Success!" , "Information", JOptionPane.INFORMATION_MESSAGE);
					frame.setVisible(false);
					new Login();
				}
			}
			
		}
	}
	
}
