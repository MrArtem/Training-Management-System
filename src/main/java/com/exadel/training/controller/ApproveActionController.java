package com.exadel.training.controller;

import com.exadel.training.controller.model.PageModel;
import com.exadel.training.controller.model.trainingModels.ApproveActionModel;
import com.exadel.training.dao.domain.ApproveAction;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.ApproveActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApproveActionController {

    private final String trainingTableName = "APPROVE_TRAINING";

    private final String lessonTableName = "APPROVE_LESSON";

    @Autowired
    private ApproveActionService approveActionService;

    @Secured("ADMIN")
    //@MessageMapping("/approve_list")
    //@SendTo("/pipe/approve_list")
    @RequestMapping("/approve_list")
    @ResponseBody
    public List<ApproveActionModel> getApproveActionList(@RequestBody PageModel pageModel) {
        List<ApproveActionModel> approveActionModelList = new ArrayList<ApproveActionModel>();
        for (ApproveAction approveAction :
                approveActionService.getActionList(pageModel.getPage(), pageModel.getPage_size())) {
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
            if (approveAction.getApproveLessonList().size() == 0) {
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
    public Long getApproveActionCount() {
        return approveActionService.getActionNumber();
    }
}
