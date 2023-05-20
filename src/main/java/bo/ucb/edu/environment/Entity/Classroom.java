package bo.ucb.edu.environment.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "SR_classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Long classroomId;

    @ManyToOne
    @JoinColumn(name = "SR_environment_environment_id")
    private Environment environment;

    @Column(name = "code")
    private Integer code;

    @Column(name = "building")
    private String building;

    @Column(name = "cl_state")
    private String state;

    @Column(name = "QR")
    private String qr;

    @Column(name = "max_people")
    private Integer maxPeople;

    @Column(name = "min_people")
    private Integer minPeople;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "tx_date")
    private Date txDate;

    @Column(name = "tx_host")
    private String txHost;

    @Column(name = "tx_user")
    private String txUser;

    public Classroom() {
    }

    public Classroom(Long classroomId, Environment environment, Integer code, String building, String state, String qr, Integer maxPeople, Integer minPeople, Boolean status, Date txDate, String txHost, String txUser) {
        this.classroomId = classroomId;
        this.environment = environment;
        this.code = code;
        this.building = building;
        this.state = state;
        this.qr = qr;
        this.maxPeople = maxPeople;
        this.minPeople = minPeople;
        this.status = status;
        this.txDate = txDate;
        this.txHost = txHost;
        this.txUser = txUser;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Integer getMinPeople() {
        return minPeople;
    }

    public void setMinPeople(Integer minPeople) {
        this.minPeople = minPeople;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomId=" + classroomId +
                ", code=" + code +
                ", building='" + building + '\'' +
                ", state='" + state + '\'' +
                ", qr='" + qr + '\'' +
                ", maxPeople=" + maxPeople +
                ", minPeople=" + minPeople +
                ", status=" + status +
                ", txDate=" + txDate +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                '}';
    }
}