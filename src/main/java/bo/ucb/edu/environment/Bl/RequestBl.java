package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.*;
import bo.ucb.edu.environment.Dto.RequestDto;
import bo.ucb.edu.environment.Dto.ResponseDto;
import bo.ucb.edu.environment.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RequestBl {
    @Autowired
    private EnvironmentRepository environmentRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectProfessorRepository subjectProfessorRepository;


    public RequestDto createRequest(RequestDto requestDto, int id) {
        Professor professor = professorRepository.getProfessorByUserId(id);
        Environment environment = environmentRepository.findById(requestDto.getEnvironment()).orElseThrow();
        Subject subject = subjectRepository.findById(requestDto.getSubject()).orElseThrow();


        SubjectProfessor subjectProfessor = subjectProfessorRepository.findBySubjectAndProfessorAndParallelIs(subject.getSubjectId(), professor.getProfessorId(), requestDto.getParallel());
        Request request = new Request();
        request.setEnvironment(environment);
        request.setDate(requestDto.getDate());
        request.setStartTime(requestDto.getInitTime());
        request.setEndTime(requestDto.getEndTime());
        request.setPeople(requestDto.getPeople());
        request.setReqState("Pendiente");
        request.setReason(requestDto.getReason());
        request.setStatus(true);
        request.setTxHost("localhost");
        request.setTxDate(new Date());
        request.setTxUser("admin");
        request.setProfessor(professor);

        System.out.println("Professor: " + professor);
        System.out.println("Subject: " + subject);
        System.out.println("SubjectProfessor: " + subjectProfessor);

        request.setSubjectProfessor(subjectProfessor);
        System.out.println("Request: " + request);

        requestRepository.save(request);
        return requestDto;
    }
}
