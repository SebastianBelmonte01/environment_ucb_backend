package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Entity.CarrerDepartment;
import bo.ucb.edu.environment.Repository.CarrerDepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrerDepartmentBl {
    private CarrerDepartmentRepository carrerDepartmentRepository;

    public CarrerDepartmentBl(CarrerDepartmentRepository carrerDepartmentRepository) {
        this.carrerDepartmentRepository = carrerDepartmentRepository;
    }

    public CarrerDepartment save (CarrerDepartment newCarrerDepartment){
        return carrerDepartmentRepository.save(newCarrerDepartment);
    }



}
