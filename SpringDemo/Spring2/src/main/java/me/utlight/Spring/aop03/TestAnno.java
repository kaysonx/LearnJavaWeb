package me.utlight.Spring.aop03;

import org.springframework.stereotype.Component;

@Component("anno")
public class TestAnno {
	@MyAnno
	public void show(){
		System.out.println("Hello Anno!");
	}
}
