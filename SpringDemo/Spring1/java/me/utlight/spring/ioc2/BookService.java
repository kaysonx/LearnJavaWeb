package me.utlight.spring.ioc2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class BookService {
	IBookDAO bookDAO;

	public void storeBook(String bookname){
		//容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("IOCBeans02.xml");
		this.bookDAO = (IBookDAO)ctx.getBean("bookdao");
		System.out.println("图书上货了...");
		String result = bookDAO.addBook(bookname);
		System.out.println(result);
	}
	
}
