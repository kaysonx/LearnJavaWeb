package me.utlight.spring.ioc4;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringIOC {

	@Test
	public void testStoreBook() {
		
		//ʹ������������������
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationCfg.class);
		//������ȥƥ�����
		BookService service = ctx.getBean(BookService.class);
		service.storeBook("spring����4");
		//user1��ͨ��ע�����õ�
		User user1 = ctx.getBean("user1",User.class);
		user1.show();
		//getUserʵ�������������õ�
		User getUser = ctx.getBean("getUser",User.class);
		getUser.show();
		System.out.println(user1 == getUser);
	}

}
