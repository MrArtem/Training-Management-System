package com.exadel.training.controller;

import com.exadel.training.controller.model.trainingModels.ApproveActionModel;
import com.exadel.training.dao.domain.ApproveAction;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.ApproveActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApproveActionController {

    private final String trainingTableName = "APPROVE_TRAINING";

    private final String lessonTableName = "APPROVE_LESSON";

    @Autowired
    private ApproveActionService approveActionService;

    @Secured("ADMIN")
//    @MessageMapping("/approve_list")
//    @SendTo("/pipe/approve_list")
    @RequestMapping("/approve_list")
    @ResponseBody
    public List<ApproveActionModel> getApproveActionList(@RequestParam("page") Integer page,
                                                         @RequestParam("page_size")
                                                         Integer pageSize) {
        List<ApproveActionModel> approveActionModelList = new ArrayList<ApproveActionModel>();
        for (ApproveAction approveAction : approveActionService.getActionList(page, pageSize)) {
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
            approveActionModelList.add(approveActionModel);
        }
        return approveActionModelList;
    }

    @Secured("ADMIN")
//    @MessageMapping("/approve_count")
//    @SendTo("/pipe/approve_count")
    @RequestMapping("/approve_count")
    @ResponseBody
    public Integer getApproveActionCount() {
        return approveActionService.getActionNumber();
    }
}
