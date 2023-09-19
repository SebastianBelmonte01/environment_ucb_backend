package bo.ucb.edu.environment.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "srUser", cascade = CascadeType.ALL)
    private List<UserGroup> userGroups;

    public User() {
    }

    public User(Long userId, String email, String secret, boolean status, String txHost, String txUser, Date txDate, Professor srProfessor, List<UserGroup> userGroups) {
        this.userId = userId;
        this.email = email;
        this.secret = secret;
        this.status = true;
        this.txHost = "Localhost";
        this.txUser = "USuario";
        this.txDate = new Date();
        this.srProfessor = srProfessor;
        this.userGroups = userGroups;
    }

    public User (String email, String secret){
        this.email = email;
        this.secret = secret;
        this.status = true;
        this.txHost = "Localhost";
        this.txUser = "Usuario";
        this.txDate = new Date();
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

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }


}