package bo.ucb.edu.environment.Entity;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "SR_reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "res_state")
    private String state;

    @Column(name = "reason_rej")
    private String reasonRej;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "tx_host")
    private String txHost;

    @Column(name = "tx_user")
    private String txUser;

    @Column(name = "tx_date")
    private Date txDate;

    public Reservation() {
    }

    public Reservation(Long reservationId, String state, String reasonRej, Boolean status, String txHost, String txUser, Date txDate) {
        this.reservationId = reservationId;
        this.state = state;
        this.reasonRej = reasonRej;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReasonRej() {
        return reasonRej;
    }

    public void setReasonRej(String reasonRej) {
        this.reasonRej = reasonRej;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", state='" + state + '\'' +
                ", reasonRej='" + reasonRej + '\'' +
                ", status=" + status +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                '}';
    }
}