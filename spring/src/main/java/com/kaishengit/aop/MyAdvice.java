package com.kaishengit.aop;

/**
 * Created by Administrator on 2016/7/1.
 */

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * aop通知类
 */
public class MyAdvice {
    public void beforeAdvice(){
        System.out.println("前置通知");
    }
    public void afterAdvice(Object result){
        System.out.println("后置通知"+result);
    }
    public void exceptionAdvice(Exception e){
        System.out.println("异常通知"+e.getMessage());
//     e.getMessage()获取的是方法异常时候传的字符串。
    }
    public void lastAdvice(){
        System.out.println("最终通知");
    }

    /**
     *  环绕通知
     * @param joinPoint
     */
    public Object aroundAdvice(ProceedingJoinPoint joinPoint){
        Object object=null;
        try {
            System.out.println("前置通知");
            object=joinPoint.proceed();//代表了方法的执行
            System.out.println("后置通知"+object);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("异常通知");
        }finally {
            System.out.println("最终通知");
        }
        return object;
    }

}
