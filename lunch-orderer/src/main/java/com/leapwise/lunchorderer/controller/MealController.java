package com.leapwise.lunchorderer.controller;

import com.leapwise.lunchorderer.models.DTO.responses.MealResponseDto;
import com.leapwise.lunchorderer.service.interfaces.MealService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MealController {

    private final MealService mealService;


    @GetMapping("/ping")
    public String ping()
    {
        return "ping";
    }

    @GetMapping("daily/menu")
    public List<MealResponseDto> getMeals()
    {
        return mealService.getMeals();
    }

    @GetMapping("/get/meal/{id}")
    public MealResponseDto getMeal(@PathVariable @NonNull Long id)
    {
        return mealService.getMealById(id);
    }

}
