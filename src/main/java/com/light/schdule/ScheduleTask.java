package com.light.schdule;

import java.util.Date;

import com.light.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
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

    @Autowired
    private MailService mailService;
	
	@Value("${spring.mail.username}")
    private String username;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
    @Scheduled(cron = "0/3 * * * * ?")
    public void printSay() {
    	logger.info("getToken定时任务启动");
        //sendDR();
    }
    
    @Scheduled(cron = "0 0/1 * * * ?")
    public void ScheduledTask3() {
        //System.out.println(" 我是一个每隔一分钟就就会执行的任务");
    }
    
    // 周一到周五 19：00 定时任务
    //@Scheduled(cron = "0 00 19 ? * MON-FRI")
    //@Scheduled(cron = "0/3 * * * * ?")
    public void sendDR() {
        String to = "justdoitym@aliyun.com";
        String subject = "test simple email send";
        String content = "Hello!";
        try {
            //mailService.sendSimpleMail(to, subject, content);
            mailService.sendMail("Test html mail title ", "asdad", to, to);
            System.out.println("邮件发送完毕");
        } catch (MailAuthenticationException e) {
            System.out.println("catch MailAuthenticationException");
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        } catch (Exception ex) {
            System.out.println("catch exception");
            System.out.println(ex.getMessage());
        }
    }
//    @Scheduled(cron = "0/3 * * * * ?")
    public void testSendSimple() {

    }
}