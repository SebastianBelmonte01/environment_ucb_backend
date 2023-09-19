package bo.ucb.edu.environment.Dto;
public class ChangePasswordDto{
    private String email;
    private String newPassword;

    public ChangePasswordDto() {
    }

    public ChangePasswordDto(String email, String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String username) {
        this.email = email;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "{" +
                " email='" + getEmail() + "'" +
                ", newPassword='" + getNewPassword() + "'" +
                "}";
    }
}