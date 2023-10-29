package com.leapwise.lunchorderer.repositories;

import com.leapwise.lunchorderer.domain.entities.MealEntity;
import com.leapwise.lunchorderer.domain.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Repository interface for managing OrderItemEntity objects in the database.
 */
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    /**
     * Custom query to find an {@link OrderItemEntity} object by matching the meal and quantity.
     *
     * @param meal     The meal to match.
     * @param quantity The quantity to match.
     * @return An optional containing the matching order item if found.
     */
    @Query("SELECT oi FROM OrderItemEntity oi WHERE oi.meal = :meal AND oi.quantity = :quantity")
    Optional<OrderItemEntity> findByMealAndQuantity(MealEntity meal, int quantity);
}
