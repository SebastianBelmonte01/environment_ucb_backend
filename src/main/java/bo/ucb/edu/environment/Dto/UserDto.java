package bo.ucb.edu.environment.Dto;

public class UserDto {

    private String name;
    private String email;
    private String type;
    private String password;

public UserDto() {
    }

    public UserDto(String name, String email, String type, String password) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
