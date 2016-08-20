package me.utlight.spring.ioc2;

import org.springframework.stereotype.Component;

//通过注解的方式实现注入
@Component("bookdao")
public class BookDAO implements IBookDAO{
	public String addBook(String bookname){
		
		return "添加图书" + bookname + "成功!";
	}
}
