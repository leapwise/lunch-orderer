package com.leapwise.lunchorderer.services;

import com.leapwise.lunchorderer.domain.entities.OrderEntity;

import java.util.List;

/**
 * Service interface for managing orders.
 */
public interface OrderService {

    /**
     * Saves an order with the specified list of meal IDs.
     *
     * @param mealIds The list of meal IDs to include in the order.
     * @return The unique identifier (ID) of the created order.
     */
    Long saveOrder(List<Long> mealIds);

    /**
     * Retrieves an order by its unique identifier.
     *
     * @param orderId The unique identifier of the order to retrieve.
     * @return The corresponding {@link OrderEntity} object.
     */
    OrderEntity getOrder(Long orderId);

    /**
     * Retrieves a list of all orders.
     *
     * @return A list of all orders.
     */
    List<OrderEntity> getAllOrders();

    /**
     * Checks if an order with the specified ID exists.
     *
     * @param orderId The unique identifier of the order to check for existence.
     * @return true if the order exists, false otherwise.
     */
    boolean exists(Long orderId);

    /**
     * Updates an existing order with the specified ID.
     *
     * @param orderId  The unique identifier of the order to be updated.
     * @param mealIds  The list of meal IDs to be associated with the order.
     * @return The updated OrderEntity.
     */
    OrderEntity updateOrder(Long orderId, List<Long> mealIds);
}
