package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gui.ManageCart;
import model.CakeOnCart;

public class ConfirmCheckOutQuery {
	
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
	
	public static boolean addTransactionHistoryDetail(String transactionId, Vector<CakeOnCart> cakes) {
		String query = "INSERT INTO transactiondetail VALUES ";
		try {
			int index = 0;
			for (int i = 0; i < cakes.size(); i++) {
				String values = "";
				values += "("+  transactionId + "," + cakes.get(i).getCakeID()+ "," + cakes.get(i).getQuantity() +")";
				query+=values;
				if (i != cakes.size()-1) {
					query+=", ";
				}
				System.out.println(query);
			}
			PreparedStatement ps = DatabaseConnector.getConnection().prepareStatement(query);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public static boolean addTransactionHistoryHeader(String transactionId, String userId, String transactionDate, String pickupDate) {
		String query = "INSERT INTO transactionheader VALUES (?,?,?,?)";
		PreparedStatement ps = DatabaseConnector.getConnection().prepareStatement(query);
		try {
			System.out.println(transactionDate);
			System.out.println(pickupDate);
			ps.setString(1, transactionId);
			ps.setString(2, userId);
			ps.setString(3, transactionDate);
			ps.setString(4, pickupDate);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public static boolean removeAllCake(String userId){
		String query = "DELETE FROM cart WHERE cart.UserID = ?";
		PreparedStatement ps = DatabaseConnector.getConnection().prepareStatement(query);
		try {
			ps.setString(1, userId);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

}
