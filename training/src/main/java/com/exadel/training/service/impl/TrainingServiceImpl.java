package com.exadel.training.service.impl;

import com.exadel.training.controller.model.LessonModel;
import com.exadel.training.controller.model.RepeatModel;
import com.exadel.training.dao.*;
import com.exadel.training.dao.domain.*;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingDAO trainingDAO;
    @Autowired
    private ListenerDAO listenerDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ApproveActionDAO approveActionDAO;
    @Autowired
    private ApproveDAO<ApproveTraining> trainingApproveDAO;
    @Autowired
    private ApproveDAO<ApproveLesson> lessonApproveDAO;
    @Autowired
    private LessonDAO lessonDAO;

    private Long getTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        return calendar.getTimeInMillis();
    }

    @Override
    public Training getTraining(long id) {
        return trainingDAO.getTrainingById(id);
    }

    @Override
    public boolean canRate(long id) {
        //todo
        return false;
    }

    @Override
    public List<Listener> getListenerListRecord(long trainingId) {
        return listenerDAO.getListenerListRecord(trainingId);
    }

    private ApproveTraining getApproveTraining(String title
            , String description, String shortInfo, Integer language, Integer maxSize, String additionalInfo) {
        ApproveTraining approveTraining = new ApproveTraining();
        approveTraining.setTitle(title);
        approveTraining.setDescription(description);
        approveTraining.setExcerpt(shortInfo);
        approveTraining.setAdditionalInfo(additionalInfo);
        approveTraining.setMaxSize(maxSize);
        approveTraining.setLanguage(language);
        return approveTraining;
    }

    @Override
    public void addTrainingNotRepeat(Long coachId, String title, String description, String shortInfo
            , Integer language, Integer maxSize,boolean isInner
            , String place, String additionalInfo, List<LessonModel> lessonModelList) {
        User coach = userDAO.getUserByID(coachId);
        Training training = new Training(title
                ,description
                ,language
                ,maxSize
                ,isInner
                ,shortInfo
                ,0,0
                , Training.State.NONE,false
                , coach);
        trainingDAO.addTraining(training);
        ApproveTraining approveTraining =  getApproveTraining(title
                ,description
                ,shortInfo
                ,language
                ,maxSize
                ,additionalInfo);
        approveTraining.setTraining(training);
        trainingApproveDAO.addApprove(approveTraining);
        ApproveAction approveAction = new ApproveAction();
        approveAction.setDate(getTime());
        approveAction.setType(ApproveAction.Type.CREATE);
        approveAction.setActionId(approveTraining.getId());
        approveActionDAO.addApproveAction(approveAction);
        for(LessonModel lessonModel : lessonModelList) {
            Lesson lesson  = new Lesson();
            lessonDAO.addLesson(lesson);
            ApproveLesson approveLesson = new ApproveLesson();
            approveLesson.setLesson(lesson);
            approveLesson.setDate(lessonModel.getDate());
            lessonApproveDAO.addApprove(approveLesson);
        }
    }

    @Override
    public void addTrainingRepeat(Long coachId, String title, String description, String shortInfo, Integer language, Integer maxSize,boolean isInner , String place, String additionalInfo, RepeatModel repeatModel) {

    }
}
