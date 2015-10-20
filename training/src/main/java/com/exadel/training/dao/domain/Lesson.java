package com.exadel.training.dao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Lesson {
    @Id
    @GeneratedValue
    private long id;

    private long date;

    private String place;

    @JsonIgnore
    @OneToOne
    private ApproveLesson approveLesson;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Training training;

    @JsonIgnore
    @OneToMany
    private List<Attendance> attendanceList;

    public Lesson() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public ApproveLesson getApproveLesson() {
        return approveLesson;
    }

    public void setApproveLesson(ApproveLesson approveLesson) {
        this.approveLesson = approveLesson;
    }
}
