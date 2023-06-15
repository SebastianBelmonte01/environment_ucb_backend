package bo.ucb.edu.environment.Dto;

public class EntranceDto {
    private Long reservationId;
    private int classroom;
    private String building;

    public EntranceDto() {
    }

    public EntranceDto(Long reservationId, int classroom, String building) {
        this.reservationId = reservationId;
        this.classroom = classroom;
        this.building = building;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
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
        return "EntranceDto{" +
                "reservationId=" + reservationId +
                ", classroom=" + classroom +
                ", building='" + building + '\'' +
                '}';
    }
}
