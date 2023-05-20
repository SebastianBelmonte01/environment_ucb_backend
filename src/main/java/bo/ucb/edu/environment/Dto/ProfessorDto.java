package bo.ucb.edu.environment.Dto;

import java.util.List;

public class ProfessorDto {
    private Long professorId;

    private String name;
    private List<SubjectDto> subjects;


    public ProfessorDto() {
    }

    public ProfessorDto(Long professorId, String name, List<SubjectDto> subjects) {
        this.professorId = professorId;
        this.name = name;
        this.subjects = subjects;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDto> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "ProfessorDto{" +
                "professorId=" + professorId +
                ", name='" + name + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}