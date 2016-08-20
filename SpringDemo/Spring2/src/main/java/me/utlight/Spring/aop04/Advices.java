package me.utlight.Spring.aop04;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//通知类，横切逻辑
@Component
@Aspect

public class Advices {
	
	//切点 方便复用
	@Pointcut("execution(* me.utlight.Spring.aop04.Math.a*(..))")
	public void pointcut(){
		
	}
	
	//前置通知
	@Before("pointcut()")
	public void before(JoinPoint jp){
		System.out.println("-----前置通知-----");
		System.out.println(jp.getSignature().getName());
	}
	
	//后置通知
	@After("pointcut()")
	public void after(JoinPoint jp){
		System.out.println("------后置通知-------");
	}
	
	//环绕通知
	@Around("execution(* me.utlight.Spring.aop04.Math.s*(..))")
	public Object arount(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println(pjp.getSignature().getName());
		System.out.println("环绕前置------------");
		Object result = pjp.proceed();
		System.out.println("环绕后置------------");
		return result;
	}
	
	//返回结果通知
	@AfterReturning(pointcut="execution(* me.utlight.Spring.aop04.Math.m*(..))",returning="result")
	public void afterReturning(JoinPoint jp, Object result){
		
		System.out.println(jp.getSignature().getName());
		System.out.println("结果是："+result);
		System.out.println("返回结果------------");
	}
	
	@AfterThrowing(pointcut="execution(* me.utlight.Spring.aop04.Math.d*(..))",throwing="exp")
	public void afterThrowing(JoinPoint jp, Exception exp){
		System.out.println(jp.getSignature().getName());
		System.out.println("异常消息:"+exp.getMessage());
		System.out.println("-----------异常通知");
	}
}
