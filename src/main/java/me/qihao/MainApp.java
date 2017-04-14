package me.qihao;

import me.qihao.servlet.service.MySubscriber;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MainApp {

    private static JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost");

    public static void main(String[] args){
        try(Jedis jedis = jedisPool.getResource()) {
            // 新开客户端添加一个channel订阅
            jedis.subscribe(new MySubscriber(), "email-channel");
        }
    }
}
