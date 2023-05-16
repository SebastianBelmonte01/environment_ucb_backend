package bo.ucb.edu.environment.Repository;

import bo.ucb.edu.environment.Entity.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironmentRepository extends JpaRepository<Environment, Long> {
}
