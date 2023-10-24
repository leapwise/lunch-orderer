package hr.leapwise.lunchorderer.controller;

import hr.leapwise.lunchorderer.model.Meal;
import hr.leapwise.lunchorderer.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/daily/menu")
    public List<Meal> getDailyMenu() {
        return mealService.getAllMeals();
    }
}
