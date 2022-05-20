package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.TransactionHistoryDetail;
import model.TransactionHistoryHeader;

public final class TransactionFormQuery {
	
	static DatabaseConnector conn = DatabaseConnector.getConnection();
	
	public Vector<TransactionHistoryHeader> getTransactionHeaderList(String userId) {
		Vector<TransactionHistoryHeader> datas = new Vector<>();
		String query = "SELECT * FROM TransactionHeader WHERE UserId = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		String transactionId, transactionDate, pickupDate;
		Integer cakePrice;
		
		try {
			ps.setString(1,userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				transactionId = rs.getString("TransactionID");
				transactionDate = rs.getString("TransactionDate");
				pickupDate = rs.getString("PickUpDate");
				datas.add(new TransactionHistoryHeader(transactionId, transactionDate, pickupDate));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return datas;
	}
	
	public Vector<TransactionHistoryDetail> getTransactionDetailList(String transactionId) {
		Vector<TransactionHistoryDetail> datas = new Vector<>();
		String query = "SELECT cake.CakeName, " +
					   		  "cake.CakeSize, " +
					   		  "cake.CakeShape, " +
					   		  "cake.CakePrice, " +
					   		  "transactiondetail.Quantity, " +
					   		  "cake.CakePrice*transactiondetail.Quantity AS Subtotal " +
					   "FROM cake JOIN transactionDetail " +
					   "ON cake.CakeID = transactionDetail.CakeID " +
					   "WHERE TransactionDetail.TransactionID = ?";
		PreparedStatement ps = conn.prepareStatement(query);

		String cakeName, cakeSize, cakeShape;
		int cakePrice, quantity, subtotal;
		
		try {
			ps.setString(1,transactionId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				cakeName = rs.getString("CakeName");
				cakeSize = rs.getString("CakeSize");
				cakeShape = rs.getString("CakeShape");
				cakePrice = rs.getInt("CakePrice");
				quantity = rs.getInt("Quantity");
				subtotal = rs.getInt("Subtotal"); 
				datas.add(new TransactionHistoryDetail(cakeName, cakeSize, cakeShape, cakePrice, quantity, subtotal)); 
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return datas;
	}
	
	public TransactionFormQuery() {
		// TODO Auto-generated constructor stub
	}

}
