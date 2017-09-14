package me.qihao.servlet.listener;

import com.auth0.jwt.algorithms.Algorithm;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.UnsupportedEncodingException;

/**
 * <p>Servlet Context Listener</p>
 *
 * @author qihao
 * @version 1.0
 * @since 1.0
 */
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        System.out.println(context.getServerInfo());
        System.out.println("Servlet环境初始化完成");

        // 初始化redis环境
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
        context.setAttribute("jedisPool", jedisPool);

        // 初始化jwt签名算法
        try {
            Algorithm algorithmHS = Algorithm.HMAC256("test");
            event.getServletContext().setAttribute("jwtAlgorithm", algorithmHS);
        } catch (UnsupportedEncodingException e) {
            // log error
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        JedisPool jedisPool = (JedisPool) event.getServletContext().getAttribute("jedisPool");
        jedisPool.destroy();
        System.out.println("Servlet环境销毁");
    }
}
