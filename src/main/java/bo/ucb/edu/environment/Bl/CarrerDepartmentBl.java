package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Entity.CarrerDepartment;
import bo.ucb.edu.environment.Dao.CarrerDepartmentRepository;
import org.springframework.stereotype.Service;

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
