package me.qihao.servlet.service;

import redis.clients.jedis.JedisPubSub;

public class MySubscriber extends JedisPubSub {

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(channel + " " + subscribedChannels);
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(channel + " " + message);
    }
}
