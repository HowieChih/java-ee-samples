package me.qihao.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * cookie servlet
 */
@WebServlet(name = "cookieServlet", urlPatterns = "/cookie")
public class CookieServlet extends HttpServlet {

    private static final long serialVersionUID = 2371300263975790701L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Cookie httpOnly = new Cookie("cookie_http_only", "true");
        httpOnly.setHttpOnly(true);
        resp.addCookie(httpOnly);
        Cookie notHttpOnly = new Cookie("not_cookie_http_only", "false");
        resp.addCookie(notHttpOnly);

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>cookie list</title>");
            out.println("</head>");
            out.println("<body>");
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // notice that: The browser does not send cookie information other than the value, such as httponly, secure, maxage, path, etc back. The browser only sends the cookie value back associated with the cookie name.
                    // output sample: JSESSIONID---036B7072FB6D21AB20F421FCF6F04F3E----1---false
                    // although JSESSIONID is httpOnly.
                    out.println("<p>" + cookie.getName() + "---" + cookie.getValue() + "---" + cookie.getMaxAge() + "---" + cookie.isHttpOnly() + "</p>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
