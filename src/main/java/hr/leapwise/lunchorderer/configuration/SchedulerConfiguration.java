package hr.leapwise.lunchorderer.configuration;

import hr.leapwise.lunchorderer.configuration.properties.ScheduledOrderProperties;
import hr.leapwise.lunchorderer.model.Meal;
import hr.leapwise.lunchorderer.service.EmailService;
import hr.leapwise.lunchorderer.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
@EnableConfigurationProperties
@RequiredArgsConstructor
public class SchedulerConfiguration {

    private final ScheduledOrderProperties scheduledOrderProperties;
    private final EmailService emailService;
    private final MealService mealService;

    @Scheduled(cron = "#{scheduledOrderProperties.cron}")
    public void sendOrderEmail() {
        final List<Meal> meals = mealService.convertMealIdsToMeals(scheduledOrderProperties.getMealIds());
        final Map<String, Long> mealCountMap = meals.stream()
                .collect(Collectors.groupingBy(Meal::getMealName, Collectors.counting()));

        final StringBuilder message = new StringBuilder();
        message.append("Hello, we would like to order the following:\n\n");
        mealCountMap.forEach((meal, count) -> message.append(String.format("%d x %s\n", count, meal)));
        message.append("\nRegards,\nLeapwise team");

        emailService.sendSimpleEmail(scheduledOrderProperties.getEmail(), "Daily order", message.toString());
    }
}
