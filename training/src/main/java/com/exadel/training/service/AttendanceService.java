package com.exadel.training.service;

import com.exadel.training.controller.model.attendanceModels.AttendanceModel;
import com.exadel.training.dao.domain.Attendance;

import java.util.Date;
import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
public interface AttendanceService {
    void updateAttendance(AttendanceModel attendanceModel);

    Attendance getAttendanceByUserIDAndLessonID(long idUser, long idLesson);

    List<Attendance> getAllAttendanceByLessonID(long idLesson);
    List<Attendance> getAllAttendanceByUserIDFromDate(long idUser, Date from);
    List<Attendance> getAllAttendanceByUserIDBetweenDates(long idUser, long idTraining, Date from, Date to);
}
