package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Claim;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    Claim findClaimByClaimId(Long id);
/*
    select * from sr_user su, sr_professor sp, sr_request sreq, sr_reservation sr, sr_claim sc
    WHERE su.user_id = sp.sr_user_user_id
    AND sp.professor_id = sreq.sr_professor_professor_id
    AND sreq.request_id = sr.sr_request_request_id
    AND sr.reservation_id = sc.sr_reservation_reservation_id
    AND sc.claim_state = 'Pendiente'

 */

    @Query("SELECT c FROM User u, Professor p, Request r, Reservation res, Claim  c " +
            "WHERE u.userId  =  :id " +
            "AND  p.user.userId = u.userId " +
            " AND p.professorId = r.professor.professorId" +
            " AND r.requestId = res.request.requestId" +
            " AND res.reservationId = c.reservation.reservationId" +
            " AND c.claimState = :status")
    List<Claim> findAllByUserAndStatus(@Param("status") String status , @Param("id") Long id);

    List<Claim> findAllByStatusIsTrue();

    @Query("SELECT c FROM Claim c WHERE c.claimState = :state")
    List<Claim> findClaimsByState(@Param("state") String state);

    @Modifying
    @Query("UPDATE Claim c SET c.claimState = :state WHERE c.claimId = :id")
    @Transactional
    void updateClaimState(@Param("id") Long id, @Param("state") String state);

    @Modifying
    @Query("UPDATE Claim c SET c.claimState = :state , c.status = false WHERE c.claimId = :id")
    @Transactional
    void deleteClaim(@Param("id") Long id, @Param("state") String state);

}
