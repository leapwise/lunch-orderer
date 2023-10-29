package com.leapwise.lunchorderer.services.impl;

import com.leapwise.lunchorderer.domain.entities.MealEntity;
import com.leapwise.lunchorderer.repositories.MealRepository;
import com.leapwise.lunchorderer.services.MealService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing meals.
 */
@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    /**
     * Constructs a new MealServiceImpl.
     *
     * @param mealRepository The repository for accessing meal data.
     */
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    /**
     * Retrieves a list of {@link MealEntity} objects representing available meals.
     *
     * @return A list of available meals.
     */
    @Override
    public List<MealEntity> getMeals() {
        return mealRepository.findAll().stream().toList();
    }

    /**
     * Saves a meal to the database.
     *
     * @param mealEntity The meal entity to be saved.
     */
    @Override
    public void saveMeal(MealEntity mealEntity) {
        mealRepository.save(mealEntity);
    }

    /**
     * Retrieves a {@link MealEntity} by its unique identifier.
     *
     * @param mealId The unique identifier of the meal to retrieve.
     * @return The corresponding MealEntity.
     */
    @Override
    public MealEntity getMeal(Long mealId) {
        return mealRepository.findById(mealId).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Retrieves the name of a meal by its unique identifier.
     *
     * @param id The unique identifier of the meal.
     * @return The name of the meal.
     */
    @Override
    public String getName(Long id) {
        try {
            return getMeal(id).getName();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }
}
