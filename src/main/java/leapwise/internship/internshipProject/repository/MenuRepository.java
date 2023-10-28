package leapwise.internship.internshipProject.repository;

import leapwise.internship.internshipProject.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface which allows access to all items in the menu
 *
 * @author Vito
 */
@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long> {
}
