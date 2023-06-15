package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.*;
import bo.ucb.edu.environment.Dto.EntranceDto;
import bo.ucb.edu.environment.Dto.RequestDto;
import bo.ucb.edu.environment.Dto.ReservationDto;
import bo.ucb.edu.environment.Entity.Professor;
import bo.ucb.edu.environment.Entity.Request;
import bo.ucb.edu.environment.Entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        List<Reservation> reservations = reservationRepository.findAllReservationsState("En Espera");
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
    public List<ReservationDto> getReservatonByState(int id, String state){
        Professor professor = professorRepository.getProfessorByUserId(id);
        List<Reservation> reservations = reservationRepository.findAllReservationsByProfesorAndResState(professor.getProfessorId(), state);
        List<ReservationDto> reservationsDtos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        for(Reservation reservation : reservations){
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setReservationId(reservation.getReservationId());
            reservationDto.setProfessorName(professor.getName());
            Date date = reservation.getRequest().getDate();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            reservationDto.setReservationDate(localDate.format(formatter));
            reservationDto.setReservationTimeInit(reservation.getRequest().getStartTime().format(timeFormatter));
            reservationDto.setReservationTimeEnd(reservation.getRequest().getEndTime().format(timeFormatter));
            reservationDto.setSubject(reservation.getRequest().getSubjectProfessor().getSubject().getName());
            reservationDto.setParallel(reservation.getRequest().getSubjectProfessor().getParallel());
            reservationDto.setEnvironment(reservation.getRequest().getEnvironment().getType());
            reservationDto.setPeople(reservation.getRequest().getPeople());
            reservationDto.setReason(reservation.getRequest().getReason());
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

    public RequestDto rejectRequest(Long reservationId, String reason){
        String message = "Rechazado";
        reservationRepository.updateReservationState(reservationId, message);
        reservationRepository.updateReservationRejectionMessage(reservationId, reason);
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

    public RequestDto cancelReservation(Long reservationId ){
        String message = "Cancelado";
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



    public EntranceDto registerEntrance(EntranceDto entranceUserDto){
        EntranceDto entranceDto = new EntranceDto();
        Reservation reservation = reservationRepository.findById(entranceUserDto.getReservationId()).get();
        entranceDto.setReservationId(reservation.getReservationId());
        entranceDto.setBuilding(classroomRepository.findById(reservation.getClassroomId()).get().getBuilding());
        entranceDto.setClassroom(classroomRepository.findById(reservation.getClassroomId()).get().getCode());

        if(entranceUserDto.getClassroom() != entranceDto.getClassroom() || !entranceUserDto.getBuilding().equals(entranceDto.getBuilding())) {
            return null;
        }
        reservationRepository.updateReservationState(entranceUserDto.getReservationId(), "Completado");
        return entranceUserDto;
    }

}


