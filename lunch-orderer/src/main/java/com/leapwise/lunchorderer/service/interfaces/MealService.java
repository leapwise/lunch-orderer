package com.leapwise.lunchorderer.service.interfaces;

import com.leapwise.lunchorderer.models.DTO.responses.MealResponseDto;
import com.leapwise.lunchorderer.models.domain.Meal;

import java.util.List;

public interface MealService {

    List<MealResponseDto> getMeals();

    List<Meal> getMealsById(List<Long> mealIds);

    MealResponseDto getMealById(Long id);

}
