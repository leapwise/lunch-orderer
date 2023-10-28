package leapwise.internship.internshipProject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * A persistence entity which represents a meal that is contained within an order
 *
 * @author Vito
 */
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The id of the order this meal belongs to. Corresponds to the id of the {@link Order} entity
     */
    private Long orderId;
    /**
     * The id of the menu item this meal represents. Corresponds to the id of the {@link MenuItem} entity
     */
    private Long menuItemId;
    /**
     * Tracks how many times this meal has been added to the order
     */
    private int amount;

    public Meal() {

    }

    /**
     * Simple constructor which assigns the id of the order to which the object belongs and the menu item it represents
     *
     * @param orderId    The order of the id it belongs to
     * @param menuItemId The id of the menu item it represents
     */
    public Meal(Long orderId, Long menuItemId) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.amount = 1;
    }

    /**
     * A getter for the id of the meal
     *
     * @return The id of the meal
     */
    public Long getId() {
        return id;
    }

    /**
     * A getter for the id of the order the meal belongs to
     *
     * @return The id of the order the meal belongs to
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * A getter for the id of the menu item the meal represents
     *
     * @return The id of the menu item the meal represents
     */
    public Long getMenuItemId() {
        return menuItemId;
    }

    /**
     * A getter which returns the amount of times this meal has been added to the order
     *
     * @return The amount variable
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the provided amount
     *
     * @param amount The provided amount
     */
    public void setAmount(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("The amount cannot be lower than 1");
        }
        this.amount = amount;
    }

}
