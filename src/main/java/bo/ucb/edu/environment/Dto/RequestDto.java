package bo.ucb.edu.environment.Dto;

import java.time.LocalTime;
import java.util.Date;

public class RequestDto {
    private Date date;
    private LocalTime initTime;
    private LocalTime endTime;
    private Long environment;
    private Long subject;
    private Integer parallel;
    private Integer people;
    private String reason;

    public RequestDto() {
    }

    public RequestDto(Date date, LocalTime initTime, LocalTime endTime, Long environment, Long subject, Integer parallel, Integer people, String reason) {
        this.date = date;
        this.initTime = initTime;
        this.endTime = endTime;
        this.environment = environment;
        this.subject = subject;
        this.parallel = parallel;
        this.people = people;
        this.reason = reason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getInitTime() {
        return initTime;
    }

    public void setInitTime(LocalTime initTime) {
        this.initTime = initTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Long getEnvironment() {
        return environment;
    }

    public void setEnvironment(Long environment) {
        this.environment = environment;
    }

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    public Integer getParallel() {
        return parallel;
    }

    public void setParallel(Integer parallel) {
        this.parallel = parallel;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "RequestDto{" +
                "date=" + date +
                ", initTime=" + initTime +
                ", endTime=" + endTime +
                ", environment=" + environment +
                ", subject=" + subject +
                ", parallel=" + parallel +
                ", people=" + people +
                ", reason='" + reason + '\'' +
                '}';
    }
}



