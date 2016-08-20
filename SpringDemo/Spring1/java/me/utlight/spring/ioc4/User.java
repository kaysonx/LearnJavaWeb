package me.utlight.spring.ioc4;

import org.springframework.stereotype.Component;

@Component("user1")
public class User {
	
	public User() {
		System.out.println("创建User对象...");
		
	}
	
	public User(String msg){
		System.out.println("创建User对象"+msg);
	}
	
	public void show(){
		System.out.println("一个User对象");
	}
}
