package hr.leapwise.lunchorderer.ordermeal;
import hr.leapwise.lunchorderer.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMealRepository extends JpaRepository<OrderMeal, Long> {

    List<OrderMeal> findByOrder(Order order);
}