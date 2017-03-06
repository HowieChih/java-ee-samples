package me.qihao.servlet.service;


import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LongtimeService implements Runnable {

    private AsyncContext ctx = null;

    public LongtimeService(AsyncContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        try {
            // 模拟该业务方法需要5s
            Thread.sleep(50000);
            PrintWriter out = ctx.getResponse().getWriter();
            out.println("业务处理完毕的时间：" + new Date() + ".");
            out.flush();
            ctx.complete();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }
}
