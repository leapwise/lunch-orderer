package com.leapwise.lunchorderer.repository;

import com.leapwise.lunchorderer.models.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o ORDER BY o.updatedAt DESC")
    List<Order> findLatestOrder();

}
