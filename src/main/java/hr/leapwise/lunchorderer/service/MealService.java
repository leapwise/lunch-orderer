package hr.leapwise.lunchorderer.service;

import hr.leapwise.lunchorderer.model.Meal;
import hr.leapwise.lunchorderer.repository.MealRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public List<Meal> convertMealIdsToMeals(final List<Long> mealIds) {
        return mealIds.stream()
                .map(mealId -> mealRepository.findById(mealId)
                        .orElseThrow(() -> new EntityNotFoundException(String.format("Meal with ID %d not found!", mealId))))
                .toList();

    }
}
