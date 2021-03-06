package com.exadel.training.dao;

import com.exadel.training.dao.domain.ApproveAction;
import com.exadel.training.dao.domain.Training;

import java.util.List;

public interface ApproveActionDAO {

    ApproveAction getApproveAction(Long id);

    ApproveAction getApproveActionByTrainingId(Long trainingId);

    void addApproveAction(ApproveAction approveAction);

    void removeApproveAction(ApproveAction approveAction);

    Integer getApproveActionNumber();

    List<ApproveAction> getApproveActionList(Integer page, Integer pageSize);
}
