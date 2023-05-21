package bo.ucb.edu.environment.Utils;

import java.time.LocalTime;

public class ReservationTime {
    private LocalTime initTime;
    private LocalTime endTiem;

    public ReservationTime() {
    }

    public ReservationTime(LocalTime initTime, LocalTime endTiem) {
        this.initTime = initTime;
        this.endTiem = endTiem;
    }

    public LocalTime getInitTime() {
        return initTime;
    }

    public void setInitTime(LocalTime initTime) {
        this.initTime = initTime;
    }

    public LocalTime getEndTiem() {
        return endTiem;
    }

    public void setEndTiem(LocalTime endTiem) {
        this.endTiem = endTiem;
    }


    @Override
    public String toString() {
        return "ReservationTime{" +
                "initTime=" + initTime +
                ", endTiem=" + endTiem +
                '}';
    }
}
