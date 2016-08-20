package me.utlight.spring.ioc2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class BookService {
	IBookDAO bookDAO;

	public void storeBook(String bookname){
		//����
		ApplicationContext ctx = new ClassPathXmlApplicationContext("IOCBeans02.xml");
		this.bookDAO = (IBookDAO)ctx.getBean("bookdao");
		System.out.println("ͼ���ϻ���...");
		String result = bookDAO.addBook(bookname);
		System.out.println(result);
	}
	
}
