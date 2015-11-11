package com.exadel.training.service.impl;

import com.exadel.training.controller.model.trainingModels.ApproveActionModel;
import com.exadel.training.dao.ApproveActionDAO;
import com.exadel.training.dao.domain.ApproveAction;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.ApproveActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ApproveActionServiceImpl implements ApproveActionService {

    private final String trainingTableName = "APPROVE_TRAINING";

    private final String lessonTableName = "APPROVE_LESSON";

    @Autowired
    private ApproveActionDAO approveActionDAO;

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    @Transactional
    public Long getActionNumber() {
        return approveActionDAO.getApproveActionNumber();
    }

    @Override
    @Transactional
    public List<ApproveAction> getActionList(Integer page, Integer pageSize) {
        return approveActionDAO.getApproveActionList(page, pageSize);
    }

    @Override
    public void addApproveAction(ApproveAction approveAction) {
        approveActionDAO.addApproveAction(approveAction);
        ApproveActionModel approveActionModel = new ApproveActionModel();
        approveActionModel.setDate(approveAction.getDate());
        approveActionModel.setType(approveAction.getType());
        Training training = approveAction.getTraining();
        approveActionModel.setTrainingId(training.getId());
        approveActionModel.setTrainingTitle(training.getTitle());
        User coach = training.getCoach();
        approveActionModel.setCoachId(coach.getId());
        approveActionModel.setCoachName(coach.getFirstName() + " " + coach.getLastName());
        approveActionModel.setId(approveAction.getId());
        if (approveAction.getApproveTraining() == null) {
            approveActionModel.setTableName(trainingTableName);
        } else {
            approveActionModel.setTableName(lessonTableName);
        }
        template.convertAndSend("/pipe/action_list", approveActionModel);
        template.convertAndSend("/pipe/action_count", getActionNumber());
    }
}
