package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.UserRepository;
import bo.ucb.edu.environment.Dto.ProfessorDto;
import bo.ucb.edu.environment.Dto.SubjectDto;
import bo.ucb.edu.environment.Entity.Professor;
import bo.ucb.edu.environment.Entity.SubjectProfessor;
import bo.ucb.edu.environment.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfessorBl {
    @Autowired
    private UserRepository userDao;


    public ProfessorDto getProfessor(Long id){
        User user = userDao.getReferenceById(id);
        Professor professor = user.getSrProfessor();
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setProfessorId(professor.getProfessorId());
        professorDto.setName(professor.getName());

        List<SubjectDto> subjectsDto = new ArrayList<>();

        for(SubjectProfessor subjectProfessor: professor.getSubjectProfessors()){
            Set<Integer> parallels = new HashSet<>();
            if(subjectsDto.isEmpty()){
                SubjectDto subjectDto = new SubjectDto();
                subjectDto.setId(subjectProfessor.getSubject().getSubjectId());
                subjectDto.setName(subjectProfessor.getSubject().getName());
                parallels.add(subjectProfessor.getParallel());
                subjectDto.setParallels(parallels);
                subjectsDto.add(subjectDto);

            }
            else {
                for(int i = 0; i <= subjectsDto.size()-1; i++){
                    System.out.println(subjectsDto.get(i).getId());
                    if(subjectsDto.get(i).getId() == subjectProfessor.getSubject().getSubjectId()){
                        System.out.println(subjectsDto.get(i).getName());
                        subjectsDto.get(i).getParallels().add(subjectProfessor.getParallel());
                        break;
                    }
                    else {
                        SubjectDto subjectDto = new SubjectDto();
                        subjectDto.setId(subjectProfessor.getSubject().getSubjectId());
                        subjectDto.setName(subjectProfessor.getSubject().getName());
                        parallels.add(subjectProfessor.getParallel());
                        subjectDto.setParallels(parallels);
                        subjectsDto.add(subjectDto);
                    }
                }
            }
        }
        professorDto.setSubjects(subjectsDto);
        return professorDto;
    }
}
