package com.exadel.training.controller.model.attendanceModels;

import com.exadel.training.dao.domain.Attendance;

/**
 * Created by ayudovin on 27.10.2015.
 */
public class AttendanceModel {

    private long idAttendance;
    private long idLesson;
    private long idUser;
    private String nameUser;
    private boolean presence;
    private String comment;

    public AttendanceModel() {
    }

    public AttendanceModel(Attendance attendance) {
        this.idAttendance = attendance.getId();
        this.idLesson = attendance.getLesson().getId();
        this.idUser = attendance.getUser().getId();
        this.nameUser = attendance.getUser().getLastName() + " " + attendance.getUser().getFirstName();
        this.presence = attendance.isPresence();
        this.comment = attendance.getComment();
    }

    public long getIdAttendance() {
        return idAttendance;
    }

    public void setIdAttendance(long idAttendance) {
        this.idAttendance = idAttendance;
    }

    public long getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(long idLesson) {
        this.idLesson = idLesson;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
