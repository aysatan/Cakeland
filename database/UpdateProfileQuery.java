package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gui.UpdateProfile;
import model.User;

public class UpdateProfileQuery {
	static DatabaseConnector conn = DatabaseConnector.getConnection();
	
	public static boolean Updateuser(User user){
		String query = "UPDATE user SET Username = ? , UserEmail = ? , UserPassword = ?, UserDOB = ? , UserPhoneNumber = ? , UserGender = ?  , UserAddress =  ?, UserRole = ?  WHERE"
				+ " UserID = ? ";
		PreparedStatement ps = conn.prepareStatement(query);
		try {
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserEmail());
			ps.setString(3, user.getUserPassword());
			ps.setString(4, user.getUserDOB());
			ps.setString(5, user.getUserPhoneNumber());
			ps.setString(6, user.getUserGender());
			ps.setString(7, user.getUserAddress());
			ps.setString(8, user.getUserRole());
			ps.setString(9, user.getUserID());
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;

	}
	
}
