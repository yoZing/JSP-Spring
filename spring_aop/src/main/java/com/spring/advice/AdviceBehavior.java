package com.spring.advice;

import org.aspectj.lang.ProceedingJoinPoint;

public class AdviceBehavior {
	
	public void chikachika() {
		System.out.println("양치는 열심히!!!!!!!!!!!!!");
	}
	
	// joinpoint : arround
	public void chikachikaArround(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("한번 닦았는데");
		
		joinPoint.proceed();
		
		System.out.println("또 닦아요!!!!!!!!");
	}
}
