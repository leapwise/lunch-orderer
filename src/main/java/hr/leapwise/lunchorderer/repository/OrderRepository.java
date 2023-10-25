package hr.leapwise.lunchorderer.repository;

import hr.leapwise.lunchorderer.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCreatedAtAfter(LocalDateTime timeAfter);
}
