package me.utlight.spring.ioc3;


import org.springframework.stereotype.Repository;

//ͨ��ע��ķ�ʽʵ��ע��
@Repository
public class BookDAO implements IBookDAO{
	public String addBook(String bookname){
		
		return "���ͼ��" + bookname + "�ɹ�!";
	}
}
