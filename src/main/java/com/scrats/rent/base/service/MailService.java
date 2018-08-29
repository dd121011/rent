package com.scrats.rent.base.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/8/28 16:02.
 */
@Component
public class MailService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendSimpleMail(String to, String subject, String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        try {
            javaMailSender.send(simpleMailMessage);
            logger.info("简单邮件已经发送。");
        } catch (Exception e){
            logger.error("发送简单邮件时发生异常!", e);
        }
    }

    public void sendHtmlMail(String to, String subject, String content){
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            javaMailSender.send(mimeMailMessage);
            logger.info("html邮件已经发送。");
        } catch (Exception e){
            logger.error("发送html邮件时发生异常!", e);
        }
    }

    public void sendHtmlMail(String to, String subject, String content, String filePath){
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            mimeMessageHelper.addAttachment(fileName, file);
            javaMailSender.send(mimeMailMessage);
            logger.info("附件邮件已经发送。");
        } catch (Exception e){
            logger.error("发送附件邮件时发生异常!", e);
        }
    }

}
