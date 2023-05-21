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
        request.setReqState("En Espera");
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
        return  requestDto;
    }

    public List<RequestSearchDto> asignEnvironment (Long id) {
        String message = "";
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
        Date date = Date.from(LocalDate.of(2023, 5, 21).atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<RequestSearchDto> requestSearchDtoList = requestRepository.findAllRequests( 10, date);
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
        return requestsAtended;
    }

    public List<RequestDto> getAllRequestsAdmin() {
        List<Reservation> reservations = reservationRepository.finaAllReservationsEnEspera();
        List<RequestDto> requestDtos = new ArrayList<>();
        for(Reservation reservation : reservations){
            Request request = requestRepository.findRequestByRequestId(reservation.getRequest().getRequestId());
            RequestDto requestDto = new RequestDto();
            requestDto.setId(reservation.getReservationId());
            requestDto.setProfessorName(professorRepository.findProfessorByProfessorId(request.getProfessor().getProfessorId()).getName());
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


    public RequestDto acceptRequest(Long reservationId){
        String message = "Aceptado";
        reservationRepository.updateReservationState(reservationId, message);
        Reservation reservation = reservationRepository.findById(reservationId).get();
        Request request = requestRepository.findRequestByRequestId(reservation.getRequest().getRequestId());
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






}
