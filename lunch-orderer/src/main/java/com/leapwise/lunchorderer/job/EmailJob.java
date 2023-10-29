package com.leapwise.lunchorderer.job;

import com.leapwise.lunchorderer.config.EmailProperties;
import com.leapwise.lunchorderer.models.DTO.responses.MealResponseDto;
import com.leapwise.lunchorderer.models.DTO.responses.OrderResponseDto;
import com.leapwise.lunchorderer.models.domain.Meal;
import com.leapwise.lunchorderer.models.domain.Order;
import com.leapwise.lunchorderer.service.interfaces.EmailService;
import com.leapwise.lunchorderer.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class EmailJob {

    private final EmailService emailService;
    private final OrderService orderService;
    private final EmailProperties emailProperties;

    @Scheduled(cron = "0 30 10 * * *")
    public void sendDailyEmail() {
        OrderResponseDto order = orderService.getLatestOrder();

        if(order.getMeals().isEmpty())
            return;

        String emailContent = generateEmailContent(order.getMeals());

        emailService.sendEmail(emailProperties.getRecipientEmail(), "Lunch Order", emailContent);
    }

    private String generateEmailContent(List<MealResponseDto> order) {
        Map<Long, Integer> mealQuantities = new HashMap<>();

        for (MealResponseDto meal : order) {
            Long mealId = meal.getId();
            mealQuantities.put(mealId, mealQuantities.getOrDefault(mealId, 0) + 1);
        }

        List<String> mealSummaries = mealQuantities.entrySet().stream()
                .map(entry -> entry.getValue() + " x " + getMealName(order, entry.getKey()))
                .collect(Collectors.toList());

        StringBuilder message = new StringBuilder();
        message.append("Hello, we would like to order the following:\n\n");
        message.append(String.join("\n", mealSummaries));
        message.append("\n\nRegards, \nLeapwise team");

        return message.toString();
    }

    private String getMealName(List<MealResponseDto> order, Long mealId) {
        Optional<MealResponseDto> optionalMeal = order.stream()
                .filter(meal -> meal.getId() == mealId)
                .findFirst();

        return optionalMeal.map(MealResponseDto::getName).orElse("");
    }
}
