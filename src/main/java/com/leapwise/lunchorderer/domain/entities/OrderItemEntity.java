package com.leapwise.lunchorderer.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an order item entity stored in the database.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orderItems")
public class OrderItemEntity {

    /**
     * Unique identifier for the order item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The {@link MealEntity} object associated with this order item.
     */
    @ManyToOne
    private MealEntity meal;

    /**
     * The quantity of the meal in this order item.
     */
    private int quantity;
}
