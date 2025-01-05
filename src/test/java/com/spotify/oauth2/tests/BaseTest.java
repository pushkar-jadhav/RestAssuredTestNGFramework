package com.spotify.oauth2.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeMethod
    public void beforeMethod(Method method){
        System.out.println("Started Executing Method: "+method.getName());
        System.out.println("Thread ID: "+Thread.currentThread().getId());
    }

    @AfterMethod
    public void afterMethod(Method method){
        System.out.println("Ended Executing Method: "+method.getName());
        System.out.println("Thread ID: "+Thread.currentThread().getId());
    }
}
