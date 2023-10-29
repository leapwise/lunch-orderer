package com.leapwise.lunchorderer.services;

import com.leapwise.lunchorderer.domain.entities.OrderItemEntity;

/**
 * Service interface for managing order items.
 */
public interface OrderItemService {

    /**
     * Creates an OrderItemEntity based on the specified meal ID and quantity.
     *
     * @param mealId   The unique identifier of the meal to include in the order item.
     * @param quantity The quantity of the meal to include in the order item.
     * @return The newly created OrderItemEntity.
     */
    OrderItemEntity createOrderItem(Long mealId, int quantity);
}
