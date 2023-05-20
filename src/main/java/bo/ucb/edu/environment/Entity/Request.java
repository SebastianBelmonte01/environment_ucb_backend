package bo.ucb.edu.environment.Entity;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "SR_request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "SR_reservation_reservation_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "SR_environment_environment_id")
    private Environment environment;

    @Column(name = "date")
    private Date date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "people")
    private Integer people;

    @Column(name = "req_state")
    private String reqState;

    @Column(name = "reason")
    private String reason;

    @Column(name = "reason_rej")
    private String reasonRej;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "tx_host")
    private String txHost;

    @Column(name = "tx_date")
    private Date txDate;

    @Column(name = "tx_user")
    private String txUser;

    @ManyToOne
    @JoinColumn(name = "SR_professor_professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "SR_subject_professor_subject_professor_id")
    private SubjectProfessor subjectProfessor;

    public Request() {
    }

    public Request(Long requestId, Reservation reservation, Environment environment, Date date, LocalTime startTime, LocalTime endTime, Integer people, String reqState, String reason, String reasonRej, Boolean status, String txHost, Date txDate, String txUser, Professor professor, SubjectProfessor subjectProfessor) {
        this.requestId = requestId;
        this.reservation = reservation;
        this.environment = environment;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.people = people;
        this.reqState = reqState;
        this.reason = reason;
        this.reasonRej = reasonRej;
        this.status = status;
        this.txHost = txHost;
        this.txDate = txDate;
        this.txUser = txUser;
        this.professor = professor;
        this.subjectProfessor = subjectProfessor;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public String getReqState() {
        return reqState;
    }

    public void setReqState(String reqState) {
        this.reqState = reqState;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public SubjectProfessor getSubjectProfessor() {
        return subjectProfessor;
    }

    public void setSubjectProfessor(SubjectProfessor subjectProfessor) {
        this.subjectProfessor = subjectProfessor;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", people=" + people +
                ", reqState='" + reqState + '\'' +
                ", reason='" + reason + '\'' +
                ", reasonRej='" + reasonRej + '\'' +
                ", status=" + status +
                ", txHost='" + txHost + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                '}';
    }
}