package me.qihao.servlet.initializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * servlet container initializer
 */
@HandlesTypes(WebAppInitializer.class)
public class MyContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("servlet container onStartup...");
        List<WebAppInitializer> initializers = new LinkedList<>();
        if (set != null) {
            for (Class<?> waiClass : set) {
                System.out.println(waiClass.getName());
                if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) &&
                        WebAppInitializer.class.isAssignableFrom(waiClass)) {
                    try {
                        initializers.add((WebAppInitializer) waiClass.newInstance());
                    } catch (Throwable ex) {
                        throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
                    }
                }
            }
        }

        for (WebAppInitializer initializer : initializers) {
            initializer.onStartup(servletContext);
        }

    }

}
