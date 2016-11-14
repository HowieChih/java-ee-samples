package me.qihao.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * non-annotation servlet
 */
public class NonAnnotationServlet extends HttpServlet {

    private static final long serialVersionUID = 6370794174312255262L;

    public NonAnnotationServlet(){
        System.out.println("non-annotation构造方法调用");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()){
            out.print("non-annotaion servlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("non-annotation init方法调用");
    }
}
