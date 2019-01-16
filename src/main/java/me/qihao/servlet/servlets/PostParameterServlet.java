package me.qihao.servlet.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;

@WebServlet(name = "postParameterServlet", urlPatterns = {"/post-param"})
public class PostParameterServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // req.setCharacterEncoding("UTF-8");
        System.out.println("cType: " + req.getParameter("cType"));

        Map<String, String[]> parameterMap = req.getParameterMap();
        if (parameterMap != null) {
            parameterMap.keySet().forEach(key -> {
                String[] values = parameterMap.get(key);
                System.out.println("key: " + key + " value: " + Arrays.toString(values));
            });
        }

        String queryStr = req.getQueryString();
        System.out.println("originalQueryString: " + queryStr);

        String decodedStr = URLDecoder.decode(queryStr, "UTF-8");
        System.out.println("decodedQueryString: " + decodedStr);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("cType"));
    }
}
