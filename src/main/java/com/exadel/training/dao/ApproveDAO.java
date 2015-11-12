package com.exadel.training.dao;

public interface ApproveDAO<T> {

    T getApprove(Class<T> c, Long id);

    void addApprove(T t);

    void removeApprove(T t);

    void editApprove(T t);
}
