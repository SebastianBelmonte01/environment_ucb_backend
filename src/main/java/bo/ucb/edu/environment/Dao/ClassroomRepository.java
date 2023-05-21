package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Classroom;
import bo.ucb.edu.environment.Entity.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    List<Classroom> findAllByEnvironmentEnvironmentId(Long id);

    Classroom findClassroomByEnvironment(Environment environment);
}
