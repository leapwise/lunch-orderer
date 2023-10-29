package com.leapwise.lunchorderer.service.interfaces;

public interface EmailService {

    public void sendEmail(String to, String subject, String text);

}
