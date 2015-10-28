package com.exadel.training.dao.impl;

import com.exadel.training.dao.AttendanceDAO;
import com.exadel.training.dao.domain.Attendance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ayudovin on 06.10.2015.
 */
@Repository
public class AttendanceDAOImpl implements AttendanceDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(Attendance attendance) {
        Session session = sessionFactory.getCurrentSession();
        session.save(attendance);
    }

    @Override
    public void delete(Attendance attendance) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(attendance);
    }

    @Override
    public void update(Attendance attendance) {
        Session session = sessionFactory.getCurrentSession();
        session.update(attendance);
    }

    @Override
    public Attendance getAttendanceByID(long id) {
        return sessionFactory.getCurrentSession()
                .load(Attendance.class, id);
    }

}
