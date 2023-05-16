package bo.ucb.edu.environment.Dto;

public class EnvironmentDto {
    private int id;
    private String type;
    private String state;

    public EnvironmentDto() {
    }

    public EnvironmentDto(int id, String type, String state) {
        this.id = id;
        this.type = type;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "EnvironmentDto{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
