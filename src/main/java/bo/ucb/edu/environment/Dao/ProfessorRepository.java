package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query(value = "SELECT * FROM sr_professor p WHERE p.sr_user_user_id = :userId", nativeQuery=true)
    Professor getProfessorByUserId(@Param("userId") Integer userId);

}
