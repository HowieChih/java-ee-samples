package me.qihao.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * redirect servlet
 */
@WebServlet(name = "redirectServlet", urlPatterns = "/redirect-to-hello-world")
public class RedirectServlet extends HttpServlet {

    private static final long serialVersionUID = 2371300263975790701L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // If the location is relative without a leading '/' the container interprets it as relative to the current request URI.
        // tips: if current request URI is "/xxx/222", the function value is "hello-world", then it will redirect to "/xxx/hello-world"
        // If the location is relative with a leading '/' the container interprets it as relative to the servlet container root.
        // tips: servlet container root from tomcat is the request scheme+"//"+serverName+serverPort http://localhost:8080
        // If the location is relative with two leading '/' the container interprets it as a network-path reference
        resp.sendRedirect(req.getContextPath() + "/hello-world");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
