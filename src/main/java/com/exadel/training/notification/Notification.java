package com.exadel.training.notification;

import javax.mail.MessagingException;

/**
 * Created by ayudovin on 13.10.2015.
 */
public interface Notification {
    void send(String adress, String subject, String text);
}
