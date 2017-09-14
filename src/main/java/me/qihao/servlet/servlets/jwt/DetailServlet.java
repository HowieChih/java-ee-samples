package me.qihao.servlet.servlets.jwt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "jwtDetailServlet", urlPatterns = "/jwt-api/detail")
public class DetailServlet extends HttpServlet {
    private static final long serialVersionUID = -8490974911897301991L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print("{\"id\": " + id + ", \"name\": \"John\"}");
        }
    }
}
