package bo.ucb.edu.environment.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "SR_career_department")
public class CarrerDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "career_department_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_date", nullable = false)
    private Date txDate;

    @Column(name = "tx_host", nullable = false)
    private String txHost;

    @Column(name = "tx_user", nullable = false)
    private String txUser;

    public CarrerDepartment() {
    }

    public CarrerDepartment(Long id, String name, String code, boolean status, Date txDate, String txHost, String txUser) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.status = status;
        this.txDate = txDate;
        this.txHost = txHost;
        this.txUser = txUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
        return "CarrerDepartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", status=" + status +
                ", txDate=" + txDate +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                '}';
    }
}
