package bo.ucb.edu.environment.Entity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SR_professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private Long professorId;

    @OneToOne
    @JoinColumn(name = "SR_user_user_id", referencedColumnName = "user_id", unique = true)
    private User user;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_host", nullable = false)
    private String txHost;

    @Column(name = "tx_user", nullable = false)
    private String txUser;

    @Column(name = "tx_date", nullable = false)
    private Date txDate;

    @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
    private List<SubjectProfessor> subjectProfessors;

    public Professor() {
    }

    public Professor(Long professorId, User user, String name, String type, boolean status, String txHost, String txUser, Date txDate, List<SubjectProfessor> subjectProfessors) {
        this.professorId = professorId;
        this.user = user;
        this.name = name;
        this.type = type;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
        this.subjectProfessors = subjectProfessors;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<SubjectProfessor> getSubjectProfessors() {
        return subjectProfessors;
    }

    public void setSubjectProfessors(List<SubjectProfessor> subjectProfessors) {
        this.subjectProfessors = subjectProfessors;
    }
}