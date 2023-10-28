package leapwise.internship.internshipProject.service;

import leapwise.internship.internshipProject.domain.Meal;
import leapwise.internship.internshipProject.domain.MenuItem;
import leapwise.internship.internshipProject.domain.Order;
import leapwise.internship.internshipProject.repository.MealRepository;
import leapwise.internship.internshipProject.repository.MenuRepository;
import leapwise.internship.internshipProject.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class which implements methods necessary for
 * the working of the {@link org.springframework.web.bind.annotation.RestController}
 *
 * @author Vito
 */
@Service
public class MealService {

    /**
     * The meal repository
     */
    private final MealRepository mealRepository;
    /**
     * The order repository
     */
    private final OrderRepository orderRepository;

    /**
     * The menu repository
     */
    private final MenuRepository menuRepository;

    /**
     * Simple constructor which assigns the provided repositories
     *
     * @param mealRepository  The provided repository that stores all meals added to the order
     * @param orderRepository The provided repository of all stored order ids
     * @param menuRepository  The provided repository of all menu items
     */
    public MealService(MealRepository mealRepository, OrderRepository orderRepository, MenuRepository menuRepository) {
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }

    /**
     * Method which finds all meals with the provided orderId
     *
     * @param orderId The provided order id
     * @return A list containing all the meals in the provided orderId
     */
    public List<Meal> getAllMealsInOrder(long orderId) {
        List<Meal> mealsInOrder = new ArrayList<>();

        List<Meal> allMeals = mealRepository.findAll();
        allMeals.forEach(m -> {
            if (m.getOrderId().equals(orderId)) {
                mealsInOrder.add(m);
            }
        });
        return mealsInOrder;
    }

    /**
     * Creates a new order from the provided list of {@link MenuItem} ids
     *
     * @param meals The ids of items to be added to the order
     * @return The id of the newly created order
     */
    public Long createNewOrder(List<Long> meals) {
        if (meals == null) {
            throw new IllegalArgumentException("Provided argument cannot be null");
        }

        orderRepository.save(new Order());

        long numOfOrders = orderRepository.count();

        this.addMenuItemsToOrder(meals, numOfOrders);
        return numOfOrders;
    }

    /**
     * Changes the content of the order with the matching provided orderId to the provided list of {@link MenuItem} ids
     *
     * @param menuItemIds The ids of new items to be added to the order
     * @param orderId     The order whose items need to be updated
     * @return A list of the newly added meals to the order with the matching provided orderId
     */
    public List<Meal> updateOrder(List<Long> menuItemIds, long orderId) {
        List<Meal> currentOrder = this.getAllMealsInOrder(orderId);
        mealRepository.deleteAll(currentOrder);

        this.addMenuItemsToOrder(menuItemIds, orderId);

        return this.getAllMealsInOrder(orderId);
    }

    /**
     * Getter for the number of orders in the repository
     *
     * @return The number of orders
     */
    public long getNumOfOrders() {
        return orderRepository.count();
    }

    /**
     * Helper function which adds menu items to the order with the matching provided orderId
     *
     * @param itemIds List of ids of menu items to be added to the order
     * @param orderId The provided order id
     */
    private void addMenuItemsToOrder(List<Long> itemIds, long orderId) {
        for (Long itemId : itemIds) {
            if (!menuRepository.existsById(itemId)) {
                throw new IllegalArgumentException("The provided menu item id: " + itemId + ", does not correspond to any item in the menu!");
            }

            Meal newMeal = findByOrderIdAndItemId(orderId, itemId);

            if (newMeal == null) {
                newMeal = new Meal(orderId, itemId);
            } else {
                newMeal.setAmount(newMeal.getAmount() + 1);
            }
            mealRepository.save(newMeal);
        }

        return;
    }

    /**
     * Helper function which finds the meal based on its orderId and itemId
     *
     * @param orderId The provided order id
     * @param id      The provided item id, which matches to the id of the {@link MenuItem} class
     * @return The {@link Meal} if found, else returns null
     */
    private Meal findByOrderIdAndItemId(long orderId, Long id) {
        List<Meal> allMeals = mealRepository.findAll();

        for (Meal meal : allMeals) {
            if (meal.getOrderId().equals(orderId) && meal.getMenuItemId().equals(id)) {
                return meal;
            }
        }

        return null;
    }

}
