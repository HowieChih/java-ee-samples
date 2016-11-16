package me.qihao.servlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * servlet container initializer
 */
@HandlesTypes(DynamicServlet.class)
public class MyContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("servlet container onStartup...++");
        if (set != null) {
            for (Class<?> clazz : set) {
                System.out.println(clazz.getName());
                if (clazz == DynamicServlet.class) {
                    System.out.println("+++++++");
                }
            }
        }
    }

}
