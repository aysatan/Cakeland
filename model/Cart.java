package model;

public final class Cart {
	
	private String userID;
	private String cakeID;
	private int quantity;
	
	public Cart(String userID, String cakeID, int quantity) {
		super();
		this.userID = userID;
		this.cakeID = cakeID;
		this.quantity = quantity;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getCakeID() {
		return cakeID;
	}

	public void setCakeID(String cakeID) {
		this.cakeID = cakeID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
