package me.utlight.spring.ioc3;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringIOC {

	@Test
	public void testStoreBook() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("IOCBeans03.xml");
		//������ȥƥ�����
		BookService service = ctx.getBean(BookService.class);
		service.storeBook("spring����3");
	}

}
