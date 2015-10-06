package com.exadel.training.dao;

import com.exadel.training.dao.domain.ApproveAction;

import java.util.List;

public interface ApproveActionDAO {

    void addApproveAction(ApproveAction approveAction);

    void removeApproveAction(ApproveAction approveAction);

    List<ApproveAction> getApproveActionList();
}
