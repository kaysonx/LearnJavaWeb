package me.utlight.spring.ioc4;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringIOC {

	@Test
	public void testStoreBook() {
		
		//使用配置类来配置容器
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationCfg.class);
		//用类型去匹配对象
		BookService service = ctx.getBean(BookService.class);
		service.storeBook("spring入门4");
		//user1是通过注解配置的
		User user1 = ctx.getBean("user1",User.class);
		user1.show();
		//getUser实在配置类中配置的
		User getUser = ctx.getBean("getUser",User.class);
		getUser.show();
		System.out.println(user1 == getUser);
	}

}
