package com.exadel.training.dao;

import com.exadel.training.dao.domain.Listener;

import java.util.List;

public interface ListenerDAO {
    List<Listener> getListenerListRecord(long trainingId);
}
