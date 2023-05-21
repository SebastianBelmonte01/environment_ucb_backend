package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.*;
import bo.ucb.edu.environment.Dto.RequestDto;
import bo.ucb.edu.environment.Dto.RequestSearchDto;
import bo.ucb.edu.environment.Dto.ResponseDto;
import bo.ucb.edu.environment.Entity.*;
import bo.ucb.edu.environment.Utils.ReservationTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

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
    @Autowired
    private ClassroomRepository classroomRepository;


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
        Request request = requestRepository.findTopByProfessorAndStatusIsTrueOrderByRequestIdDesc(professor);
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

    public List<RequestDto> getAllRequests(int id) {
        Professor professor = professorRepository.getProfessorByUserId(id);
        List<Request> requests = requestRepository.findAllByProfessorAndStatusIsTrue(professor);
        List<RequestDto> requestDtos = new ArrayList<>();
        for(Request request : requests){
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
            requestDtos.add(requestDto);
        }
        return requestDtos;
    }

    public RequestDto cancelRequest(int userId, Long requestId){
        Professor professor = professorRepository.getProfessorByUserId(userId);
        Request request = requestRepository.findRequestByRequestIdAndProfessor(requestId, professor);
        request.setStatus(false);
        request.setReqState("Cancelado");
        Request updatedRequest = requestRepository.save(request);
        RequestDto requestDto = new RequestDto();
        requestDto.setId(updatedRequest.getRequestId());
        requestDto.setProfessorName(updatedRequest.getProfessor().getName());
        requestDto.setDate(updatedRequest.getDate());
        requestDto.setInitTime(updatedRequest.getStartTime());
        requestDto.setEndTime(updatedRequest.getEndTime());
        requestDto.setEnvironment(updatedRequest.getEnvironment().getType());
        requestDto.setSubject(updatedRequest.getSubjectProfessor().getSubject().getName());
        requestDto.setParallel(updatedRequest.getSubjectProfessor().getParallel());
        requestDto.setPeople(updatedRequest.getPeople());
        requestDto.setReason(updatedRequest.getReason());
        return  requestDto;
    }

    public List<RequestSearchDto> asignEnvironment (Long id) {
        List<Classroom> classrooms = classroomRepository.findAllByEnvironmentEnvironmentId(id);
        Map<Long, List<ReservationTime>> map = new HashMap<>();
        for(Classroom classroom : classrooms){
            ReservationTime reservationTime = new ReservationTime();
            List<ReservationTime> reservationTimeList = new ArrayList<>();
            reservationTime.setInitTime(LocalTime.of(8,0,0 ));
            reservationTime.setEndTiem(LocalTime.of(20, 0, 0));
            reservationTimeList.add(reservationTime);
            map.put(classroom.getClassroomId(), reservationTimeList);
        }
        Date date = Date.from(LocalDate.of(2023, 5, 20).atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(LocalDate.of(2023, 5, 20));
        List<RequestSearchDto> requestSearchDtoList = requestRepository.findAllRequests( 1, date);
        Set<Long> requestsAtended = new HashSet<>();
        for(RequestSearchDto requestSearchDto : requestSearchDtoList) {
            List<ReservationTime> res = map.get(requestSearchDto.getClassroomId());
            for(int i = 0; i<= res.size()-1; i++) {
                //If the init time is before the request init time,
                //And end time is after the request end time
                // Add to res a new Array [init, initRes as end], [endRes as init, end]
                if(requestsAtended.isEmpty() || !requestsAtended.contains(requestSearchDto.getRequestId())){
                    if(res.get(i).getInitTime().isBefore(requestSearchDto.getStartTime()) && res.get(i).getEndTiem().isAfter(requestSearchDto.getEndTime())) {
                        LocalTime endTime = res.get(i).getEndTiem();
                        res.get(i).setEndTiem(requestSearchDto.getStartTime());
                        ReservationTime newReservation = new ReservationTime();
                        newReservation.setInitTime(requestSearchDto.getEndTime());
                        newReservation.setEndTiem(endTime);
                        System.out.println(newReservation);
                        res.add(newReservation);
                        requestsAtended.add(requestSearchDto.getRequestId());
                        map.put(requestSearchDto.getClassroomId(), res);
                        res.sort(Comparator.comparing(ReservationTime::getInitTime));
                        break;
                    }
                }
            }

        }
        return requestSearchDtoList;
    }

}
