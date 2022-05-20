package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.User;

public class RegisterQuery {
	static DatabaseConnector conn = DatabaseConnector.getConnection();
	
	public static boolean insertuser(User user){
		String query = "INSERT INTO user VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		try {
			ps.setString(1, user.getUserID());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserEmail());
			ps.setString(4, user.getUserPassword());
			ps.setString(5, user.getUserGender());
			ps.setString(6, user.getUserDOB());
			ps.setString(7, user.getUserPhoneNumber());
			ps.setString(8, user.getUserAddress());
			ps.setString(9, user.getUserRole());
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;

	}
	
	public static Vector<User> getUserListRegister(){
		Vector<User> users = new Vector<>();
		String query = "SELECT * FROM user";
		PreparedStatement ps = conn.prepareStatement(query);
		String userID, userName, userEmail, userPassword, userGender, userDOB, userPhoneNumber, userAddress, userRole;
		
		try {
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
