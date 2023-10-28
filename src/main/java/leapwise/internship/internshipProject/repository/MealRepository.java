package leapwise.internship.internshipProject.repository;

import leapwise.internship.internshipProject.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface which allows access to all meals added to orders
 *
 * @author Vito
 */
@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}
