package hr.leapwise.lunchorderer.configuration;

import hr.leapwise.lunchorderer.configuration.properties.OrderSchedulerProperties;
import hr.leapwise.lunchorderer.model.Meal;
import hr.leapwise.lunchorderer.model.Order;
import hr.leapwise.lunchorderer.service.EmailService;
import hr.leapwise.lunchorderer.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@EnableScheduling
@EnableConfigurationProperties
@RequiredArgsConstructor
public class OrderSchedulerConfiguration {

    private final OrderSchedulerProperties orderSchedulerProperties;
    private final EmailService emailService;
    private final OrderService orderService;

    @Scheduled(cron = "#{orderSchedulerProperties.cron}")
    public void sendOrderEmail() {
        final List<Order> todayOrders = orderService.getTodayOrders();

        if (todayOrders.isEmpty()) {
            log.info("No orders have been made!");
            return;
        }

        final List<Meal> allOrderMeals = getMealsFromOrders(todayOrders);
        final Map<String, Long> mealCountMap = getMealCount(allOrderMeals);

        final StringBuilder message = new StringBuilder();
        message.append("Hello, we would like to order the following:\n\n");
        mealCountMap.forEach((meal, count) -> message.append(String.format("%d x %s\n", count, meal)));
        message.append("\nRegards,\nLeapwise team");

        emailService.sendSimpleEmail(orderSchedulerProperties.getEmail(), "Daily order", message.toString());
    }

    private List<Meal> getMealsFromOrders(final List<Order> orders) {
        return orders.stream()
                .map(Order::getMeals)
                .flatMap(Collection::stream)
                .toList();
    }

    private Map<String, Long> getMealCount(final List<Meal> meals) {
        return meals.stream()
                .collect(Collectors.groupingBy(Meal::getMealName, Collectors.counting()));
    }
}
