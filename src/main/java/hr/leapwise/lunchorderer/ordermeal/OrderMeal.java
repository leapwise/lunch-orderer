package hr.leapwise.lunchorderer.ordermeal;
import hr.leapwise.lunchorderer.meal.Meal;
import hr.leapwise.lunchorderer.order.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

@Entity
public class OrderMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderMealId;
    @ManyToOne
    @JoinColumn(name = "o_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "m_id")
    private Meal meal;


    public OrderMeal(Long orderMealId, Order order, Meal meal) {
        this.orderMealId = orderMealId;
        this.order = order;
        this.meal = meal;
    }

    public OrderMeal() {

    }

    public Long getOrderMealId() {
        return orderMealId;
    }

    public void setOrderMealId(Long orderMealId) {
        this.orderMealId = orderMealId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}