package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.User;
import bo.ucb.edu.environment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginDao {
    @Autowired
    private UserRepository userRepository;

    public User findByEmailAndSecret(String email, String secret){
        User user = userRepository.findByEmailAndSecret(email, secret);
        if(user != null){
            return user;
        }
        throw new IllegalArgumentException("Invalid credentials");

    }
}
