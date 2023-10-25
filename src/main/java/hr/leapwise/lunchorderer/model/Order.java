package hr.leapwise.lunchorderer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "`ORDER`")
public class Order {

    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ORDER_MEALS",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MEAL_ID")
    )
    private List<Meal> meals;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
