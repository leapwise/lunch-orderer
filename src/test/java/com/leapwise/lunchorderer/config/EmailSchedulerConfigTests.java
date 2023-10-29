package com.leapwise.lunchorderer.config;

import com.leapwise.lunchorderer.services.EmailService;
import com.leapwise.lunchorderer.services.MealService;
import com.leapwise.lunchorderer.services.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

@SpringBootTest
public class EmailSchedulerConfigTests {

    @Value("${email.recipient}")
    private String recipient;

    @MockBean
    private EmailService emailService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MealService mealService;

    @Autowired
    private EmailSchedulerConfig emailSchedulerConfig;

    @Test
    public void testSendDailyEmailSendsCorrectData() {
        orderService.saveOrder(Arrays.asList(1L, 3L, 8L));
        orderService.saveOrder(Arrays.asList(3L, 7L, 8L));
        orderService.saveOrder(Arrays.asList(1L, 1L, 8L));

        Map<String, Long> countPerMeal = new HashMap<>() {{
            put(mealService.getName(1L), 3L);
            put(mealService.getName(3L), 2L);
            put(mealService.getName(7L), 1L);
            put(mealService.getName(8L), 3L);
        }};

        Map<String, String> emailInfo = emailSchedulerConfig.constructEmail(countPerMeal);
        emailSchedulerConfig.sendDailyEmail();
        Mockito.verify(emailService).sendEmail(recipient, emailInfo.get("email"), emailInfo.get("subject"));
    }

}
