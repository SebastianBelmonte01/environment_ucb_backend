package bo.ucb.edu.environment.Dto;

import java.util.Date;

public class ReservationDto {


    private Long reservationId;
    private String professorName;
    private String reservationDate;
    private String reservationTimeInit;
    private String reservationTimeEnd;
    private String environment;
    private String subject;
    private int parallel;
    private int people;
    private Long requestId;
    private String resState;
    private String reasonRej;

    private String reason;
    private boolean status;
    private int classroom;
    private String building;

    public ReservationDto() {
    }

    public ReservationDto(Long reservationId, String professorName, String reservationDate, String reservationTimeInit, String reservationTimeEnd, String environment, String subject, int parallel, int people, Long requestId, String resState, String reasonRej, String reason, boolean status, int classroom, String building) {
        this.reservationId = reservationId;
        this.professorName = professorName;
        this.reservationDate = reservationDate;
        this.reservationTimeInit = reservationTimeInit;
        this.reservationTimeEnd = reservationTimeEnd;
        this.environment = environment;
        this.subject = subject;
        this.parallel = parallel;
        this.people = people;
        this.requestId = requestId;
        this.resState = resState;
        this.reasonRej = reasonRej;
        this.reason = reason;
        this.status = status;
        this.classroom = classroom;
        this.building = building;


    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTimeInit() {
        return reservationTimeInit;
    }

    public void setReservationTimeInit(String reservationTimeInit) {
        this.reservationTimeInit = reservationTimeInit;
    }

    public String getReservationTimeEnd() {
        return reservationTimeEnd;
    }

    public void setReservationTimeEnd(String reservationTimeEnd) {
        this.reservationTimeEnd = reservationTimeEnd;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getParallel() {
        return parallel;
    }

    public void setParallel(int parallel) {
        this.parallel = parallel;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getResState() {
        return resState;
    }

    public void setResState(String resState) {
        this.resState = resState;
    }

    public String getReasonRej() {
        return reasonRej;
    }

    public void setReasonRej(String reasonRej) {
        this.reasonRej = reasonRej;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getClassroom() {
        return classroom;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

}