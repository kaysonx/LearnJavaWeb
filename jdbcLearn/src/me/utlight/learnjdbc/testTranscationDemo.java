package me.utlight.learnjdbc;

public class testTranscationDemo {
	public static void main(String[] args) {
		testTransaction transaction = new testTransaction();
		System.out.println("测试事务开始....");
		
		transaction.findAll();
		
		System.out.println("fn:testTransction01.....");
		transaction.testTransaction01();
		transaction.findAll();
		
		
		System.out.println("fn:testTransaction02.....");
		transaction.testTransaction02();
		transaction.findAll();
		
		
		System.out.println("fn:testTransaction03.....");
		transaction.testTransaction03();
		transaction.findAll();
		
		
		System.out.println("fn:testTransaction04.....");
		transaction.testTransaction04();
		transaction.findAll();
		
	}
}
