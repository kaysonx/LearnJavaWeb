package me.utlight.spring.ioc4;

import org.springframework.stereotype.Component;

@Component("user1")
public class User {
	
	public User() {
		System.out.println("����User����...");
		
	}
	
	public User(String msg){
		System.out.println("����User����"+msg);
	}
	
	public void show(){
		System.out.println("һ��User����");
	}
}
