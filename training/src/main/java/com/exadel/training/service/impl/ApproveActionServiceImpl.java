package com.exadel.training.service.impl;

import com.exadel.training.dao.ApproveActionDAO;
import com.exadel.training.dao.domain.ApproveAction;
import com.exadel.training.service.ApproveActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ApproveActionServiceImpl implements ApproveActionService {

    @Autowired
    private ApproveActionDAO approveActionDAO;

    @Override
    @Transactional
    public Integer getActionNumber() {
        return approveActionDAO.getApproveActionNumber();
    }

    @Override
    @Transactional
    public List<ApproveAction> getActionList(Integer page, Integer pageSize) {
        return approveActionDAO.getApproveActionList(page, pageSize);
    }
}
