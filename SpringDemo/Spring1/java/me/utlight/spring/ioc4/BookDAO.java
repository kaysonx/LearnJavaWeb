package me.utlight.spring.ioc4;


import org.springframework.stereotype.Repository;

//ͨ��ע��ķ�ʽʵ��ע��
@Repository
public class BookDAO implements IBookDAO{
	
	public BookDAO() {
		System.out.println(this.getClass().getName()+"ʵ������...");
	}
	public String addBook(String bookname){
		
		return "���ͼ��" + bookname + "�ɹ�!";
	}
}
