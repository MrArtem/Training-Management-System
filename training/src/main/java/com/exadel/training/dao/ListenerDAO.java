package com.exadel.training.dao;

import com.exadel.training.dao.domain.Listener;

import java.util.List;

public interface ListenerDAO {
    List<Listener> getListenerListRecord(long trainingId);

    List<Listener> getListenerListAccepted(long trainingId);

    void addListener(Listener listener);

    void changeListener(Listener listener);

    Listener getListenerByTrainingUser(long trainingId, long userId);

    void removeListener(Listener listener);

    Listener getNextListenerInWaitList(long trainingId);
}
