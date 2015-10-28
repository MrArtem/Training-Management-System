package com.exadel.training.service;

import com.exadel.training.controller.model.attendanceModels.AttendanceModel;
import com.exadel.training.dao.domain.Attendance;

import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
public interface AttendanceService {
    void updateAttendance(AttendanceModel attendanceModel);

    List<Attendance> getAllAttendanceByLessonID(long idLesson);
}
