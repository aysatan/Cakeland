package model;

public final class TransactionHistoryDetail {
	private String cakeName;
	private String cakeSize;
	private String cakeShape;
	private int cakePrice;
	private int quantity;
	private int subtotal;
	
	public TransactionHistoryDetail(String cakeName, String cakeSize, String cakeShape, int cakePrice, int quantity, int subtotal) {
		this.cakeName = cakeName;
		this.cakeSize = cakeSize;
		this.cakeShape = cakeShape;
		this.cakePrice = cakePrice;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	public String getCakeName() {
		return cakeName;
	}

	public void setCakeName(String cakeName) {
		this.cakeName = cakeName;
	}

	public String getCakeSize() {
		return cakeSize;
	}

	public void setCakeSize(String cakeSize) {
		this.cakeSize = cakeSize;
	}

	public String getCakeShape() {
		return cakeShape;
	}

	public void setCakeShape(String cakeShape) {
		this.cakeShape = cakeShape;
	}

	public int getCakePrice() {
		return cakePrice;
	}

	public void setCakePrice(int cakePrice) {
		this.cakePrice = cakePrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

}
