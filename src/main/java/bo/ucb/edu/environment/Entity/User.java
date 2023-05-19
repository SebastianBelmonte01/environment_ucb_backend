package bo.ucb.edu.environment.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "SR_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "secret", nullable = false, columnDefinition = "TEXT")
    private String secret;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_host", nullable = false, length = 100)
    private String txHost;

    @Column(name = "tx_user", nullable = false, length = 50)
    private String txUser;

    @Column(name = "tx_date", nullable = false)
    private Date txDate;

    @OneToOne(mappedBy = "user")
    private Professor srProfessor;

    public User() {
    }

    public User(Long userId, String email, String secret, boolean status, String txHost, String txUser, Date txDate, Professor srProfessor) {
        this.userId = userId;
        this.email = email;
        this.secret = secret;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
        this.srProfessor = srProfessor;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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

    public Professor getSrProfessor() {
        return srProfessor;
    }

    public void setSrProfessor(Professor srProfessor) {
        this.srProfessor = srProfessor;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", secret='" + secret + '\'' +
                ", status=" + status +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                ", srProfessor=" + srProfessor +
                '}';
    }
}