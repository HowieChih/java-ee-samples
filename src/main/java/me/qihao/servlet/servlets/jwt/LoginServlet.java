package me.qihao.servlet.servlets.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "jwtLoginServlet", urlPatterns = "/jwt-api/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 4110052349251731103L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String result;
        if ("admin".equals(userName) && "123456".equals(password)) {
            Algorithm algorithmHS = (Algorithm) request.getServletContext().getAttribute("jwtAlgorithm");
            String token = JWT.create()
                    .withIssuer("admin")
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60000))
                    .sign(algorithmHS);
            response.setHeader("Authorization", "Bearer " + token);
            result = "{\"code\": 200, \"msg\": \"login success\"}";
        } else {
            result = "{\"code\": 401, \"msg\": \"login failed\"}";
        }

        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(result);
        }
    }
}
