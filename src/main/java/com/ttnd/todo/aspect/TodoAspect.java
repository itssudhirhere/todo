package com.ttnd.todo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class TodoAspect {
	/*
    @Before("execution(public void display())")
    public void someCalculation(){
        System.out.println("===doSomething===");
    }

    @After("execution(public void display())")
    public void afterAdvice(){
        System.out.println("===after advice===");
    }

    @Before("execution(public * get*())")
    public void beforeAdvice(){
        System.out.println("===before getter--advice===");
    }


    @Around("execution(void display())")
    void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Around before");
        proceedingJoinPoint.proceed();
        System.out.println("Around after");
    }


    @AfterReturning(pointcut = "execution(void display())")
    void afterReturningAdvice() {
        System.out.println("Running AfterReturning " );
    }



    @AfterThrowing(pointcut = "execution(void display())", throwing = "ex")
    void afterReturningAdvice(Exception ex) {
        System.out.println("Running AfterThrowing " + ex);
    }

*/


   /* @Before("within(com.spring.demo.*)")
    public void beforeAdvice(){
        System.out.println("===before advice within===");
    }
    @Before("args(Integer )")
    public void beforeAdvicearg(){
        System.out.println("===before hhhhhhhhh within===");
    }
*/
    @Before("bean(sessionData)")
    public void beforeAdviceBean(){
        System.out.println("===before bean ceation within=sessionData==");
    }

}
