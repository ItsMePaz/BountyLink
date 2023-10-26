package com.bountylink.v1.bountylink.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailSenderService {
    void sendEmail(String toEmail, String subject, String body);
}
