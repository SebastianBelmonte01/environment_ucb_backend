package bo.ucb.edu.environment.Entity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "SR_environment")
public class Environment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "environment_id")
    private Long environmentId;

    @Column(name = "type")
    private String type;

    @Column(name = "env_state")
    private String envState;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "tx_host")
    private String txHost;

    @Column(name = "tx_user")
    private String txUser;

    @Column(name = "tx_date")
    private Date txDate;

    @OneToMany(mappedBy = "environment")
    private List<Classroom> classrooms;

    public Environment() {
    }

    public Environment(Long environmentId, String type, String envState, Boolean status, String txHost, String txUser, Date txDate, List<Classroom> classrooms) {
        this.environmentId = environmentId;
        this.type = type;
        this.envState = envState;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
        this.classrooms = classrooms;
    }

    public Long getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(Long environmentId) {
        this.environmentId = environmentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnvState() {
        return envState;
    }

    public void setEnvState(String envState) {
        this.envState = envState;
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

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "environmentId=" + environmentId +
                ", type='" + type + '\'' +
                ", envState='" + envState + '\'' +
                ", status=" + status +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                ", classrooms=" + classrooms +
                '}';
    }
}
