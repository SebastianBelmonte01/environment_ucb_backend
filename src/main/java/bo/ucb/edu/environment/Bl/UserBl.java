package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.UserRepository;
import bo.ucb.edu.environment.Dto.UserDto;
import bo.ucb.edu.environment.Entity.User;
import bo.ucb.edu.environment.Entity.UserGroup;
import bo.ucb.edu.environment.Utils.Hash;
import bo.ucb.edu.environment.Utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserBl {

    @Autowired
    private UserRepository userRepository;



    public UserDto createUser (UserDto user){
        Logger log = Logger.getLogger("bo.ucb.edu.environment.Bl.UserBl");
        Validation validation = new Validation();
        Hash hash = new Hash();
        validation.validateEmail(user.getEmail());
        if(userRepository.findByEmail(user.getEmail()) != null){
            return null;

        }
        user.setPassword(hash.hashString(user.getPassword(), user.getEmail()));
        System.out.println("HASHED Password: " + user.getPassword());
        log.info("Inserting values into User table");
        userRepository.save(new User(user.getEmail(), user.getPassword()));
        log.info("Values inserted into User table");
        return user;
    }


}
