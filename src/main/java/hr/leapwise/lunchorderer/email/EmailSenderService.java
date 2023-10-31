package hr.leapwise.lunchorderer.email;

import hr.leapwise.lunchorderer.meal.MealRepository;
import hr.leapwise.lunchorderer.order.OrderRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;



@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MealRepository mealRepository;
    @Scheduled(cron = "0 30 10 * * ?")
    public void triggerMail() throws MessagingException {
        String bodyMessage = generateBody();
        String subjectMessage = "Leapwise order";
        sendSimpleEmail("email@gmail.com",subjectMessage, bodyMessage);
    }
    public String generateBody() {
        return "todo...";
    }

    public void sendSimpleEmail(String toEmail, String subject,String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("email@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }
}
