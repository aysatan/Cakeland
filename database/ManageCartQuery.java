package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gui.ManageCart;
import model.CakeOnCart;

public class ManageCartQuery {

	public static Vector<CakeOnCart> getCakeOnCartList(String userId) {
		Vector<CakeOnCart> vCake = new Vector<>();
		
		PreparedStatement ps = DatabaseConnector.getConnection().prepareStatement(
				"SELECT cake.CakeID, cake.CakeName, cake.CakeShape, cake.CakeSize, cake.CakePrice, cart.Quantity FROM cake JOIN cart ON cake.CakeID = cart.CakeID WHERE UserID = ?"
				);
		
		try {
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String cakeID = rs.getString("CakeID");
				String cakeName = rs.getString("CakeName");
				String cakeShape = rs.getString("CakeShape");
				String cakeSize = rs.getString("CakeSize");
				int cakePrice = rs.getInt("CakePrice");
				int quantity = rs.getInt("Quantity");
				vCake.add(new CakeOnCart(cakeID, cakeName, cakeShape, cakeSize, cakePrice, quantity));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return vCake;
	}
	
	public static boolean removeCake(String userId){
		String query = "DELETE FROM cart WHERE cart.CakeID = ? AND cart.UserID = ?";
		PreparedStatement ps = DatabaseConnector.getConnection().prepareStatement(query);
		try {
			ps.setString(1, ManageCart.selectedID);
			ps.setString(2, userId);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public static boolean updateCake(String userId, int spinnerQtyValue) {
		
		String query = "UPDATE cart SET Quantity = ? WHERE cart.CakeID = ? AND cart.UserID = ?";
		PreparedStatement ps = DatabaseConnector.getConnection().prepareStatement(query);
		
		try {
			ps.setInt(1, spinnerQtyValue);
			ps.setString(2, ManageCart.selectedID);
			ps.setString(3, userId);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
