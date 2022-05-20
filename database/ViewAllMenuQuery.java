package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Cake;
import model.Cart;
import model.User;

public class ViewAllMenuQuery {
	
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
	
	public static Vector<Cart> getCartList(String userId){
		Vector<Cart> carts = new Vector<>();
		String query = "SELECT * FROM cart " +
					   "WHERE cart.UserID = ? ";
		
		PreparedStatement ps = conn.prepareStatement(query);
		
		String userID, cakeID;
		int quantity;
		
		try {
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				userID = rs.getString("userID");
				cakeID = rs.getString("cakeID");
				quantity = rs.getInt("quantity");
				carts.add(new Cart(userID, cakeID, quantity));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return carts;
	}
	
	public static boolean addToCart(Cart cart) {
		
		String query = "INSERT INTO cart VALUES (?,?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		
		try {
			ps.setString(1, cart.getUserID());
			ps.setString(2, cart.getCakeID());
			ps.setInt(3, cart.getQuantity());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
