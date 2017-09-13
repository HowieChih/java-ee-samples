package me.qihao.servlet.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "jwtLoginServlet", urlPatterns = "/login")
public class JwtLoginServlet extends HttpServlet{
    private static final long serialVersionUID = 4110052349251731103L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String redirectTo;
        if ("admin".equals(userName) && "123456".equals(password)) {
            redirectTo = "/cart";
        } else {
            redirectTo = "/html/jwt.html";
        }
        response.sendRedirect(request.getContextPath() + redirectTo);
    }
}
