package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.User;
import bo.ucb.edu.environment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        return userRepository.findById(id).get();
    }



}
