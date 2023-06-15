package bo.ucb.edu.environment.Entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "SR_claim")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Long claimId;

    @OneToOne
    @JoinColumn(name = "SR_reservation_reservation_id", referencedColumnName = "reservation_id")
    private Reservation reservation;

    @Column(name = "res_claim", columnDefinition = "text", nullable = false)
    private String claim;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "des_claim", columnDefinition = "text", nullable = false)
    private String description;

    @Column(name = "claim_state", length = 30, nullable = false)
    private String claimState;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_user", length = 50, nullable = false)
    private String transactionUser;

    @Column(name = "tx_date", nullable = false)
    private Date transactionDate;

    @Column(name = "tx_host", length = 100, nullable = false)
    private String transactionHost;

    public Claim() {
    }

    public Claim(Long claimId, Reservation reservation, String claim, Date date, String description, String claimState, boolean status, String transactionUser, Date transactionDate, String transactionHost) {
        this.claimId = claimId;
        this.reservation = reservation;
        this.claim = claim;
        this.date = date;
        this.description = description;
        this.claimState = claimState;
        this.status = status;
        this.transactionUser = transactionUser;
        this.transactionDate = transactionDate;
        this.transactionHost = transactionHost;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClaimState() {
        return claimState;
    }

    public void setClaimState(String claimState) {
        this.claimState = claimState;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTransactionUser() {
        return transactionUser;
    }

    public void setTransactionUser(String transactionUser) {
        this.transactionUser = transactionUser;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionHost() {
        return transactionHost;
    }

    public void setTransactionHost(String transactionHost) {
        this.transactionHost = transactionHost;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "claimId=" + claimId +
                ", reservation=" + reservation +
                ", claim='" + claim + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", claimState='" + claimState + '\'' +
                ", status=" + status +
                ", transactionUser='" + transactionUser + '\'' +
                ", transactionDate=" + transactionDate +
                ", transactionHost='" + transactionHost + '\'' +
                '}';
    }
}
