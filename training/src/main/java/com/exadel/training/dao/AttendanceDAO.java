package com.exadel.training.dao;

import com.exadel.training.dao.domain.Attendance;

import java.util.Date;
import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
public interface AttendanceDAO {
    void save(Attendance attendance);
    void delete(Attendance attendance);
    void update(Attendance attendance);

    Attendance getAttendanceByID(long id);
    Attendance getAttendanceByUserIDAndLessonID(long idUser, long idLesson);
    List<Attendance> getAllAttendanceByUserIDFromDate(long idUser, Date from);
    List<Attendance> getAllAttendanceByUserIDBetweenDates(long idUser, long idTraining, Date from, Date to);
}
