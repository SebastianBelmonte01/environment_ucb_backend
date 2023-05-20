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

    @Query(value = "SELECT * FROM SR_subject_professor WHERE SR_subject_subject_id = :subjectId AND SR_professor_professor_id = :professorId AND parallel = :parallel", nativeQuery = true)
    SubjectProfessor findBySubjectAndProfessorAndParallelIs(@Param("subjectId") Long subjectId, @Param("professorId") Long professorId, @Param("parallel") int parallel);
}
