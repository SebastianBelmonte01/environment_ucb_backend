package bo.ucb.edu.environment.Entity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SR_subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private int code;

    @Column(name = "credits", nullable = false)
    private int credits;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_host", nullable = false)
    private String txHost;

    @Column(name = "tx_user", nullable = false)
    private String txUser;

    @Column(name = "tx_date", nullable = false)
    private Date txDate;

    @OneToMany(mappedBy = "subject")
    private List<SubjectProfessor> subjectProfessors;

    public Subject() {
    }

    public Subject(int subjectId, String name, int code, int credits, boolean status, String txHost, String txUser, Date txDate, List<SubjectProfessor> subjectProfessors) {
        this.subjectId = subjectId;
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
        this.subjectProfessors = subjectProfessors;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
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

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", credits=" + credits +
                ", status=" + status +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                ", subjectProfessors=" + subjectProfessors +
                '}';
    }
}
