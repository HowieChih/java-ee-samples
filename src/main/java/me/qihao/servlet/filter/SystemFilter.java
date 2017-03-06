package me.qihao.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>System Filter</p>
 * <p>If you want to set order value for serveral Filter,
 * you must combine the using of @WebFilter and web.xml's filter-mapping element.
 * <a href='http://stackoverflow.com/questions/6560969/how-to-define-servlet-filter-order-of-execution-using-annotations-in-war'>reference</a>
 * </p>
 *
 * @author qihao
 * @version 1.0
 * @since 1.0
 */
@WebFilter(filterName = "systemFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "sysParamVersion", value = "1.0"),
                @WebInitParam(name = "sysParamEnv", value = "dev")
        },
        asyncSupported = true
)
public class SystemFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getFilterName() + " init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("system filter: " + request.getRequestURL());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("system filter destroy");
    }
}
