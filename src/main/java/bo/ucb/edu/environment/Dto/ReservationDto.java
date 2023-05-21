package bo.ucb.edu.environment.Dto;

import java.util.Date;

public class ReservationDto {
    private Long reservationId;
    private Long classroomId;
    private Long requestId;
    private String resState;
    private String reasonRej;
    private boolean status;
    private int classroom;
    private String building;

public ReservationDto() {
    }

    public ReservationDto(Long reservationId, Long classroomId, Long requestId, String resState, String reasonRej, boolean status, int classroom, String building) {
        this.reservationId = reservationId;
        this.classroomId = classroomId;
        this.requestId = requestId;
        this.resState = resState;
        this.reasonRej = reasonRej;
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

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
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

    @Override
    public String toString() {
        return "ReservationDto{" +
                "reservationId=" + reservationId +
                ", classroomId=" + classroomId +
                ", requestId=" + requestId +
                ", resState='" + resState + '\'' +
                ", reasonRej='" + reasonRej + '\'' +
                ", status=" + status +
                ", classroom=" + classroom +
                ", building='" + building + '\'' +
                '}';
    }
}
