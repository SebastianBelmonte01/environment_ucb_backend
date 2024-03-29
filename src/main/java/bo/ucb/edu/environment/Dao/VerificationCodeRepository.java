package bo.ucb.edu.environment.Dao;
import bo.ucb.edu.environment.Entity.User;
import bo.ucb.edu.environment.Entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    //Funcion para agregar un codigo de verificacion

    VerificationCode saveAndFlush(VerificationCode verificationCode);


    //Funcion para buscar un codigo de verificacion por el codigo

    @Query(value = "SELECT * FROM verification_code WHERE code = :code", nativeQuery = true)
    VerificationCode findVerificationCodeByCode(@Param("code") String code);

    //Funcion para actualizar el estado de un codigo de verificacion
    @Transactional
    @Modifying
    @Query(value = "UPDATE verification_code SET status = false WHERE code = ?1", nativeQuery = true)
    int updateStatus(Long code);
}