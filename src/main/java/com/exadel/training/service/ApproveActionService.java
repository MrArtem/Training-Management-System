package com.exadel.training.service;

import com.exadel.training.dao.domain.ApproveAction;

import java.util.List;

public interface ApproveActionService {

    Long getActionNumber();

    List<ApproveAction> getActionList(Integer page, Integer pageSize);

    void addApproveAction(ApproveAction approveAction);
}
