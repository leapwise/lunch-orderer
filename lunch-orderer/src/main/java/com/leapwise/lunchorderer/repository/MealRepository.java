package com.leapwise.lunchorderer.repository;

import com.leapwise.lunchorderer.models.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
