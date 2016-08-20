package me.utlight.Spring.aop05;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class TestAop05 {
	
	public static void main(String[] args){
		
		// 通过类初始化容器
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationCfg.class);
		Math math = ctx.getBean("math", Math.class);
		int n1 = 100, n2 = 0;
		math.add(n1, n2);
		math.sub(n1, n2);
		math.mut(n1, n2);
		try {
			math.div(n1, n2);
		} catch (Exception e) {
		}
		
		User user=ctx.getBean("getUser",User.class);
		user.show();
	}

}
