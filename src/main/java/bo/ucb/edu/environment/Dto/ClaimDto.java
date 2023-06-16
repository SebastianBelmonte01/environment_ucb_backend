package bo.ucb.edu.environment.Dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;

public class ClaimDto {
    private Long claimId;
    private Long reservationId;
    private String resClaim;
    private Date date;
    private String desClaim;
    private String claimState;
    private Boolean status;
    private String txUser;
    private Date txDate;
    private String txHost;
    private MultipartFile image;

    public ClaimDto() {
    }

    public ClaimDto(Long claimId, Long reservationId, String resClaim, Date date, String desClaim, String claimState, Boolean status, String txUser, Date txDate, String txHost, MultipartFile image) {
        this.claimId = claimId;
        this.reservationId = reservationId;
        this.resClaim = resClaim;
        this.date = date;
        this.desClaim = desClaim;
        this.claimState = claimState;
        this.status = status;
        this.txUser = txUser;
        this.txDate = txDate;
        this.txHost = txHost;
        this.image = image;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ClaimDto{" +
                "claimId=" + claimId +
                ", reservationId=" + reservationId +
                ", resClaim='" + resClaim + '\'' +
                ", date=" + date +
                ", desClaim='" + desClaim + '\'' +
                ", claimState='" + claimState + '\'' +
                ", status=" + status +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                ", txHost='" + txHost + '\'' +
                ", image=" + image +
                '}';
    }
}
