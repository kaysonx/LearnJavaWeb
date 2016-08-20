package me.utlight.spring.ioc4;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class BookService {
	//自动装配
	//@Autowired
	@Resource
	IBookDAO bookDAO;

	public void storeBook(String bookname){
		//容器
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("IOCBeans02.xml");
//		this.bookDAO = (IBookDAO)ctx.getBean("bookdao");
		System.out.println("图书上货了...");
		String result = bookDAO.addBook(bookname);
		System.out.println(result);
	}
	
}
