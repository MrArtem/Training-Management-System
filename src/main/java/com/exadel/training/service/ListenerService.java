package com.exadel.training.service;

import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.User;

import java.util.List;

public interface ListenerService {

    void addListener(long trainingId, long userId);

    void leaveListener(long trainingId, long userId);

    List<User> getListenerListAccepted(long trainingId);

    List<User> getListenerListByTrainingAndState(long trainingId, Listener.State state);

    boolean canSubscribe(long trainingId, long userId);
}
