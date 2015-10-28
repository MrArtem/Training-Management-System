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
    List<Attendance> getAllAttendanceByUserIDBetweenDates(long idUser, Date from, Date to);
}
