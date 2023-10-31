package hr.leapwise.lunchorderer.order;
import hr.leapwise.lunchorderer.ordermeal.OrderMeal;
import hr.leapwise.lunchorderer.ordermeal.OrderMealRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import hr.leapwise.lunchorderer.meal.Meal;
import hr.leapwise.lunchorderer.meal.MealRepository;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
import java.util.*;

@RestController
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderMealRepository orderMealRepository;
    private final MealRepository mealRepository;


    public OrderController(OrderRepository orderRepository, MealRepository mealRepository, OrderMealRepository orderMealRepository) {
        this.orderRepository = orderRepository;
        this.mealRepository = mealRepository;
        this.orderMealRepository = orderMealRepository;
    }

    @PostMapping("/order")
    public ResponseEntity<Long> createOrder(@RequestBody List<Long> mealIds) {
        for (Long mealId : mealIds) {
            if (!mealRepository.existsById(mealId)) {
                return ResponseEntity.badRequest().body(-1L);
            }
        }

        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order = orderRepository.save(order);

        for (Long mealId : mealIds) {
            Meal meal = mealRepository.findById(mealId).orElse(null);
            if (meal != null) {
                OrderMeal orderMeal = new OrderMeal();
                orderMeal.setOrder(order);
                orderMeal.setMeal(meal);
                orderMealRepository.save(orderMeal);
            }
        }

        return ResponseEntity.ok(order.getOrderId());
    }


    @PutMapping("/order/{id}")
    public ResponseEntity<Map<String, Object>> updateOrder(@PathVariable Long id, @RequestBody List<Long> mealIds) {

        for (Long mealId : mealIds) {
            if (!mealRepository.existsById(mealId)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            List<OrderMeal> existingOrderMeals = orderMealRepository.findByOrder(order);

            for (OrderMeal orderMeal : existingOrderMeals) {
                orderMealRepository.delete(orderMeal);
            }

            for (Long mealId : mealIds) {
                Meal meal = mealRepository.findById(mealId).orElse(null);
                if (meal != null) {
                    OrderMeal orderMeal = new OrderMeal();
                    orderMeal.setOrder(order);
                    orderMeal.setMeal(meal);
                    orderMealRepository.save(orderMeal);
                }
            }

            order = orderRepository.save(order);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("createdAt", order.getCreatedAt());
            responseData.put("orderId", order.getOrderId());

            List<Map<String, Object>> meals = new ArrayList<>();
            List<OrderMeal> orderMeals = orderMealRepository.findByOrder(order);
            for (OrderMeal orderMeal : orderMeals) {
                Meal meal = orderMeal.getMeal();
                Map<String, Object> mealData = new HashMap<>();
                mealData.put("mealId", meal.getMealId());
                mealData.put("mealName", meal.getMealName());
                mealData.put("price", meal.getPrice());
                meals.add(mealData);
            }
            responseData.put("meals", meals);

            return ResponseEntity.ok(responseData);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

