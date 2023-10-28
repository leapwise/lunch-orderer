package leapwise.internship.internshipProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Class which contains the main function of the project. Used to start the application
 *
 * @author Vito
 */
@SpringBootApplication
@EnableScheduling
public class LunchOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LunchOrderApplication.class, args);
    }

}