package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Dto.RequestDto;
import bo.ucb.edu.environment.Dto.RequestSearchDto;
import bo.ucb.edu.environment.Entity.Professor;
import bo.ucb.edu.environment.Entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Request findTopByProfessorAndStatusIsTrueOrderByRequestIdDesc (Professor professor);
    List<Request> findAllByProfessorAndStatusIsTrue(Professor professor);
    Request findRequestByRequestIdAndProfessor(Long id, Professor professor);

    @Query("SELECT new bo.ucb.edu.environment.Dto.RequestSearchDto(sr.requestId, sr.date, sr.startTime, sr.endTime, sc.classroomId, sr.reqState) " +
            "FROM Request sr, Environment se, Classroom sc " +
            "WHERE se.environmentId = sr.environment.environmentId " +
            "AND se.environmentId = sc.environment.environmentId " +
            "AND sr.date = :date " +
            "AND sr.environment.environmentId = :envId")
    List<RequestSearchDto> findAllRequests(@Param("envId") Integer envId, @Param("date") Date date);


    @Modifying
    @Query("UPDATE Request sr SET sr.reqState = :reqState WHERE sr.requestId = :reqId")
    void updateRequestByRequestId(@Param("reqId") Long reqId, @Param("reqState") String reqState);

    Request findRequestByRequestId(Long id);

    List<Request> findAllByProfessorAndReqState(Professor professor, String reqState);




}
