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
        // 通过同时开启URIEncoding="UTF-8"和useBodyEncodingForURI="true"两个选项，并且使用request设置编码，会发现后者会覆盖前者对于query string的编码。
        req.setCharacterEncoding("iso-8859-1");
        String queryString = req.getQueryString();
        System.out.println(queryString);

        String originalKey = req.getParameter("key");
        System.out.println(originalKey);
        StringBuilder sb = new StringBuilder();
        byte[] bytes = originalKey.getBytes();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        System.out.println(sb);
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
