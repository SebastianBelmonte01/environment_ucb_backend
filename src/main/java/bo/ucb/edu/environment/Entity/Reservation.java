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

    @Column(name = "SR_classroom_classroom_id", nullable = false)
    private Long classroomId;

    @OneToOne
    @JoinColumn(name = "SR_request_request_id", referencedColumnName = "request_id", nullable = false)
    private Request request;

    @Column(name = "res_state", nullable = false, length = 30)
    private String resState;

    @Column(name = "reason_rej")
    private String reasonRej;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "tx_host", nullable = false, length = 30)
    private String txHost;

    @Column(name = "tx_user", nullable = false, length = 50)
    private String txUser;

    @Column(name = "tx_date", nullable = false)
    private Date txDate;

public Reservation() {
    }

    public Reservation(Long reservationId, Long classroomId, Request request, String resState, String reasonRej, Boolean status, String txHost, String txUser, Date txDate) {
        this.reservationId = reservationId;
        this.classroomId = classroomId;
        this.request = request;
        this.resState = resState;
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

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getResState() {
        return resState;
    }

    public void setResState(String resState) {
        this.resState = resState;
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
                ", classroomId=" + classroomId +
                ", request=" + request +
                ", resState='" + resState + '\'' +
                ", reasonRej='" + reasonRej + '\'' +
                ", status=" + status +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                '}';
    }
}