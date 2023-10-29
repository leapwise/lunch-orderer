package com.leapwise.lunchorderer.service.implementations;

import com.leapwise.lunchorderer.models.DTO.responses.MealResponseDto;
import com.leapwise.lunchorderer.models.domain.Meal;
import com.leapwise.lunchorderer.repository.MealRepository;
import com.leapwise.lunchorderer.service.interfaces.MealService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MealServiceImpl implements MealService {

    private final ModelMapper mapper;
    private final MealRepository mealRepository;

    public List<MealResponseDto> getMeals() {
        List<Meal> meals = mealRepository.findAll();
        return meals
                .stream()
                .map(meal -> mapper.map(meal, MealResponseDto.class) )
                .collect(Collectors.toList());
    }

    public List<Meal> getMealsById(List<Long> mealIds) {
        return mealIds.stream()
                .map(mealId -> mealRepository.findById(mealId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Meal not found with ID: " + mealId)))
                .collect(Collectors.toList());
    }

    public MealResponseDto getMealById(Long id) {
        Optional<Meal> meal = mealRepository.findById(id);

        Meal foundMeal = meal.orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No meal with requestd id found"));

        return mapper.map(foundMeal, MealResponseDto.class);
    }

}
