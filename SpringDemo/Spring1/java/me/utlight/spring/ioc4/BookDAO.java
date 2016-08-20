package me.utlight.spring.ioc4;


import org.springframework.stereotype.Repository;

//通过注解的方式实现注入
@Repository
public class BookDAO implements IBookDAO{
	
	public BookDAO() {
		System.out.println(this.getClass().getName()+"实例化了...");
	}
	public String addBook(String bookname){
		
		return "添加图书" + bookname + "成功!";
	}
}
