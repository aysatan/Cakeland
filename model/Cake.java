package model;

public final class Cake {
	private String cakeID, cakeName, cakeSize, cakeShape;
	private Integer cakePrice;
	
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
	public Integer getCakePrice() {
		return cakePrice;
	}
	public void setCakePrice(Integer cakePrice) {
		this.cakePrice = cakePrice;
	}
	
	public Cake(String cakeID, String cakeName, String cakeSize, String cakeShape, Integer cakePrice) {
		super();
		this.cakeID = cakeID;
		this.cakeName = cakeName;
		this.cakeSize = cakeSize;
		this.cakeShape = cakeShape;
		this.cakePrice = cakePrice;
	}

}
