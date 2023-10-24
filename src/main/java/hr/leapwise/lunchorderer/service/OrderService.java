package hr.leapwise.lunchorderer.service;

import hr.leapwise.lunchorderer.model.Meal;
import hr.leapwise.lunchorderer.model.Order;
import hr.leapwise.lunchorderer.repository.MealRepository;
import hr.leapwise.lunchorderer.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MealRepository mealRepository;

    public Order getOrder(final Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with ID %d not found!", orderId)));
    }

    public Order createOrder(final List<Long> mealIds) {
        final List<Meal> meals = convertMealIdsToMeals(mealIds);

        final Order order = new Order();
        order.setMeals(meals);

        return orderRepository.save(order);
    }

    public Order updateOrder(final Long orderId, final List<Long> mealIds) {
        final Order order = getOrder(orderId);
        final List<Meal> meals = convertMealIdsToMeals(mealIds);

        order.getMeals().addAll(meals);

        return orderRepository.save(order);
    }

    private List<Meal> convertMealIdsToMeals(final List<Long> mealIds) {
        return mealIds.stream()
                .map(mealId -> mealRepository.findById(mealId)
                        .orElseThrow(() -> new EntityNotFoundException(String.format("Meal with ID %d not found!", mealId))))
                .toList();

    }
}
