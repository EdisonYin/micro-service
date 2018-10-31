package com.light.config;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class RootConfig {

    private JavaMailSenderImpl javaMailSender;
    private SimpleMailMessage simpleMailMessage;
    
    @Autowired
    private JavaMailSender javaMailSendertool;
    
	@Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.password}")
    private String password;


    /**
     * 配置邮件发送器 MailSender
     *
     * 可以用来发送一些简单的消息
     * @return
     */
    public MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(port);//默认端口，标准的SMTP端口
        mailSender.setUsername(username);//用户名
        mailSender.setPassword(password);//密码
        return mailSender;
    }


    /**
     * 配置邮件发送器
     * @return
     * null
     */
    public JavaMailSenderImpl JavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(port);//默认端口，标准的SMTP端口
        mailSender.setUsername(username);//用户名
        mailSender.setPassword(password);//密码
        return mailSender;
    }
}