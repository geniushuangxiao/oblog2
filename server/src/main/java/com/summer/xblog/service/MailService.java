package com.summer.xblog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {
    @Autowired
    private Environment env;

    public boolean sendSimpleEmail(String to, String random) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("mail.send.from"));
        message.setTo(to);
        message.setSubject("欢迎注册");
        String url = env.getProperty("mail.send.url");
        String port = env.getProperty("server.port");
        if("80".equals(port)) {
            message.setText(String.format("%s/#/register/activate/%s", url, random));
        } else {
            message.setText(String.format("%s:%s/#/register/activate/%s", url, port, random));
        }
        return this.send(message);
    }

    private boolean send(SimpleMailMessage message) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mail.server.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("mail.server.port")));
        mailSender.setUsername(env.getProperty("mail.server.username"));
        mailSender.setPassword(env.getProperty("mail.server.password"));
        try {
            mailSender.send(message);
            return true;
        } catch (MailException e) {
            log.error("邮件发送失败", e);
            return false;
        }
    }
}
