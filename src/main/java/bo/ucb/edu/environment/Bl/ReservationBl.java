package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.*;
import bo.ucb.edu.environment.Dto.RequestDto;
import bo.ucb.edu.environment.Dto.ReservationDto;
import bo.ucb.edu.environment.Entity.Professor;
import bo.ucb.edu.environment.Entity.Request;
import bo.ucb.edu.environment.Entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationBl {
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

    public List<RequestDto> getAllRequestsAdmin() {
        List<Reservation> reservations = reservationRepository.findAllReservationsState("Espera");
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
            requestDto.setState(reservation.getResState());
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
/*
    public List<ReservationDto> getReservatonByState(int id, String state){
        Professor professor = professorRepository.getProfessorByUserId(id);
        List<Reservation> reservations = reservationRepository.findAllReservationsByProfesorAndResState(professor, state);
        List<ReservationDto> reservationsDtos = new ArrayList<>();
        for(Reservation reservation : reservations){
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setReservationId(reservation.getReservationId());
            reservationDto.setClassroomId(reservation.getClassroomId());
            reservationDto.setRequestId(reservation.getRequest().getRequestId());
            reservationDto.setResState(reservation.getResState());
            reservationDto.setReasonRej(reservation.getReasonRej());
            reservationDto.setStatus(reservation.getStatus());
            reservationDto.setBuilding(classroomRepository.findById(reservation.getClassroomId()).get().getBuilding());
            reservationDto.setClassroom(classroomRepository.findById(reservation.getClassroomId()).get().getCode());
            reservationsDtos.add(reservationDto);
        }
        return reservationsDtos;
    }


 */
    public RequestDto getReservationById(Long reservationId){
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
