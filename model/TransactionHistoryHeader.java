package model;

public final class TransactionHistoryHeader {
	private String transactionId;
	private String transactionDate;
	private String pickupDate;
	
	public TransactionHistoryHeader(String transactionId, String transactionDate, String pickupDate) {
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.pickupDate = pickupDate;
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}
	
}
