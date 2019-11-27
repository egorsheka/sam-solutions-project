package by.sam.mvc.models;


import by.sam.mvc.models.user.Cook;

import javax.persistence.*;

@Entity
@Table(name = "work_times")
public class WorkTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Enumerated(EnumType.STRING)
    @Column(name = "week_day")
    private WeekDay day;


    @Column(name = "start_time")
    private String startTime;


    @Column(name = "end_time")
    private String endTime;


    public WorkTime() {
    }

    public WorkTime(WeekDay day) {
        this.day = day;

    }

    public WorkTime(WeekDay day, String startTime, String endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WeekDay getDay() {
        return day;
    }

    public void setDay(WeekDay day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
