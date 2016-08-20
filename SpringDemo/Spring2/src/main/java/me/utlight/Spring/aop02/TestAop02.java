package me.utlight.Spring.aop02;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop02 {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop02.xml");
		Math math = ctx.getBean("math",Math.class);
		
		int n1 = 100, n2 =  2;
		math.add(n1, n2);
		math.sub(n1, n2);
		math.mut(n1, n2);
		math.div(n1, n2);
	}

}
