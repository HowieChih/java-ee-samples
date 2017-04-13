package me.qihao.servlet.service;

import java.util.logging.Logger;

public class EmailService implements Runnable{

    private static final Logger logger = Logger.getLogger("me.qihao.servlet.service.EmailService");
    private String address;

    public EmailService(String address) {
        this.address = address;
    }

    private void send(String address) throws InterruptedException {
        // 假设邮件发送程序需要3s
        Thread.sleep(3000);
        logger.info("email successfully send to: " + address);
    }

    @Override
    public void run() {
        try {
            this.send(this.address);
        } catch (InterruptedException e) {
            logger.throwing("EmailService", "run()", e);
        }
    }
}
