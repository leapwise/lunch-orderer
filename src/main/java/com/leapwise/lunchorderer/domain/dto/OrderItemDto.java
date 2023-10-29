package com.leapwise.lunchorderer.domain.dto;

import com.leapwise.lunchorderer.domain.entities.MealEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing an order item.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {

    /**
     * Unique identifier for the order item.
     */
    private Long id;

    /**
     * The {@link MealEntity} object associated with this order item.
     */
    private MealEntity meal;

    /**
     * The quantity of the meal in this order item.
     */
    private int quantity;
}
