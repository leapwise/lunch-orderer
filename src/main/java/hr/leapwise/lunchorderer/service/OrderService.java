package hr.leapwise.lunchorderer.service;

import hr.leapwise.lunchorderer.model.Meal;
import hr.leapwise.lunchorderer.model.Order;
import hr.leapwise.lunchorderer.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MealService mealService;

    public Order getOrder(final Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with ID %d not found!", orderId)));
    }

    public Long createOrder(final List<Long> mealIds) {
        final List<Meal> meals = mealService.convertMealIdsToMeals(mealIds);

        final Order order = new Order();
        order.setMeals(meals);

        final Order savedOrder = orderRepository.save(order);
        return savedOrder.getOrderId();
    }

    public Order updateOrder(final Long orderId, final List<Long> mealIds) {
        final Order order = getOrder(orderId);
        final List<Meal> meals = mealService.convertMealIdsToMeals(mealIds);

        order.getMeals().addAll(meals);

        return orderRepository.save(order);
    }
}
