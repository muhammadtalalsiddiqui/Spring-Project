package com.dataproviderservice.Aspect;


import com.google.common.base.Stopwatch;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
        @Before("within(com.dataproviderservice.Controller.EmployeeController)")
    public void before() {

            System.out.println("Start-----------------------------------------------------> ");

        }
    @After("execution (* com.dataproviderservice.Controller.EmployeeController.employee())")
    public void after()
    {

        System.out.println("Stop-----------------------------------------------------> ");
    }
}
