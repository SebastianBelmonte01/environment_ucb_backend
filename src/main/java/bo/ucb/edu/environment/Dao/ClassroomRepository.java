package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Classroom;
import bo.ucb.edu.environment.Entity.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    List<Classroom> findAllByEnvironmentEnvironmentId(Long id);
}
