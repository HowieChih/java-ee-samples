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
 *
 * ServletContainerInitializer 也是 Servlet 3.0 新增的一个接口，
 * 容器在启动时使用 JAR 服务 API(JAR Service API) 来发现 ServletContainerInitializer 的实现类，
 * 并且容器将 WEB-INF/lib 目录下 JAR 包中的类都交给该类的 onStartup() 方法处理，
 * 我们通常需要在该实现类上使用 @HandlesTypes 注解来指定希望被处理的类，
 * 过滤掉不希望给 onStartup() 处理的类。
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
