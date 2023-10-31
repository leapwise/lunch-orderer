package hr.leapwise.lunchorderer.meal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MealController {
    private final MealRepository repository;

    MealController(MealRepository repository){
        this.repository = repository;
    }

    @GetMapping("/daily/menu")
    public List<Meal> getDailyMenu() {
        List<Meal> dailyMenu = repository.findAll();
        return dailyMenu;
    }

}
