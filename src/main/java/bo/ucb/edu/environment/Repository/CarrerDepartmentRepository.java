package bo.ucb.edu.environment.Repository;

import bo.ucb.edu.environment.Entity.CarrerDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrerDepartmentRepository extends JpaRepository<CarrerDepartment, Long> {

}
