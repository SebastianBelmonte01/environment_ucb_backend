package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.*;
import bo.ucb.edu.environment.Dto.RequestDto;
import bo.ucb.edu.environment.Dto.ResponseDto;
import bo.ucb.edu.environment.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        Environment environment = environmentRepository.findEnvironmentByType(requestDto.getEnvironment());
        Subject subject = subjectRepository.findSubjectByName(requestDto.getSubject());


        SubjectProfessor subjectProfessor = subjectProfessorRepository.findBySubjectAndProfessorAndParallelIs(subject, professor, requestDto.getParallel());
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
        request.setSubjectProfessor(subjectProfessor);
        requestRepository.save(request);
        return requestDto;
    }

    public RequestDto getLastRequest (int id){
        Professor professor = professorRepository.getProfessorByUserId(id);
        Request request = requestRepository.findTopByProfessorOrderByRequestIdDesc(professor);
        RequestDto requestDto = new RequestDto();
        requestDto.setId(request.getRequestId());
        requestDto.setProfessorName(request.getProfessor().getName());
        requestDto.setDate(request.getDate());
        requestDto.setInitTime(request.getStartTime());
        requestDto.setEndTime(request.getEndTime());
        requestDto.setEnvironment(request.getEnvironment().getType());
        requestDto.setSubject(request.getSubjectProfessor().getSubject().getName());
        requestDto.setParallel(request.getSubjectProfessor().getParallel());
        requestDto.setPeople(request.getPeople());
        requestDto.setReason(request.getReason());
        return requestDto;
    }



}
