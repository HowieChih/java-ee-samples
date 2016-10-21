package me.qihao.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>first me.qihao.servlet</p>
 *
 * @author qihao
 * @version 1.0
 * @since 1.0
 */
@WebServlet(
        name = "helloWorldServlet",
        urlPatterns = {"/hello-world"},
        initParams = {
                @WebInitParam(name = "env", value = "dev"),
                @WebInitParam(name = "version", value = "1.0")
        })
public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = -6262601852295504673L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            // when first call request.getSession(), session will create
            req.getSession();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>hello world</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Servlet HelloWorldServlet at " + req.getContextPath() + "</h2>");
            // getInitParameter() is equivalent to getServletConfig().getInitParameter()
            out.println("<p>environment: " + getInitParameter("env") + "</p>");
            out.println("<p>version: " + getInitParameter("version") + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public String getServletInfo() {
        super.getServletInfo();
        return "hello world me.qihao.servlet";
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // if override init funcation, must call super.init(config),
        // otherwise, you shall get NULL with getServletConfig().
        super.init(config);
        System.out.println(config.getServletName() + "初始化");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("HelloWorld Servlet销毁");
    }
}
