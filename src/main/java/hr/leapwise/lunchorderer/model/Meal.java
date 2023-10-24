package hr.leapwise.lunchorderer.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Meal {

    @Id
    @Column(name = "MEAL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;

    @Column(name = "MEAL_NAME")
    private String mealName;

    @Column(name = "PRICE")
    private Double price;
}
