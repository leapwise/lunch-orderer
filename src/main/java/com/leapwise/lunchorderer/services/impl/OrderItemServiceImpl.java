package com.leapwise.lunchorderer.services.impl;

import com.leapwise.lunchorderer.domain.entities.MealEntity;
import com.leapwise.lunchorderer.domain.entities.OrderItemEntity;
import com.leapwise.lunchorderer.repositories.OrderItemRepository;
import com.leapwise.lunchorderer.services.MealService;
import com.leapwise.lunchorderer.services.OrderItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for managing order items.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final MealService mealService;

    /**
     * Constructs a new OrderItemServiceImpl.
     *
     * @param orderItemRepository The repository for accessing order item data.
     * @param mealService         The service for managing meals.
     */
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, MealService mealService) {
        this.orderItemRepository = orderItemRepository;
        this.mealService = mealService;
    }

    /**
     * Creates an OrderItemEntity based on the specified meal ID and quantity.
     *
     * @param mealId   The unique identifier of the meal to include in the order item.
     * @param quantity The quantity of the meal to include in the order item.
     * @return The newly created OrderItemEntity.
     */
    @Override
    public OrderItemEntity createOrderItem(Long mealId, int quantity) {
        try {
            MealEntity mealEntity = mealService.getMeal(mealId);

            Optional<OrderItemEntity> existingOrderItem = orderItemRepository.findByMealAndQuantity(mealEntity, quantity);
            if (existingOrderItem.isPresent())
                return existingOrderItem.get();

            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setMeal(mealEntity);
            orderItemEntity.setQuantity(quantity);
            orderItemRepository.save(orderItemEntity);
            return orderItemEntity;
        } catch (EntityNotFoundException e) {
            return null;
        }
    }
}
