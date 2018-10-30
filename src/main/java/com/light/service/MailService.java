package com.light.service;


/** * @Author: Edison * @Date: 2018/10/29 21:00 * @Description:
 *
 * This is a Mail service interface for send many kings of eamil.* */

public interface MailService {
    /**
     * 发送html格式的邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String to,String subject,String content);
}
