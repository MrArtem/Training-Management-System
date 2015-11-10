package com.exadel.training.dao;

import com.exadel.training.dao.domain.ApproveTraining;

public interface ApproveDAO<T> {

    T getApprove(Class<T> c, Long id);

    void addApprove(T t);

    void removeApprove(T t);

    void editApprove(T t);
}
