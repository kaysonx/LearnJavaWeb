package me.utlight.entity;

public class Order {

	private int id; // ��Ӧorder_id
	private String orderNo;//��Ӧorder_no
	private float price;//��Ӧorder_price
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNo=" + orderNo + ", price=" + price + "]";
	}
	
	
	
}	
