package me.utlight.Spring.aop01;

import org.aspectj.lang.JoinPoint;

//通知类，横切逻辑
//使用配置文件的方式配置
public class Advices {
	
	public void before(JoinPoint jp){
		System.out.println("-----前置通知-----");
		System.out.println(jp.getSignature().getName());
	}
	
	public void after(JoinPoint jp){
		System.out.println("------后置通知-------");
	}
}
