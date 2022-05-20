package gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import assets.ColorPalette;
import components.Button;
import components.CheckBox;
import components.Frame;
import components.HeadingLabel4;
import components.Panel;
import components.PasswordField;
import components.RadioButton;
import components.TextArea;
import components.TextField;
import database.RegisterQuery;
import database.UpdateProfileQuery;
import model.User;

public class UpdateProfile extends JFrame implements ActionListener{
//	Data
	Random rand = new Random();
	boolean done;
	private User user;
	int tempnumber;
//	Component
	Frame frame = new Frame(500,700,"CakeLAnd");
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
	HeadingLabel4 lblTitle = new HeadingLabel4("CakeLAnd"); 
	HeadingLabel4 lblCreate = new HeadingLabel4("Change Profile", ColorPalette.navy); 
	JLabel lblUsername = new JLabel("Username"); 
	JLabel lblEmail = new JLabel("Email");  
	JLabel lblGender = new JLabel("Gender"); 
	JLabel lblDob = new JLabel("DOB"); 
	JLabel lblDash1 = new JLabel("-");
	JLabel lblDash2 = new JLabel("-");
	JLabel lblPhone = new JLabel("Phone"); 
	JLabel lblAddress = new JLabel("Address"); 
	JLabel lblOldPassword = new JLabel("Old Password");
	JLabel lblPassword = new JLabel("New Password"); 
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
	PasswordField pswOldPassword = new PasswordField();
	PasswordField pswPassword = new PasswordField();
	PasswordField pswConfirm = new PasswordField();
	Button btnSave = new Button("Save Change");
	RadioButton RBMale = new RadioButton("Male");
	RadioButton RBFemale = new RadioButton("Female");
	CheckBox checkbox1 = new CheckBox();
	TextArea TaAddress = new TextArea(5,10);
	ButtonGroup G1 = new ButtonGroup();
	GridBagConstraints gbc = new GridBagConstraints();
	Vector<User> users = new Vector<>();
	
	public UpdateProfile(User user) {
		this.user = user;
		filled();
		
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
		pnlBtnRegister.add(btnSave);
		frame.add(pnlBtnRegister, gbc);
		
		btnSave.addActionListener(this);

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
		pnlFormLeft.add(lblOldPassword, gbc);
		
		gbc.gridy = 7;
		pnlFormLeft.add(lblPassword, gbc);
		
		gbc.gridy = 8;
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
		RBMale.getRadioButton().setEnabled(false);
		pnlRB.add(RBMale.getRadioButton(), gbc);
		gbc.gridx = 1;
		RBFemale.getRadioButton().setEnabled(false);
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
		pnlFormRight.add(pswOldPassword, gbc);
		
		gbc.gridy = 7;
		pnlFormRight.add(pswPassword,gbc);
		
		gbc.gridy = 8;
		pnlFormRight.add(pswConfirm, gbc);
		
	}
	
	public void filled() {
		users = RegisterQuery.getUserListRegister();
		
		txtUsername.setText(user.getUserName());
		txtEmail.setText(user.getUserEmail());
		txtDob1.setText(user.getUserDOB().substring(0,4));
		txtDob2.setText(user.getUserDOB().substring(5,7));
		txtDob3.setText(user.getUserDOB().substring(8,10));
		txtPhonenumber.setText(user.getUserPhoneNumber());
		txtAddress.setText(user.getUserAddress());
		//Tambah
		if(user.getUserGender().equals("Male")) {
			RBMale.getRadioButton().setSelected(true);
		} else if (user.getUserGender().equals("Female")) {
			RBFemale.getRadioButton().setSelected(true);
		}
		
		
	}
	
	public boolean validation() {
		//username
		for(int i = 0; i < users.size(); i++) {
			if(txtUsername.getText().equals(user.getUserName())) {
				
			} else if (txtUsername.getText().equals(users.get(i).getUserName())) {
				JOptionPane.showMessageDialog(this, "Username already exist!", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			} 
		}
		if(txtUsername.getText().length() < 5 || txtUsername.getText().length() > 15) {
			JOptionPane.showMessageDialog(this, "Username must between 5 - 15 character", "Error", JOptionPane.ERROR_MESSAGE);
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

		//DOB
//		if(!(txtDob1.getText().matches("\\d+") || !(txtDob2.getText().matches("\\d+") || !(txtDob3.getText().matches("\\d+")) ))) {
//			JOptionPane.showMessageDialog(this, "Date of birthday invalid", "Error", JOptionPane.ERROR_MESSAGE);
//			return false;
//		}
		//Number Phone edit
		
		for(int i = 0; i < users.size(); i++) {
		if(txtPhonenumber.getText().equals(user.getUserPhoneNumber())) {
			
		}else if (txtPhonenumber.getText().equals(users.get(i).getUserPhoneNumber())) {
			JOptionPane.showMessageDialog(this, "Phone Number already exist", "Error", JOptionPane.ERROR_MESSAGE);
		}
		System.out.println(user.getUserPhoneNumber());
		}
//		try {
//			tempnumber = Integer.parseInt(txtPhonenumber.getText());
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(frame, "Phone number must between 10 - 12 digits only", "Error", JOptionPane.ERROR_MESSAGE);
//		}
		if(txtPhonenumber.getText().length() < 10 || txtPhonenumber.getText().length() > 12     ) {
			JOptionPane.showMessageDialog(this, "Phone number must between 10 - 12 digits only", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//Address
		if(!(txtAddress.getText().endsWith("Street")) ) {
			JOptionPane.showMessageDialog(this, "Address must ends with street", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!(pswPassword.getText().toString().isEmpty())){
			
			
		
		//old password
		if(!(pswOldPassword.getText().equals(user.getUserPassword()))) {
			JOptionPane.showMessageDialog(this, "Password must be same" , "Error", JOptionPane.ERROR_MESSAGE);
			return false;
			}
		//New Password
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
			
		//confirmPassword
			if(!(pswConfirm.getText().equals(pswPassword.getText()))) {
			JOptionPane.showMessageDialog(this, "Password must be same" , "Error", JOptionPane.ERROR_MESSAGE);
			
			}
		} else {
			JOptionPane.showMessageDialog(this, "Password must be fill" , "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSave) {
			validation();
		
		
		if(validation()) {
			String userPassword;
			String userID = user.getUserID();
			String userName = txtUsername.getText();
			String userEmail = txtEmail.getText();
			String Gender = user.getUserGender();
			String userDOB = txtDob1.getText() +"-" + txtDob2.getText() + "-" + txtDob3.getText();
			String userPhoneNumber = txtPhonenumber.getText();
			String userAddress = txtAddress.getText();
			if(!(pswPassword.getText().toString().isEmpty())) {
				userPassword = pswPassword.getText();	
			} else {
				userPassword = user.getUserPassword();	
			}
			String Role = user.getUserRole();
			User user = new User(userID, userName, userEmail, userPassword, Gender, userDOB, userPhoneNumber, userAddress, Role);
		
			if(UpdateProfileQuery.Updateuser(user)) {
				JOptionPane.showMessageDialog(this, "Update Success!" , "Information", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		}
		
	}
	

}
