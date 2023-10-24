package hr.leapwise.lunchorderer.service;

import hr.leapwise.lunchorderer.model.Meal;
import hr.leapwise.lunchorderer.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
}
