package com.leapwise.lunchorderer.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class EmailProperties {

    @Value("${lunchorderer.email.sender}")
    private String senderEmail;

    @Value("${lunchorderer.email.recipient}")
    private String recipientEmail;

}
