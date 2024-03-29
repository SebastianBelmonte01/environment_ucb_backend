package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, Long> {
    Environment findEnvironmentByType(String type);
}
