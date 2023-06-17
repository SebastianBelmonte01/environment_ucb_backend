package bo.ucb.edu.environment.Dto;

import bo.ucb.edu.environment.Entity.Reservation;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;

public class ClaimDto {
    private Long claimId;
    private ReservationDto reservationDto;
    private String resClaim;
    private Date date;
    private String desClaim;
    private String claimState;
    private String image;

    public ClaimDto() {
    }

    public ClaimDto(Long claimId, ReservationDto reservationDto, String resClaim, Date date, String desClaim, String claimState, String image) {
        this.claimId = claimId;
        this.reservationDto = reservationDto;
        this.resClaim = resClaim;
        this.date = date;
        this.desClaim = desClaim;
        this.claimState = claimState;
        this.image = image;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public ReservationDto getReservationDto() {
        return reservationDto;
    }

    public void setReservationDto(ReservationDto reservationDto) {
        this.reservationDto = reservationDto;
    }

    public String getResClaim() {
        return resClaim;
    }

    public void setResClaim(String resClaim) {
        this.resClaim = resClaim;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesClaim() {
        return desClaim;
    }

    public void setDesClaim(String desClaim) {
        this.desClaim = desClaim;
    }

    public String getClaimState() {
        return claimState;
    }

    public void setClaimState(String claimState) {
        this.claimState = claimState;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ClaimDto{" +
                "claimId=" + claimId +
                ", reservationDto=" + reservationDto +
                ", resClaim='" + resClaim + '\'' +
                ", date=" + date +
                ", desClaim='" + desClaim + '\'' +
                ", claimState='" + claimState + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
