package com.exadel.training.service.impl;

import com.exadel.training.controller.model.attendanceModels.AttendanceModel;
import com.exadel.training.dao.AttendanceDAO;
import com.exadel.training.dao.domain.Attendance;
import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.service.AttendanceService;
import com.exadel.training.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService{

    @Autowired
    LessonService lessonService;
    @Autowired
    AttendanceDAO attendanceDAO;

    @Override
    public void updateAttendance(AttendanceModel attendanceModel) {
        //todo ask about default attendance
        Attendance attendance = attendanceDAO.getAttendanceByID(attendanceModel.getIdAttendance());

        attendance.setComment(attendanceModel.getComment());
        attendance.setPresence(attendanceModel.isPresence());

        attendanceDAO.update(attendance);
    }

    @Override
    public List<Attendance> getAllAttendanceByLessonID(long idLesson) {
        return lessonService.getLessonByID(idLesson).getAttendanceList();
    }

    @Override
    public List<Attendance> getAllAttendanceByUserIDBetweenDates(long idUser, Date from, Date to) {
        return attendanceDAO.getAllAttendanceByUserIDBetweenDates(idUser, from, to);
    }
}
