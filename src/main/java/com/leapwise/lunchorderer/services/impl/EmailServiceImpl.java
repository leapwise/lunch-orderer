package com.leapwise.lunchorderer.services.impl;

import com.leapwise.lunchorderer.services.EmailService;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Service implementation for sending emails.
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final MailSender emailSender;

    /**
     * Constructs a new EmailServiceImpl.
     *
     * @param emailSender The email sender used to send emails.
     */
    public EmailServiceImpl(MailSender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * Sends an email with the specified recipient, text, and subject.
     *
     * @param recipient The email recipient's address.
     * @param text      The content of the email.
     * @param subject   The subject of the email.
     */
    @Override
    public void sendEmail(String recipient, String text, String subject){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setText(text);
        email.setSubject(subject);
        email.setTo(recipient);
        emailSender.send(email);
    }
}
