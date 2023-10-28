package leapwise.internship.internshipProject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * A persistence entity which models a menu item which can be chosen to add to the order
 *
 * @author Vito
 */
@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The description of the item in the menu
     */
    private String itemDescription;
    /**
     * The price of the item in the menu
     */
    private double price;

    public MenuItem() {

    }

    /**
     * A constructor which assigns the provided item description and price
     *
     * @param itemDescription The provided description of the item
     * @param price           The provided price of the item
     */
    public MenuItem(String itemDescription, double price) {
        this.itemDescription = itemDescription;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    /**
     * A getter for the item description
     *
     * @return The item description
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Sets the provided item description
     *
     * @param itemDescription The provided item description
     */
    public void setItemDescription(String itemDescription) {
        if (itemDescription == null) {
            throw new IllegalArgumentException("The provided argument cannot be null");
        }
        this.itemDescription = itemDescription;
    }

    /**
     * A getter for the price of the item
     *
     * @return The price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the provided price
     *
     * @param price The provided price
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Cannot set a negative price");
        }

        this.price = price;
    }
}
