package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    Claim findClaimByClaimId(Long id);

    List<Claim> findAllByStatusIsTrue();

    @Query("SELECT c FROM Claim c WHERE c.claimState = :state")
    List<Claim> findClaimsByState(@Param("state") String state);

    @Modifying
    @Query("UPDATE Claim c SET c.claimState = :state, c.resClaim = :response WHERE c.claimId = :id")
    void updateClaimState(@Param("id") Long id, @Param("state") String state, @Param("response") String response);
}
