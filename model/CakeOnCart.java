package model;

public final class CakeOnCart {

	private String cakeID;
	private String cakeName;
	private String cakeShape;
	private String cakeSize;
	private int cakePrice;
	private int quantity;
	
	public CakeOnCart(String cakeID, String cakeName, String cakeShape, String cakeSize, int cakePrice, int quantity) {
		super();
		this.cakeID = cakeID;
		this.cakeName = cakeName;
		this.cakeShape = cakeShape;
		this.cakeSize = cakeSize;
		this.cakePrice = cakePrice;
		this.quantity = quantity;
	}
	
	public String getCakeID() {
		return cakeID;
	}

	public void setCakeID(String cakeID) {
		this.cakeID = cakeID;
	}

	public String getCakeName() {
		return cakeName;
	}

	public void setCakeName(String cakeName) {
		this.cakeName = cakeName;
	}

	public String getCakeShape() {
		return cakeShape;
	}

	public void setCakeShape(String cakeShape) {
		this.cakeShape = cakeShape;
	}

	public String getCakeSize() {
		return cakeSize;
	}

	public void setCakeSize(String cakeSize) {
		this.cakeSize = cakeSize;
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
	
}
