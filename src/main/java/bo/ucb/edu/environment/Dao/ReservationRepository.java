package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Professor;
import bo.ucb.edu.environment.Entity.Request;
import bo.ucb.edu.environment.Entity.Reservation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    //Select every Request that reqState 'En Esepera'
    @Query("SELECT sr FROM Reservation sr WHERE sr.resState = :state")
    List<Reservation> findAllReservationsState(@Param("state") String state);

    //Update the state of the request to 'Aceptada'
    @Modifying
    @Query("UPDATE Reservation sr SET sr.resState = :msg WHERE sr.reservationId = :id")
    @Transactional
    void updateReservationState(@Param("id") Long id, @Param("msg") String msg);

    @Modifying
    @Query("UPDATE Reservation sr SET sr.resState = :msg, sr.status = false WHERE sr.reservationId = :id")
    @Transactional
    void deleterReservation(@Param("id") Long id, @Param("msg") String msg);

    @Query("SELECT new bo.ucb.edu.environment.Entity.Reservation(sres.reservationId, sres.classroomId, sres.request, sres.resState, sres.reasonRej, sres.status, sres.txHost, sres.txUser, sres.txDate) " +
            "FROM Professor sp, Request sr, Reservation sres, Classroom sc, Environment se " +
            "WHERE sp.professorId = :professorId " +
            "AND sr.requestId = sres.request.requestId " +
            "AND se.environmentId = sr.environment.environmentId " +
            "AND sres.classroomId = sc.classroomId " +
            "AND sc.environment.environmentId = se.environmentId " +
            "AND sres.resState = :state")
    List<Reservation> findAllReservationsByProfesorAndResState(@Param("professorId") Long professorId, @Param("state") String state);

    @Modifying
    @Query("UPDATE Reservation sr SET sr.reasonRej = :msg WHERE sr.reservationId = :id")
    void updateReservationRejectionMessage(@Param("id") Long id, @Param("msg") String msg);

    Reservation findReservationByRequest(Request request);

    @Modifying
    @Query("UPDATE Reservation sr SET sr.resState = :msg, sr.status = false WHERE sr.reservationId = :id")
    void updateReservationDeletion(@Param("id") Long id, @Param("msg") String msg);

    Reservation findReservationByReservationId(Long id);


}