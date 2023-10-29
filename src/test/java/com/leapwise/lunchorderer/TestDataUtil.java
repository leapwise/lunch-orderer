package com.leapwise.lunchorderer;

import com.leapwise.lunchorderer.domain.entities.MealEntity;

import java.math.BigDecimal;

public class TestDataUtil {

    public static MealEntity createTestMealEntityA() {
        return MealEntity.builder()
                .id(1L)
                .name("Pizza")
                .price(BigDecimal.valueOf(8.5))
                .build();
    }

    public static MealEntity createTestMealEntityB() {
        return MealEntity.builder()
                .id(2L)
                .name("Tuna Burger")
                .price(BigDecimal.valueOf(7.5))
                .build();
    }

    public static MealEntity createTestMealEntityC() {
        return MealEntity.builder()
                .id(3L)
                .name("Chicken Salad")
                .price(BigDecimal.valueOf(7.0))
                .build();
    }

    public static MealEntity createTestMealEntityD() {
        return MealEntity.builder()
                .id(4L)
                .name("Tacos")
                .price(BigDecimal.valueOf(8.0))
                .build();
    }
}
