package leapwise.internship.internshipProject.controller;


import leapwise.internship.internshipProject.domain.Meal;
import leapwise.internship.internshipProject.domain.MenuItem;
import leapwise.internship.internshipProject.repository.MenuRepository;
import leapwise.internship.internshipProject.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Rest controller which implements the 3 required APIs
 *
 * @author Vito
 */
@RestController
public class MealController {

    /**
     * Allows access to the menu repository
     */
    private final MenuRepository menuRepository;
    /**
     * Allows access to the meal service
     */
    private final MealService mealService;

    /**
     * Simple constructor which assigns the provided repository and service
     *
     * @param menuRepository The provided menu repository
     * @param mealService    The provided meal service
     */
    public MealController(MenuRepository menuRepository, MealService mealService) {
        this.menuRepository = menuRepository;
        this.mealService = mealService;
    }

    /**
     * Method which implements the 1. API.
     * Finds all the items in the menu repository and wraps them in a response object
     *
     * @return The response object containing a {@link List} of all items in the menu
     */
    @GetMapping("/daily/menu")
    public ResponseEntity<List<MenuItem>> getMenuItems() {
        List<MenuItem> items = menuRepository.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    /**
     * Method which implements the 2. API.
     * Creates a new order from the provided items in the argument.
     *
     * @param itemIds The provided item ids
     * @return A response object which contains the id of the newly created order
     */
    @PostMapping("/order")
    public ResponseEntity<Long> storeOrder(@RequestBody List<Long> itemIds) {
        Long newOrderId = mealService.createNewOrder(itemIds);
        return new ResponseEntity<>(newOrderId, HttpStatus.CREATED);
    }

    /**
     * Method which implements the 3. API.
     * Finds the order based on the provided id, and changes its contents to the provided list of items.
     *
     * @param itemIds The new {@link List} of items to be added to the order
     * @param id      The id of the order to be updated
     * @return A response object which contains a {@link List} of all newly added items to the order
     */
    @PutMapping("/order/{id}")
    public ResponseEntity<List<Meal>> updateOrder(@RequestBody List<Long> itemIds, @PathVariable long id) {
        if (id > mealService.getNumOfOrders()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The provided id does not exist");
        }
        List<Meal> updatedOrder = mealService.updateOrder(itemIds, id);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
}
