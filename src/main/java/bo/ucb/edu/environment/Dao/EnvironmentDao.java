package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Environment;
import bo.ucb.edu.environment.Repository.EnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EnvironmentDao {
    @Autowired
    private EnvironmentRepository environmentRepository;

    public List<Environment> findAll(){
        return environmentRepository.findAll();
    }

}
