package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.*;
import bo.ucb.edu.environment.Dto.RequestDto;
import bo.ucb.edu.environment.Dto.RequestSearchDto;
import bo.ucb.edu.environment.Dto.ReservationDto;
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
    @Autowired
    private ReservationRepository reservationRepository;


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
        System.out.println(request);
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
        requestDto.setState(request.getReqState());
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
            requestDto.setState(request.getReqState());
            requestDtos.add(requestDto);
        }
        return requestDtos;
    }

    public List<RequestDto> getRequestsByState(int id, String state) {
        Professor professor = professorRepository.getProfessorByUserId(id);
        List<Request> requests = requestRepository.findAllByProfessorAndReqState(professor, state);
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
            requestDto.setState(request.getReqState());
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
        requestDto.setState(updatedRequest.getReqState());
        System.out.println(requestDto);
        return  requestDto;
    }

    public void asignEnvironment (RequestDto requestDto) {
        Environment environment = environmentRepository.findEnvironmentByType(requestDto.getEnvironment());
        Date date = requestDto.getDate();
        String message = "";
        List<Classroom> classrooms = classroomRepository.findAllByEnvironmentEnvironmentId(environment.getEnvironmentId());
        Map<Long, List<ReservationTime>> map = new HashMap<>();
        for(Classroom classroom : classrooms){
                ReservationTime reservationTime = new ReservationTime();
                List<ReservationTime> reservationTimeList = new ArrayList<>();
                reservationTime.setInitTime(LocalTime.of(7,0,0 ));
                reservationTime.setEndTiem(LocalTime.of(22, 0, 0));
                reservationTimeList.add(reservationTime);
                map.put(classroom.getClassroomId(), reservationTimeList);
        }
        List<RequestSearchDto> requestSearchDtoList = requestRepository.findAllRequests( environment.getEnvironmentId(), date);
        List<RequestSearchDto> requestsAtended = new ArrayList<>();
        Set<Long> keys = new HashSet<>();
        for(RequestSearchDto requestSearchDto : requestSearchDtoList) {
            List<ReservationTime> res = map.get(requestSearchDto.getClassroomId());
            for(int i = 0; i<= res.size()-1; i++) {
                if(requestsAtended.isEmpty() || !keys.contains(requestSearchDto.getRequestId())){
                    if(res.get(i).getInitTime().isBefore(requestSearchDto.getStartTime()) && res.get(i).getEndTiem().isAfter(requestSearchDto.getEndTime()) ) {
                        LocalTime endTime = res.get(i).getEndTiem();
                        res.get(i).setEndTiem(requestSearchDto.getStartTime());
                        ReservationTime newReservation = new ReservationTime();
                        newReservation.setInitTime(requestSearchDto.getEndTime());
                        newReservation.setEndTiem(endTime);
                        System.out.println(newReservation);
                        res.add(newReservation);
                        keys.add(requestSearchDto.getRequestId());
                        requestsAtended.add(requestSearchDto);
                        map.put(requestSearchDto.getClassroomId(), res);
                        res.sort(Comparator.comparing(ReservationTime::getInitTime));
                        //requestRepository.updateRequestByRequestId(requestSearchDto.getRequestId(), message);
                        if(requestSearchDto.getState().equals("Pendiente")){
                            Reservation reservation = new Reservation();
                            reservation.setClassroomId(requestSearchDto.getClassroomId());
                            reservation.setRequest(requestRepository.findById(requestSearchDto.getRequestId()).get());
                            reservation.setResState("En Espera");
                            reservation.setStatus(true);
                            reservation.setTxDate(new Date());
                            reservation.setTxHost("localhost");
                            reservation.setTxUser("sebastianbelmonte");
                            reservationRepository.save(reservation);
                        }
                        break;
                    }
                    else {
                        message = "Sin Espacio";
                        requestRepository.updateRequestByRequestId(requestSearchDto.getRequestId(), message);
                    }
                }

            }

        }

    }

    public RequestDto getRequest(RequestDto requestDto){
        Request request = requestRepository.findRequestByRequestId(requestDto.getId());
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
        requestDto.setState(request.getReqState());
        return requestDto;
    }

}
