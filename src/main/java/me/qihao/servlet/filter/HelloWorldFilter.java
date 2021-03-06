package me.qihao.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>/hello-world filter</p>
 *
 * @author qihao
 * @version 1.0
 * @since 1.0
 */
@WebFilter(filterName = "helloWorldFilter", servletNames = {"helloWorldServlet"})
public class HelloWorldFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getFilterName() + " init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("hello world filter: " + request.getRequestURL());
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("post-process the filter.");
    }

    @Override
    public void destroy() {
        System.out.println("hello world filter destroy");
    }
}
