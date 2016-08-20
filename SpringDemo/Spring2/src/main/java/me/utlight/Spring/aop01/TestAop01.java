package me.utlight.Spring.aop01;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop01 {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop01.xml");
		Math math = ctx.getBean("math",Math.class);
		
		int n1 = 100, n2 =  2;
		math.add(n1, n2);
		math.sub(n1, n2);
		math.mut(n1, n2);
		math.div(n1, n2);
	}

}
