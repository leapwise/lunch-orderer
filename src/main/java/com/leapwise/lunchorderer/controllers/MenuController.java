package com.leapwise.lunchorderer.controllers;

import com.leapwise.lunchorderer.domain.dto.MealDto;
import com.leapwise.lunchorderer.domain.entities.MealEntity;
import com.leapwise.lunchorderer.mappers.impl.MealMapper;
import com.leapwise.lunchorderer.services.MealService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * Controller for handling menu-related requests.
 */
@RestController
public class MenuController {

    private final MealService mealService;
    private final MealMapper mealMapper;

    /**
     * Constructs a new MenuController.
     *
     * @param mealService The service responsible for managing meals.
     * @param mealMapper  The mapper used to convert between domain and DTO objects.
     */
    public MenuController(MealService mealService, MealMapper mealMapper) {
        this.mealService = mealService;
        this.mealMapper = mealMapper;
    }

    /**
     * Retrieves a list of meals available for the daily menu.
     *
     * @return A list of {@link MealDto} objects representing the available meals.
     */
    @GetMapping(path = "/daily/menu")
    public List<MealDto> listMeals(){
        List<MealEntity> meals = this.mealService.getMeals();
        return meals.stream()
                .map(mealMapper::mapTo)
                .filter(Objects::nonNull)
                .toList();
    }
}
