package me.qihao.servlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * tomcat container initializer
 */
public class MyContainerInitializer implements ServletContainerInitializer{

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("servlet container onStartup...");
    }

}
