package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Request;
import bo.ucb.edu.environment.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    //Select every Request that reqState 'En Esepera'
    @Query("SELECT sr FROM Reservation sr WHERE sr.resState = 'En Espera'")
    List<Reservation> finaAllReservationsEnEspera();

    //Update the state of the request to 'Aceptada'
    @Modifying
    @Query("UPDATE Reservation sr SET sr.resState = :msg WHERE sr.reservationId = :id")
    void updateReservationState(@Param("id") Long id, @Param("msg") String msg);


}
