package leapwise.internship.internshipProject.repository;

import leapwise.internship.internshipProject.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface which allows access to order numbers
 *
 * @author Vito
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
