package bo.ucb.edu.environment.Dto;

public class LoginDto {
    private String email;
    private String secret;

    public LoginDto() {
    }

    public LoginDto(String email, String secret) {
        this.email = email;
        this.secret = secret;
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

    @Override
    public String toString() {
        return "LoginDto{" +
                "email='" + email + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
