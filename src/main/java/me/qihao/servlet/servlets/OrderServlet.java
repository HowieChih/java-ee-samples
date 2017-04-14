package me.qihao.servlet.servlets;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
            out.println("<p>your order has been successfully created.</p>");

            // send email with redis pub/sub
            JedisPool jedisPool = (JedisPool) req.getServletContext().getAttribute("jedisPool");
            try (Jedis jedis = jedisPool.getResource()) {
                // test redis
                out.println("<p>mykey from redis: " + jedis.get("mykey") + "</p>");

                jedis.publish("email-channel", "howiechih@qq.com");
            }

            // send user email notice with new thread
            // new Thread(new EmailService("howiechih@qq.com")).start();
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
