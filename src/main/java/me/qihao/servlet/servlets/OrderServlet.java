package me.qihao.servlet.servlets;

import me.qihao.servlet.service.EmailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>order servlet</p>
 *
 * @author qihao
 * @version 1.0
 * @since 1.0
 */
@WebServlet(name = "orderServlet", urlPatterns = {"/cart", "/order"})
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = -7819385230625364595L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/cart".equals(req.getServletPath())) {
            req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
        } else {
            assert "/order".equals(req.getServletPath());

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            // create order
            out.println("your order has been successfully created.");

            // send user email notice with new thread
            new Thread(new EmailService("howiechih@qq.com")).start();
            out.flush();
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
