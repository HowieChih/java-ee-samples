package me.qihao.servlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * servlet container initializer
 */
public class MyContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        if (set != null) {
            for (Class<?> clazz : set) {
                System.out.println(clazz.getName());
            }
        }
        System.out.println("servlet container onStartup...");
    }

}
