package me.utlight.spring.ioc4;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class BookService {
	//�Զ�װ��
	//@Autowired
	@Resource
	IBookDAO bookDAO;

	public void storeBook(String bookname){
		//����
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("IOCBeans02.xml");
//		this.bookDAO = (IBookDAO)ctx.getBean("bookdao");
		System.out.println("ͼ���ϻ���...");
		String result = bookDAO.addBook(bookname);
		System.out.println(result);
	}
	
}
