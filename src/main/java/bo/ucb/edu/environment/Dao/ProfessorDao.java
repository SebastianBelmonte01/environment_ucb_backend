package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Professor;
import bo.ucb.edu.environment.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorDao {
    @Autowired
    private ProfessorRepository pofessorRepository;

    public Professor getProfessorById(int userId){
        return pofessorRepository.getProfessorByUserId(userId);
    }




}
