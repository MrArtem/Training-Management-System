package com.exadel.training.service.impl;

import com.exadel.training.controller.model.attendanceModels.AttendanceModel;
import com.exadel.training.dao.AttendanceDAO;
import com.exadel.training.dao.LessonDAO;
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
        Attendance attendance = attendanceDAO.getAttendanceByID(attendanceModel.getIdAttendance());

        attendance.setComment(attendanceModel.getComment());
        attendance.setPresence(attendanceModel.isPresence());

        attendanceDAO.update(attendance);
    }

    @Override
    public Attendance getAttendanceByUserIDAndLessonID(long idUser, long idLesson) {
        return attendanceDAO.getAttendanceByUserIDAndLessonID(idUser, idLesson);
    }

    @Override
    public List<Attendance> getAllAttendanceByLessonID(long idLesson) {
        //todo check load and get
        Lesson lesson = lessonService.getLessonByID(idLesson);
        return lesson.getAttendanceList();
    }

    @Override
    public List<Attendance> getAllAttendanceByUserIDFromDate(long idUser, Date from) {
        return attendanceDAO.getAllAttendanceByUserIDFromDate(idUser, from);
    }

    @Override
    public List<Attendance> getAllAttendanceByUserIDBetweenDates(long idUser, long idTraining, Date from, Date to) {
        return attendanceDAO.getAllAttendanceByUserIDBetweenDates(idUser, idTraining, from, to);
    }
}
