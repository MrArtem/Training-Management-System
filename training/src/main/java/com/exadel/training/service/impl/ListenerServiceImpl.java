package com.exadel.training.service.impl;

import com.exadel.training.dao.ListenerDAO;
import com.exadel.training.dao.TrainingDAO;
import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.ListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    }

    @Override
    public void leaveListener(long trainingId, long userId) {
        Listener listener = listenerDAO.getListenerByTrainingUser(trainingId, userId);
        if (listener != null) {
            listener.setState(Listener.State.LEAVE);
            listenerDAO.changeListener(listener);
        }
    }

    @Override
    public List<User> getListenerListAccepted(long trainingId) {
        List<User> userList = new ArrayList<User>();
        for (Listener listener : listenerDAO.getListenerListAccepted(trainingId)) {
            userList.add(listener.getUser());
        }
        return userList;
    }
}
