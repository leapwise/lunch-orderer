package leapwise.internship.internshipProject.domain;

import jakarta.persistence.*;

/**
 * A persistence entity which tracks the number of orders
 *
 * @author Vito
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
