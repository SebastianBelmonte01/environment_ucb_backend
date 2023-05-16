package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.CarrerDepartment;
import bo.ucb.edu.environment.Repository.CarrerDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrerDepartmenDao {
    @Autowired
    private CarrerDepartmentRepository carrerDepartmentRepository;

    public List<CarrerDepartment> findAll(){
        return carrerDepartmentRepository.findAll();
    }

}
