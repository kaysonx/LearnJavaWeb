package me.utlight.spring.ioc2;

import org.springframework.stereotype.Component;

//ͨ��ע��ķ�ʽʵ��ע��
@Component("bookdao")
public class BookDAO implements IBookDAO{
	public String addBook(String bookname){
		
		return "���ͼ��" + bookname + "�ɹ�!";
	}
}
