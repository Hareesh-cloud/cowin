package com.hcl.cowin.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService 
{
    @Autowired
    private JavaMailSender mailSender;
      
    /**
     * This method will send compose and send the message 
     * @throws MessagingException 
     * */
    public void sendMail(String to, String subject, String body) throws MessagingException 
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
       
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,false, "utf-8");
        mimeMessage.setContent(body, "text/html");
        helper.setTo(to);
        helper.setSubject(subject);
       
        mailSender.send(mimeMessage);
    }
  
    
    public SimpleMailMessage emailTemplate()
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("user@gmail.com");
        message.setFrom("admin@gmail.com");
        message.setSubject("Important email");
        message.setText("FATAL - Application crash. Save your job !!");
        return message;
    }
}