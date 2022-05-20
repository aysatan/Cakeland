package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gui.ManageMenu;
import model.Cake;

public final class ManageMenuQuery {
	
	static DatabaseConnector conn = DatabaseConnector.getConnection();
	
	public static Vector<Cake> getCakeList(){
		Vector<Cake> cakes = new Vector<>();
		String query = "SELECT * FROM cake";
		ResultSet rs = conn.executeQuery(query);
		
		String cakeID, cakeName, cakeShape, cakeSize;
		Integer cakePrice;
		
		try {
			while(rs.next()){
				cakeID = rs.getString("CakeID");
				cakeName = rs.getString("CakeName");
				cakeShape = rs.getString("CakeShape");
				cakeSize = rs.getString("CakeSize");
				cakePrice = rs.getInt("CakePrice");
				cakes.add(new Cake(cakeID, cakeName, cakeSize, cakeShape, cakePrice));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return cakes;
	}

	
	public static boolean insertCake(String cakeID, String cakeName, Integer cakePrice, String cakeSize, String cakeShape ){
		String query = "INSERT INTO cake VALUES (?, ?, ?, ?, ?)";
	
		PreparedStatement ps = conn.prepareStatement(query);
		try {
			ps.setString(1, cakeID);
			ps.setString(2, cakeName);
			ps.setInt(3, cakePrice);
			ps.setString(4, cakeSize);
			ps.setString(5, cakeShape);	
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	
	public static boolean removeCake(){
		String query = "DELETE FROM cake WHERE cakeID = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		try {
			ps.setString(1, ManageMenu.selectedCode);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

}
