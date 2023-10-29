package com.leapwise.lunchorderer.services;

/**
 * Service interface for sending emails.
 */
public interface EmailService {

    /**
     * Sends an email with the specified recipient, text, and subject.
     *
     * @param recipient The email recipient's address.
     * @param text      The content of the email.
     * @param subject   The subject of the email.
     */
    void sendEmail(String recipient, String text, String subject);
}
