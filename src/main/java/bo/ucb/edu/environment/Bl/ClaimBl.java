package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.ClaimRepository;
import bo.ucb.edu.environment.Dao.ReservationRepository;
import bo.ucb.edu.environment.Dto.ClaimDto;
import bo.ucb.edu.environment.Entity.Claim;
import bo.ucb.edu.environment.Entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Date;

@Service
public class ClaimBl {
    @Autowired
    ClaimRepository claimDao;
    @Autowired
    ReservationRepository reservationDao;

    public void saveNewClaim(String description, byte[] imageData, Long reservationId) throws IOException {
        Claim claim = new Claim();
        claim.setClaimState("Pendiente");
        claim.setImageData(imageData);
        claim.setReservation(reservationDao.findReservationByReservationId(reservationId));
        claim.setDescription(description);
        claim.setDate(new Date());
        claim.setStatus(true);
        claim.setTransactionUser("sebastianbelmonte");
        claim.setTransactionDate(new Date());
        claim.setTransactionHost("localhost");
        claimDao.save(claim);
    }







}
