package com.leapwise.lunchorderer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) representing a meal.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealDto {

    /**
     * Unique identifier for the meal.
     */
    private Long id;

    /**
     * The name of the meal.
     */
    private String name;

    /**
     * The price of the meal.
     */
    private BigDecimal price;
}
