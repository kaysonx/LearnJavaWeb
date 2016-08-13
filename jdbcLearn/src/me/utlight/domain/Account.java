package me.utlight.domain;

/**
 * @author liusha
 * Account  bean
 */
public class Account {

	private int id;
	private String name;
	private float money;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public float getMoney() {
		return money;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMoney(float money) {
		this.money = money;
	}
}
