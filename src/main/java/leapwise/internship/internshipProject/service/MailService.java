package leapwise.internship.internshipProject.service;

import leapwise.internship.internshipProject.domain.Meal;
import leapwise.internship.internshipProject.repository.MealRepository;
import leapwise.internship.internshipProject.repository.MenuRepository;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

/**
 * Service class which contains all the necessary functions to send a scheduled
 * Email with the summary of orders
 *
 * @author Vito
 */
@Service
public class MailService {

    /**
     * The {@link JavaMailSender} class
     */
    private final JavaMailSender mailSender;
    /**
     * The {@link Environment} class of the project
     */
    private final Environment environment;
    /**
     * Allows access to the meal repository
     */
    private final MealRepository mealRepository;
    /**
     * Allows access to the menu repository
     */
    private final MenuRepository menuRepository;

    /**
     * Simple constructor which asssigns all the provided values
     *
     * @param environment    The environment of the project
     * @param mealRepository The meal repository interface
     * @param menuRepository The menu repository interface
     */
    public MailService(Environment environment, MealRepository mealRepository, MenuRepository menuRepository) {
        this.mailSender = this.getJavaMailSender();
        this.environment = environment;
        this.mealRepository = mealRepository;
        this.menuRepository = menuRepository;
    }

    /**
     * Schedule function which sends an email containing the summary of orders, every day at 10:30 am
     * to the email defined in the "resources/application.properties" file
     */
    @Scheduled(cron = "0 30 10 * * ?")
    public void sendMail() {
        String sender = environment.getProperty("internshipProject.email.sender");
        String recipient = environment.getProperty("internshipProject.email.recipient");
        String subject = "Lunch order";
        String text = createEmail();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(recipient);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }

    /**
     * Function which initializes the {@link JavaMailSender} with all the necessary configurations for sending an email
     *
     * @return The initialized {@link JavaMailSender}
     */
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("my.gmail@gmail.com");
        mailSender.setPassword("password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    /**
     * Helper function which creates the email body.
     * Iterates through all the items in the orders and adds them to the email text.
     *
     * @return The completed email text
     */
    private String createEmail() {
        String text = "Hello, we would like to order the following:\n\n";

        List<Meal> meals = mealRepository.findAll();

        for (Meal meal : meals) {
            String mealName = menuRepository.findById(meal.getMenuItemId()).get().getItemDescription();
            int amount = meal.getAmount();

            text += amount + " X " + mealName + "\n";
        }

        text += "\nRegards,\nVito Sabalic";

        return text;
    }

}
