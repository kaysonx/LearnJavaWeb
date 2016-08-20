package me.utlight.spring.ioc2;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringIOC {

	@Test
	public void testStoreBook() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("IOCBeans02.xml");
		//������ȥƥ�����
		BookService service = ctx.getBean(BookService.class);
		service.storeBook("spring����2");
	}

}
