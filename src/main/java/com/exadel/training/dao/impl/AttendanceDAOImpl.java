package com.exadel.training.dao.impl;

import com.exadel.training.dao.AttendanceDAO;
import com.exadel.training.dao.domain.Attendance;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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

    @Override
    public Attendance getAttendanceByUserIDAndLessonID(long idUser, long idLesson) {
        return (Attendance) sessionFactory.getCurrentSession()
                .createQuery("select a from Attendance as a where a.user.id = :idUser and a.lesson.id = :idLesson")
                .setParameter("idUser", idUser)
                .setParameter("idLesson", idLesson)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Attendance> getAllAttendanceByUserIDBetweenDates(long idUser, long idTraining, Date from, Date to) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Attendance.class, "attendance");

        criteria.createAlias("attendance.user", "user");
        criteria.add(Restrictions.eq("user.id", idUser));

        criteria.createAlias("attendance.lesson", "lesson");
        criteria.add(Restrictions.gt("lesson.date", from.getTime()));
        criteria.add(Restrictions.lt("lesson.date", to.getTime()));

        criteria.createAlias("lesson.training", "training");
        criteria.add(Restrictions.eq("training.id", idTraining));

        criteria.addOrder(Order.asc("lesson.date"));

        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Attendance> getAllAttendanceByUserIDFromDate(long idUser, Date from) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Attendance.class, "attendance");

        criteria.createAlias("attendance.user", "user");
        criteria.add(Restrictions.eq("user.id", idUser));

        criteria.createAlias("attendance.lesson", "lesson");
        criteria.add(Restrictions.gt("lesson.date", from.getTime()));

        return criteria.list();
    }

}
