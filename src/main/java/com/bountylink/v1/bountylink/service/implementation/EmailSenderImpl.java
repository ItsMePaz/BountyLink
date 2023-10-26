package com.bountylink.v1.bountylink.service.implementation;


import com.bountylink.v1.bountylink.service.EmailSenderService;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Properties;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Service

public class EmailSenderImpl implements EmailSenderService {

   @Autowired
   private JavaMailSender javaMailSender;


    //THIS NEEDS TO BE FIXED
    @Override
    @Async

    public void sendEmail(String toEmail, String subject, String body)  {
        final String username = "mpazdev@gmail.com";
        final String password = "kzdqggdmxxquzhgh";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");



        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mpazdev@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("pazmichaelandrew70@gmail.com"));
            message.setSubject("Subject of the Email");
            message.setText("This is the body of the email.");

            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
