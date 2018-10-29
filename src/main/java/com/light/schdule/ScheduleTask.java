package com.light.schdule;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.light.config.RootConfig;


@Component
@EnableScheduling
public class ScheduleTask {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RootConfig rootConfig;
	
	@Value("${spring.mail.username}")
    private String username;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
    @Scheduled(cron = "0/3 * * * * ?")
    public void printSay() {
    	//logger.info("getToken定时任务启动");
    	//rootConfig.sendMail("test", "content", "edisonyin@augmentum.com.cn", "edisonyin@augmentum.com.cn" ,"");
    }
    
    @Scheduled(cron = "0 0/1 * * * ?")
    public void ScheduledTask3() {
        //System.out.println(" 我是一个每隔一分钟就就会执行的任务");
        //rootConfig.sendMail("test", "content", "edisonyin@augmentum.com.cn", "edisonyin@augmentum.com.cn" ,"");
    }
    
    // 周一到周五 19：00 定时任务
    //@Scheduled(cron = "0 00 19 ? * MON-FRI")
    //@Scheduled(cron = "0/3 * * * * ?")
    public void sendDR() {
    	MailSender	mailSender = rootConfig.mailSender();
        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom("edisonyin@augmentum.com.cn");//发件人
        message.setTo("drewyuan@augmentum.com.cn");//收件人
        message.setSubject("Spring Email Test");//主题
        message.setText("<html><META http-equiv=Content-Type content='text/html; charset=GBK'><body><img src='cid:Coupon'>" +
                "<h4>" + "测试乱码" + " says...</h4>" +
                "<i>" + "测试乱码2" + "</i>" +
                "</body></html>");;//正文
        mailSender.send(message);
        System.out.println("邮件发送完毕");
    }
//    @Scheduled(cron = "0/3 * * * * ?")
    public void testSendSimple() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo("edisonyin@augmentum.com.cn");
        message.setSubject("标题：测试标题");
        message.setText("测试内容部份");
        javaMailSender.send(message);
    }
}