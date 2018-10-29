package com.light.serviceImpl;

import com.light.service.MailService;
import org.springframework.stereotype.Service;

/**
 * @Author: Edison * @Date: 2018/10/29 21:10 * @Description:
 *
 * 邮件服务类
 * The service is email service implements*
 */
@Service
public class MailServiceImpl implements MailService {

    @Override
    public void sendHtmlMail(String to, String subject, String content) {

    }
}
