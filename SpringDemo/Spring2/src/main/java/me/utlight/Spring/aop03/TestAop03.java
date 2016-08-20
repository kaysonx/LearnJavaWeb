package me.utlight.Spring.aop03;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop03 {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop03.xml");
		IMath math = ctx.getBean("math",Math.class);
		
		int n1 = 100, n2 =  2;
		math.add(n1, n2);
		math.sub(n1, n2);
		math.mut(n1, n2);
		math.div(n1, n2);
		
		TestAnno anno = ctx.getBean("anno", TestAnno.class);
		anno.show();
	}

}
