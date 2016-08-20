package me.utlight.Spring.aop03;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//通知类，横切逻辑
@Component
@Aspect
public class Advices {
	
	@Before("execution(* me.utlight.Spring.aop03.Math.*(..))")
	public void before(JoinPoint jp){
		System.out.println("-----前置通知-----");
		System.out.println(jp.getSignature().getName());
	}
	//execution切点函数
//	@After("execution(* me.utlight.Spring.aop03.Math.*(..))")
//	public void after(JoinPoint jp){
//		System.out.println("------后置通知-------");
//	}
	
	//@annotation切点函数
//	@After("@annotation(me.utlight.Spring.aop03.MyAnno)")
//	public void after(JoinPoint jp){
//		System.out.println("------后置通知-------");
//	}
//	
	//within切点函数
	//me.utlight.Spring.aop03下的所有类的所有方法被切入
//	@After("within(me.utlight.Spring.aop03.*)")
//	public void after(JoinPoint jp){
//		System.out.println("------后置通知-------");
//	}
	
	//this切点函数
	//实现了IMth接口的代理对象的任意连接点
//	@After("this(me.utlight.Spring.aop03.IMath)")
//	public void after(JoinPoint jp){
//		System.out.println("------后置通知-------");
//	}
//	
	//args切点函数
	@After("args(int,int)")
	public void after(JoinPoint jp){
		System.out.println("------后置通知-------");
	}
//	
//	@After("@annotation(me.utlight.Spring.aop03.MyAnno)")
//	public void after(JoinPoint jp){
//		System.out.println("------后置通知-------");
//	}
	
}
