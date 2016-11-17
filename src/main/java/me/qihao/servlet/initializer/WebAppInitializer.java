package me.qihao.servlet.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Web Application Initializer
 */
public interface WebAppInitializer {

    void onStartup(ServletContext servletContext) throws ServletException;
}
