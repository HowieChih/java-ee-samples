package me.qihao.servlet.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

@WebServlet(name = "URLEncodingServlet", urlPatterns = {"/url-encoding"})
public class URLEncodingServlet extends HttpServlet{

    private static final long serialVersionUID = -8545369046552937679L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String queryString = req.getQueryString();
        System.out.println(queryString);

        String originalKey = req.getParameter("key");
        System.out.println(originalKey);
        String decodedKey = new String(originalKey.getBytes("iso-8859-1"), Charset.forName("utf-8"));
        System.out.println(decodedKey);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.println("key is: " + originalKey);
        out.println("decoded key is: " + decodedKey);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
