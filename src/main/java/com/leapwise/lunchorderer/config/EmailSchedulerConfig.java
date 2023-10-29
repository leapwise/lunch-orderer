package com.leapwise.lunchorderer.config;

import com.leapwise.lunchorderer.domain.dto.OrderDto;
import com.leapwise.lunchorderer.domain.dto.OrderItemDto;
import com.leapwise.lunchorderer.domain.entities.OrderEntity;
import com.leapwise.lunchorderer.mappers.impl.OrderMapper;
import com.leapwise.lunchorderer.services.EmailService;
import com.leapwise.lunchorderer.services.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Configuration class for scheduling a daily order email.
 */
@Configuration
@Data
public class EmailSchedulerConfig {

    private final EmailService emailService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Value("${email.recipient}")
    private String recipient;

    /**
     * Constructs an EmailSchedulerConfig.
     *
     * @param emailService The email service for sending emails.
     * @param orderService The service for managing orders.
     * @param orderMapper  The mapper for mapping order entities to order DTOs.
     */
    public EmailSchedulerConfig(EmailService emailService, OrderService orderService, OrderMapper orderMapper) {
        this.emailService = emailService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    /**
     * Scheduled task to send a daily order email.
     */
    @Scheduled(cron = "${email.cron}")
    public void sendDailyEmail(){
        List<OrderEntity> orderEntities = orderService.getAllOrders();
        List<OrderDto> orderDtos = orderEntities.stream()
                                    .map(orderMapper::mapTo)
                                    .filter(Objects::nonNull)
                                    .toList();

        List<OrderItemDto> orderItems = orderDtos.stream()
                                    .map(OrderDto::getOrderItems)
                                    .flatMap(Collection::stream).toList();

        Map<String, Long> quantityPerMeal = orderItems.stream()
                .collect(Collectors.groupingBy(orderItem -> orderItem.getMeal().getName(),
                        Collectors.summingLong(OrderItemDto::getQuantity)));

        Map<String, String> emailInfo = constructEmail(quantityPerMeal);
        emailService.sendEmail(recipient, emailInfo.get("email"), emailInfo.get("subject"));
    }

    /**
     * Constructs the email content and subject based on the quantity of each meal in all orders.
     *
     * @param quantityPerMeal A map of meal names to their respective quantities.
     * @return A map containing email content and subject.
     */
    public Map<String, String> constructEmail(Map<String, Long> quantityPerMeal){
        StringBuilder email = new StringBuilder("Hello, we would like to order the following:\n\n");
        for (Map.Entry<String, Long> entry : quantityPerMeal.entrySet()) {
            String meal = entry.getKey();
            Long quantity = entry.getValue();
            email.append(String.format("%d x %s\n", quantity, meal));
        }
        email.append("\nRegards,\nLeapwise team");
        String subject = "Leapwise Lunch Order";
        Map<String, String> emailInfo = new HashMap<String,String>();
        emailInfo.put("email", email.toString());
        emailInfo.put("subject", subject);
        return emailInfo;
    }
}
