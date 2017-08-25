package me.qihao.servlet.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * test javascript fetch api in fetch.html
 *
 * @author qihao
 * @version 1.0 2017-08-24
 * @since 1.0
 */
@WebServlet(name = "fetchServlet", urlPatterns = "/fetch.json")
public class FetchServlet extends HttpServlet{
    private static final long serialVersionUID = 390820856557104640L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String result = "{\"name\": \"张三\",\"age\": 11}";
        try (PrintWriter out = resp.getWriter()){
            out.print(result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String favColor = req.getParameter("favColor");
        String password= req.getParameter("password");
        System.out.printf("%s %s %s\n", firstName, favColor, password);

        resp.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()){
            out.println(firstName + "'s info has been saved.");
        }
    }
}
