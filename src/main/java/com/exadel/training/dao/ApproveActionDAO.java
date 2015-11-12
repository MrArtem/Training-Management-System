package com.exadel.training.dao;

import com.exadel.training.dao.domain.ApproveAction;

import java.util.List;

public interface ApproveActionDAO {

    ApproveAction getApproveAction(Long id);

    ApproveAction getApproveActionByTrainingId(Long trainingId);

    void addApproveAction(ApproveAction approveAction);

    void removeApproveAction(ApproveAction approveAction);

    Long getApproveActionNumber();

    List<ApproveAction> getApproveActionList(Integer page, Integer pageSize);
}
