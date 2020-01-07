package com.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;



@Aspect
public class LoggingAspect {
	/** Following is the definition for a pointcut to select
	    *  all the methods available. So advice will be called
	    *  for all the methods.
	    */
	   @Pointcut("execution(* com.*.*.*(..))")
	   private void selectAll(){}

	   /** 
	    * This is the method which I would like to execute
	    * before a selected method execution.
	    */
	   @Before("selectAll()")
	   public void beforeAdvice(){
	      System.out.println("AOP : Going to setup student profile.");
	   }

	   /** 
	    * This is the method which I would like to execute
	    * after a selected method execution.
	    */
	   @After("selectAll()")
	   public void afterAdvice(){
	      System.out.println("AOP : Student profile has been setup.");
	   }

	   /** 
	    * This is the method which I would like to execute
	    * when any method returns.
	    */
	   @AfterReturning(pointcut = "selectAll()", returning="retVal")
	   public void afterReturningAdvice(Object retVal){
	      System.out.println("AOP : Returning:" + retVal);
	   }

	   /**
	    * This is the method which I would like to execute
	    * if there is an exception raised by any method.
	    */
	   @AfterThrowing(pointcut = "selectAll()", throwing = "ex")
	   public void AfterThrowingAdvice(Exception ex){
		   ex.printStackTrace();
	      System.out.println("AOP : There has been an exception: " + ex.toString());   
	   }
}
