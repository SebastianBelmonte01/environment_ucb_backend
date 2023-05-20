package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Professor;
import bo.ucb.edu.environment.Entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Request findTopByProfessorOrderByRequestIdDesc (Professor professor);

}
