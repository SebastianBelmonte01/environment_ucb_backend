package bo.ucb.edu.environment.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public void validateEmail(String email) {
        String regex = ".*@ucb\\.edu\\.bo$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new EnvironmentReservationException("El correo no es valido");
        }
    }
}
