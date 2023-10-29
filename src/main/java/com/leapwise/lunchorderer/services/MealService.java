package com.leapwise.lunchorderer.services;

import com.leapwise.lunchorderer.domain.entities.MealEntity;

import java.util.List;

/**
 * Service interface for managing meals.
 */
public interface MealService {

    /**
     * Retrieves a list of {@link MealEntity} objects representing available meals.
     *
     * @return A list of available meals.
     */
    List<MealEntity> getMeals();

    /**
     * Saves a meal to the database.
     *
     * @param mealEntity The meal entity to be saved.
     */
    void saveMeal(MealEntity mealEntity);

    /**
     * Retrieves a {@link MealEntity} by its unique identifier.
     *
     * @param mealId The unique identifier of the meal to retrieve.
     * @return The corresponding MealEntity.
     */
    MealEntity getMeal(Long mealId);

    /**
     * Retrieves the name of a meal by its unique identifier.
     *
     * @param id The unique identifier of the meal.
     * @return The name of the meal.
     */
    String getName(Long id);
}
