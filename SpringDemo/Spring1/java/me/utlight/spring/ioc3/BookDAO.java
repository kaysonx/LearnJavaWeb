package me.utlight.spring.ioc3;


import org.springframework.stereotype.Repository;

//通过注解的方式实现注入
@Repository
public class BookDAO implements IBookDAO{
	public String addBook(String bookname){
		
		return "添加图书" + bookname + "成功!";
	}
}
