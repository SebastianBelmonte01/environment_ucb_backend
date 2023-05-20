package bo.ucb.edu.environment.Dto;

import java.util.List;
import java.util.Set;

public class SubjectDto {
    private Long id;
    private String name;

    private Set<Integer> parallels;

    public SubjectDto() {
    }

    public SubjectDto(Long id, String name, Set<Integer> parallels) {
        this.id = id;
        this.name = name;
        this.parallels = parallels;
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

    public Set<Integer> getParallels() {
        return parallels;
    }

    public void setParallels(Set<Integer> parallels) {
        this.parallels = parallels;
    }
}