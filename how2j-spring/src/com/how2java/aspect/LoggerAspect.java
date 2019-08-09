package com.how2java.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	
	@Around(value = "execution(* com.how2java.service.ProductService.*(..))")
	public Object log(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("start log:"+jp.getSignature().getName());
		Object obj = jp.proceed();
		System.out.println("end log:" + jp.getSignature().getName());
		return obj;
		
	}
}
