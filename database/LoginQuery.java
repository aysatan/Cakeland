package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gui.ManageMenu;
import model.User;

public final class LoginQuery {

	static DatabaseConnector conn = DatabaseConnector.getConnection();
	
	public static Vector<User> getUserList(String username, String password){
		Vector<User> users = new Vector<>();
		String query = "SELECT * FROM user " +
					   "WHERE user.Username = ? " +
					   "AND user.UserPassword = ? ";
		PreparedStatement ps = conn.prepareStatement(query);
		String userID, userName, userEmail, userPassword, userGender, userDOB, userPhoneNumber, userAddress, userRole;
		
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				userID = rs.getString("userID");
				userName = rs.getString("userName");
				userEmail = rs.getString("userEmail");
				userPassword = rs.getString("userPassword");
				userGender = rs.getString("userGender");
				userDOB = rs.getString("userDOB");
				userPhoneNumber = rs.getString("userPhoneNumber");
				userAddress = rs.getString("userAddress");
				userRole = rs.getString("userRole");
				users.add(new User(userID, userName, userEmail, userPassword, userGender, userDOB, userPhoneNumber, userAddress, userRole));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return users;
	}

}
