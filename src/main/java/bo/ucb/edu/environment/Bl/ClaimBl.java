package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.ClaimRepository;
import bo.ucb.edu.environment.Dao.ClassroomRepository;
import bo.ucb.edu.environment.Dao.ReservationRepository;
import bo.ucb.edu.environment.Dto.ClaimDto;
import bo.ucb.edu.environment.Dto.ReservationDto;
import bo.ucb.edu.environment.Entity.Claim;
import bo.ucb.edu.environment.Entity.Classroom;
import bo.ucb.edu.environment.Entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;


import java.util.Base64;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimBl {
    @Autowired
    ClaimRepository claimDao;
    @Autowired
    ReservationRepository reservationDao;
    @Autowired
    ClassroomRepository classroomRepository;

    public void saveNewClaim(String description, byte[] imageData, Long reservationId) throws IOException {
        reservationDao.updateReservationState(reservationId, "Reclamado");
        Claim claim = new Claim();
        claim.setClaimState("Pendiente");
        //image data to BASE64
        if(imageData != null){
            String imageDataString = Base64.getEncoder().encodeToString(imageData);
            claim.setImageData(imageDataString);
        }
        claim.setReservation(reservationDao.findReservationByReservationId(reservationId));
        claim.setDescription(description);
        claim.setDate(new Date());
        claim.setStatus(true);
        claim.setTransactionUser("sebastianbelmonte");
        claim.setTransactionDate(new Date());
        claim.setTransactionHost("localhost");
        claimDao.save(claim);
    }

    public List<ClaimDto> getAllPendingClaims(){
        List<Claim> claims = claimDao.findClaimsByState("Pendiente");
        List<ClaimDto> claimDtos = new ArrayList<>();
        for (Claim claim: claims) {
            ReservationDto reservationDto = new ReservationDto();
            ClaimDto claimDto = new ClaimDto();
            reservationDto.setReservationId(claim.getReservation().getReservationId());
            reservationDto.setProfessorName(claim.getReservation().getRequest().getProfessor().getName());
            reservationDto.setSubject(claim.getReservation().getRequest().getSubjectProfessor().getSubject().getName());
            reservationDto.setParallel(claim.getReservation().getRequest().getSubjectProfessor().getParallel());
            reservationDto.setPeople(claim.getReservation().getRequest().getPeople());
            reservationDto.setReservationDate(claim.getReservation().getRequest().getDate().toString());
            reservationDto.setReservationTimeInit(claim.getReservation().getRequest().getStartTime().toString());
            reservationDto.setReservationTimeEnd(claim.getReservation().getRequest().getEndTime().toString());
            reservationDto.setEnvironment(claim.getReservation().getRequest().getEnvironment().getType());
            reservationDto.setBuilding(classroomRepository.findClassroomByClassroomId(claim.getReservation().getClassroomId()).getBuilding());
            reservationDto.setClassroom(classroomRepository.findClassroomByClassroomId(claim.getReservation().getClassroomId()).getCode());
            claimDto.setClaimId(claim.getClaimId());
            claimDto.setReservationDto(reservationDto);
            claimDto.setDate(claim.getDate());
            claimDto.setDesClaim(claim.getDescription());
            claimDto.setClaimState(claim.getClaimState());
            claimDto.setImage(claim.getImageData());
            claimDtos.add(claimDto);
        }
        return claimDtos;
    }

    public List<ClaimDto> getAllAtendedClaims(){
        List<Claim> claims = claimDao.findClaimsByState("Atendido");
        List<ClaimDto> claimDtos = new ArrayList<>();
        for (Claim claim: claims) {
            ReservationDto reservationDto = new ReservationDto();
            ClaimDto claimDto = new ClaimDto();
            reservationDto.setReservationId(claim.getReservation().getReservationId());
            reservationDto.setProfessorName(claim.getReservation().getRequest().getProfessor().getName());
            reservationDto.setSubject(claim.getReservation().getRequest().getSubjectProfessor().getSubject().getName());
            reservationDto.setParallel(claim.getReservation().getRequest().getSubjectProfessor().getParallel());
            reservationDto.setPeople(claim.getReservation().getRequest().getPeople());
            reservationDto.setReservationDate(claim.getReservation().getRequest().getDate().toString());
            reservationDto.setReservationTimeInit(claim.getReservation().getRequest().getStartTime().toString());
            reservationDto.setReservationTimeEnd(claim.getReservation().getRequest().getEndTime().toString());
            reservationDto.setEnvironment(claim.getReservation().getRequest().getEnvironment().getType());
            reservationDto.setBuilding(classroomRepository.findClassroomByClassroomId(claim.getReservation().getClassroomId()).getBuilding());
            reservationDto.setClassroom(classroomRepository.findClassroomByClassroomId(claim.getReservation().getClassroomId()).getCode());
            claimDto.setClaimId(claim.getClaimId());
            claimDto.setReservationDto(reservationDto);
            claimDto.setDate(claim.getDate());
            claimDto.setDesClaim(claim.getDescription());
            claimDto.setClaimState(claim.getClaimState());
            claimDto.setImage(claim.getImageData());
            claimDtos.add(claimDto);
        }
        return claimDtos;
    }

    public List<ClaimDto> getUserClaimsByState(Long userId, String status){
        List<Claim> claims = claimDao.findAllByUserAndStatus(status, userId);
        List<ClaimDto> claimDtos = new ArrayList<>();
        for (Claim claim: claims) {
            ReservationDto reservationDto = new ReservationDto();
            ClaimDto claimDto = new ClaimDto();
            reservationDto.setReservationId(claim.getReservation().getReservationId());
            reservationDto.setProfessorName(claim.getReservation().getRequest().getProfessor().getName());
            reservationDto.setSubject(claim.getReservation().getRequest().getSubjectProfessor().getSubject().getName());
            reservationDto.setParallel(claim.getReservation().getRequest().getSubjectProfessor().getParallel());
            reservationDto.setPeople(claim.getReservation().getRequest().getPeople());
            reservationDto.setReservationDate(claim.getReservation().getRequest().getDate().toString());
            reservationDto.setReservationTimeInit(claim.getReservation().getRequest().getStartTime().toString());
            reservationDto.setReservationTimeEnd(claim.getReservation().getRequest().getEndTime().toString());
            reservationDto.setEnvironment(claim.getReservation().getRequest().getEnvironment().getType());
            reservationDto.setBuilding(classroomRepository.findClassroomByClassroomId(claim.getReservation().getClassroomId()).getBuilding());
            reservationDto.setClassroom(classroomRepository.findClassroomByClassroomId(claim.getReservation().getClassroomId()).getCode());
            claimDto.setClaimId(claim.getClaimId());
            claimDto.setReservationDto(reservationDto);
            claimDto.setDate(claim.getDate());
            claimDto.setDesClaim(claim.getDescription());
            claimDto.setClaimState(claim.getClaimState());
            claimDto.setImage(claim.getImageData());
            claimDtos.add(claimDto);
        }
        return claimDtos;
    }

    public void sendClaimResponse (Long claimId, String claimResponse) {
        //claimDao.updateClaimState(claimId, claimResponse, "Atendido");
        Claim claim = claimDao.findClaimByClaimId(claimId);
        claim.setClaimState("Atendido");
        claim.setResClaim(claimResponse);
        claimDao.save(claim);
    }

    public void updateClaimState(Long claimId, String claimState) {
        claimDao.updateClaimState(claimId, claimState);
    }












}
