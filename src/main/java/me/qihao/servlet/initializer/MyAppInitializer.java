package me.qihao.servlet.initializer;

import me.qihao.servlet.DynamicServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.nio.charset.Charset;

/**
 * My Application Initializer
 */
public class MyAppInitializer implements WebAppInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dynamicServlet", DynamicServlet.class);
        dynamic.addMapping("/dynamic");
    }

    public static void main(String[] args){
        String str = "文";
        byte[] bytes = str.getBytes(Charset.forName("utf-16"));
        for (byte b : bytes) {
            System.out.println(b);
        }
        char c = '文';
        System.out.println(Integer.valueOf(c));
    }
}
