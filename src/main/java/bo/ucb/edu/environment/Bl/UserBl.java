package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.UserRepository;
import bo.ucb.edu.environment.Dto.UserDto;
import bo.ucb.edu.environment.Utils.Hash;
import bo.ucb.edu.environment.Utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBl {

    @Autowired
    private UserRepository userRepository;


    public UserDto createUser (UserDto user){
        Validation validation = new Validation();
        Hash hash = new Hash();
        validation.validateEmail(user.getEmail());
        user.setPassword(hash.hashString(user.getPassword(), user.getEmail()));
        System.out.println("Password: " + user.getPassword());
        //TODO need to save the values on the DB
        return user;
    }


}
