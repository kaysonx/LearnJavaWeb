package me.utlight.spring.ioc1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookService {
	IBookDAO bookDAO;
	public BookService() {
		//����
		ApplicationContext ctx = new ClassPathXmlApplicationContext("IOCBeans01.xml");
		this.bookDAO = (IBookDAO)ctx.getBean("bookdao");
	}

	public void storeBook(String bookname){
		System.out.println("ͼ���ϻ���...");
		String result = bookDAO.addBook(bookname);
		System.out.println(result);
	}
	
}
