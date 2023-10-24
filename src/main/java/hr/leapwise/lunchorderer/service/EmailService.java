package hr.leapwise.lunchorderer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final MailSender mailSender;

    public void sendSimpleEmail(final String recipient, final String subject, final String text) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lunch.orderer@leapwise.co");
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}
