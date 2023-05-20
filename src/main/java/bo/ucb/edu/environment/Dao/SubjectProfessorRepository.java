package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Professor;
import bo.ucb.edu.environment.Entity.Subject;
import bo.ucb.edu.environment.Entity.SubjectProfessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectProfessorRepository extends JpaRepository<SubjectProfessor, Long> {

    SubjectProfessor findBySubjectAndProfessorAndParallelIs(Subject subject, Professor professor, int parallel);
}
