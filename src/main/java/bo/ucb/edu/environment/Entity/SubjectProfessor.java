package bo.ucb.edu.environment.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "SR_subject_professor")
public class SubjectProfessor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_professor_id")
    private Long subjectProfessorId;

    @ManyToOne
    @JoinColumn(name = "SR_professor_professor_id", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "SR_subject_subject_id", nullable = false)
    private Subject subject;

    @Column(name = "parallel", nullable = false)
    private int parallel;

    public SubjectProfessor() {
    }

    public SubjectProfessor(Long subjectProfessorId, Professor professor, Subject subject, int parallel) {
        this.subjectProfessorId = subjectProfessorId;
        this.professor = professor;
        this.subject = subject;
        this.parallel = parallel;
    }

    public Long getSubjectProfessorId() {
        return subjectProfessorId;
    }

    public void setSubjectProfessorId(Long subjectProfessorId) {
        this.subjectProfessorId = subjectProfessorId;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getParallel() {
        return parallel;
    }

    public void setParallel(int parallel) {
        this.parallel = parallel;
    }

    @Override
    public String toString() {
        return "SubjectProfessor{" +
                "subjectProfessorId=" + subjectProfessorId +
                ", parallel=" + parallel +
                '}';
    }
}
