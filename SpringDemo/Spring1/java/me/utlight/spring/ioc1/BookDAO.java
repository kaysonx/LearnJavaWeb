package me.utlight.spring.ioc1;

public class BookDAO implements IBookDAO{
	public String addBook(String bookname){
		
		return "添加图书" + bookname + "成功!";
	}
}
