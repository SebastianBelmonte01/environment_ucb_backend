package bo.ucb.edu.environment.Dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class  RequestSearchDto {

    private Long requestId;
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long classroomId;

    private String state;

    public RequestSearchDto() {
    }

    public RequestSearchDto(Long requestId, Date date, LocalTime startTime, LocalTime endTime, Long classroomId, String state) {
        this.requestId = requestId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroomId = classroomId;
        this.state = state;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "RequestSearchDto{" +
                "requestId=" + requestId +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", classroomId=" + classroomId +
                ", state='" + state + '\'' +
                '}';
    }
}