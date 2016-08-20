package me.utlight.Spring.aop02;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//通知类，横切逻辑
@Component
@Aspect
public class Advices {
	
	@Before("execution(* me.utlight.Spring.aop02.Math.*(..))")
	public void before(JoinPoint jp){
		System.out.println("-----前置通知-----");
		System.out.println(jp.getSignature().getName());
	}
	
	@After("execution(* me.utlight.Spring.aop02.Math.*(..))")
	public void after(JoinPoint jp){
		System.out.println("------后置通知-------");
	}
}
