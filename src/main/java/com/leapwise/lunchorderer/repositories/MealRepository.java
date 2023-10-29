package com.leapwise.lunchorderer.repositories;

import com.leapwise.lunchorderer.domain.entities.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing MealEntity objects in the database.
 */
@Repository
public interface MealRepository extends JpaRepository<MealEntity, Long> {
}
