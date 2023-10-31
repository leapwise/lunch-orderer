package hr.leapwise.lunchorderer.meal;

import java.math.BigDecimal;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;
    @Column
    private String mealName;
    @Column
    private BigDecimal price;

    public Long getMealId() {
        return mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(mealId, meal.mealId) && Objects.equals(mealName, meal.mealName) && Objects.equals(price, meal.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId, mealName, price);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", mealName='" + mealName + '\'' +
                ", price=" + price +
                '}';
    }
}
