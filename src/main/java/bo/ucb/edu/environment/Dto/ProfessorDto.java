package bo.ucb.edu.environment.Dto;

import java.util.List;

public class ProfessorDto {
    private int professorId;

    private String name;
    private List<SubjectDto> subjects;


    public ProfessorDto() {
    }

    public ProfessorDto(int professorId, String name, List<SubjectDto> subjects) {
        this.professorId = professorId;
        this.name = name;
        this.subjects = subjects;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
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