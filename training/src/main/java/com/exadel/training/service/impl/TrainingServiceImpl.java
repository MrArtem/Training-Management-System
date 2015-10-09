package com.exadel.training.service.impl;

import com.exadel.training.controller.model.LessonModel;
import com.exadel.training.controller.model.RepeatModel;
import com.exadel.training.dao.ListenerDAO;
import com.exadel.training.dao.TrainingDAO;
import com.exadel.training.dao.domain.ApproveTraining;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingDAO trainingDAO;
    @Autowired
    private ListenerDAO listenerDAO;

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

    private ApproveTraining getApproveTraining(User coach
            , String title, String description, String shortInfo, Integer language, Integer maxSize, String additionalInfo) {
        ApproveTraining approveTraining = new ApproveTraining();
        approveTraining.setTitle(title);
        approveTraining.setDescription(description);
        approveTraining.setExcerpt(shortInfo);
        //todo additionalInfo
        approveTraining.setMaxSize(maxSize);
        approveTraining.setLanguage(language);
        return approveTraining;
    }

    @Override
    public void addTrainingNotRepeat(Long coachId, String title, String description, String shortInfo, Integer language, Integer maxSize, String place, String additionalInfo, List<LessonModel> lessonModelList) {

    }

    @Override
    public void addTrainingRepeat(Long coachId, String title, String description, String shortInfo, Integer language, Integer maxSize, String place, String additionalInfo, RepeatModel repeatModel) {

    }
}
