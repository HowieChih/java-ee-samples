package me.qihao.servlet.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "jwtAuthenticationFilter", urlPatterns = "/jwt-api/*")
public class JwtAuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String contextPath = request.getContextPath();
        String path = request.getRequestURI().substring(contextPath.length());
        if ("/jwt-api/login".equals(path)) {
            filterChain.doFilter(request, response);
        } else {
            String authHeader = request.getHeader("Authorization");
            String token = "";
            if (authHeader != null && authHeader.startsWith("Bearer")) {
                token = authHeader.substring("Bearer".length() + 1);
            }
            // jwt token verify
            Algorithm algorithmHS = (Algorithm) request.getServletContext().getAttribute("jwtAlgorithm");
            JWTVerifier verifier = JWT.require(algorithmHS).withIssuer("admin").build();
            try {
                DecodedJWT jwt = verifier.verify(token);
                filterChain.doFilter(request, response);
            } catch (JWTVerificationException e) {
                // log error
                e.printStackTrace();
                response.setContentLength(0);
                response.setStatus(403);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
