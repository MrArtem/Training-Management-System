package com.exadel.training.notification.impl;

/**
 * Created by ayudovin on 13.10.2015.
 */
import com.exadel.training.notification.Notification;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;


@Component
public class NotificationEmail implements Notification {
    private final String USERNAME = "mrartem6695@gmail.com";
    private final String PASSWORD = "jordan23!";

    private Properties properties;
    private Session session;

    public NotificationEmail() {
        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.debug", "true");

        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
    }

    public void send(String adress, String subject, String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(adress));
            message.setSubject(subject);
            message.setText("");

            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messagePart = new MimeBodyPart();


            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}
