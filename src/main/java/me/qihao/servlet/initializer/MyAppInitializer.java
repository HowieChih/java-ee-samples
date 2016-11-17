package me.qihao.servlet.initializer;

import me.qihao.servlet.DynamicServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * My Application Initializer
 */
public class MyAppInitializer implements WebAppInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dynamicServlet", DynamicServlet.class);
        dynamic.addMapping("/dynamic");
    }
}
