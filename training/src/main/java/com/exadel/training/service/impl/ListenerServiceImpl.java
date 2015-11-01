package com.exadel.training.service.impl;

import com.exadel.training.dao.*;
import com.exadel.training.dao.domain.*;
import com.exadel.training.service.ListenerService;
import com.exadel.training.service.NewsService;
import com.exadel.training.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ListenerServiceImpl implements ListenerService {
    @Autowired
    private ListenerDAO listenerDAO;
    @Autowired
    private TrainingDAO trainingDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AttendanceDAO attendanceDAO;
    @Autowired
    private LessonDAO lessonDAO;
    @Autowired
    private NewsService newsService;

    @Override
    public void addListener(long trainingId, long userId) {
        Listener listener = listenerDAO.getListenerByTrainingUser(trainingId, userId);
        if (listener != null) {
            listenerDAO.removeListener(listener);
        }
        listener = new Listener();
        List<Listener> listenerListAccepted = listenerDAO.getListenerListAccepted(trainingId);
        Training training = trainingDAO.getTrainingById(trainingId);
        User user = userDAO.getUserByID(userId);
        if (listenerListAccepted.size() < training.getMaxSize()) {
            listener.setState(Listener.State.ACCEPTED);
        } else {
            listener.setState(Listener.State.WAITING);
        }
        listener.setTraining(training);
        listener.setUser(user);
        listenerDAO.addListener(listener);
        List<Lesson> lessonList = lessonDAO.getLessonListActualFrom(trainingId, Utils.getTime());
        for(Lesson lesson : Utils.emptyIfNull(lessonList)) {
            Attendance attendance = new Attendance();
            attendance.setLesson(lesson);
            attendance.setUser(user);
            attendanceDAO.save(attendance);
        }
        newsService.addNews(user, News.TableName.TRAINING, News.ActionType.JOIN, trainingId);
    }

    @Override
    public void leaveListener(long trainingId, long userId) {
        Listener listener = listenerDAO.getListenerByTrainingUser(trainingId, userId);
        if (listener != null) {
            listener.setState(Listener.State.LEAVE);
            listenerDAO.changeListener(listener);
            listener = listenerDAO.getNextListenerInWaitList(trainingId);
            if(listener != null) {
                listener.setState(Listener.State.WAITING);
                listenerDAO.changeListener(listener);
            }
        }
        List<Attendance> attendanceList  = attendanceDAO.getAllAttendanceByUserIDFromDate(userId, new Date(Utils.getTime()));
        for(Attendance attendance : Utils.emptyIfNull(attendanceList)) {
            attendanceDAO.delete(attendance);
        }
        User user = userDAO.getUserByID(userId);
        newsService.addNews(user, News.TableName.TRAINING, News.ActionType.LEAVE, trainingId);
    }

    @Override
    public List<User> getListenerListAccepted(long trainingId) {
        List<User> userList = new ArrayList<User>();
        for (Listener listener : listenerDAO.getListenerListAccepted(trainingId)) {
            userList.add(listener.getUser());
        }
        return userList;
    }

    @Override
    public List<User> getListenerListByTrainingAndState(long trainingId, Listener.State state) {
       List<User> userList = new ArrayList<User>();
        for (Listener listener : listenerDAO.getListenerListByTrainingAndState(trainingId, state)) {
            userList.add(listener.getUser());
        }
        return userList;
    }

    @Override
    public boolean canSubscribe(long trainingId, long userId) {
        Listener listener = listenerDAO.getListenerByTrainingAndUser(trainingId, userId);
        if(listener == null) {
            return true;
        }
        if( listener.getState() == Listener.State.LEAVE ) {
            return true;
        }
        return false;
    }
}
