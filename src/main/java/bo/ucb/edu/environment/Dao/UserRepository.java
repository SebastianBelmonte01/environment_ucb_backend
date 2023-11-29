package bo.ucb.edu.environment.Dao;
import bo.ucb.edu.environment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM sr_user u WHERE u.email = :email and u.secret = :secret", nativeQuery=true)
    User findByEmailAndSecret(
            @Param("email") String email,
            @Param("secret") String secret
    );

    User findByEmail(String email);


    String findByUserId(Long id);

    @Modifying
    @Query(value = "UPDATE sr_user u SET u.password = :password WHERE u.user_id = :id", nativeQuery=true)
    User updatePasswordByUserId(Long id, String password);


}
