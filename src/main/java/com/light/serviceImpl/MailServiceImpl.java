package com.light.serviceImpl;

import com.light.config.RootConfig;
import com.light.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @Author: Edison * @Date: 2018/10/29 21:10 * @Description:
 *
 * 邮件服务类
 * The service is email service implements*
 */
@Service
public class MailServiceImpl implements MailService {


    // mail sender factory.
    @Autowired
    private RootConfig rootConfig;
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        String from = "edisonyin@augmentum.com.cn";
        MailSender mailSender = rootConfig.mailSender();

        // 简单邮件消息构造器
        // 可以设置收件人，消息内容，邮件格式为普通邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();//消息构造器

        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        //发送邮件
        mailSender.send(mailMessage);

    }
}
