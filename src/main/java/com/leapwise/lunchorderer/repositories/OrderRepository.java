package com.leapwise.lunchorderer.repositories;

import com.leapwise.lunchorderer.domain.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing OrderEntity objects in the database.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
