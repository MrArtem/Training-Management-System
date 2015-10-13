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
    private final int DAY_OF_WEEK = 7;
    private final long MILLIS_IN_DAY = 86400000L;
    private final long MILLIS_IN_WEEK = MILLIS_IN_DAY * DAY_OF_WEEK;

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
    @Autowired
    private TagDAO tagDAO;

    private Long getTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return calendar.getTimeInMillis();
    }

    private int getDayOfWeek(Long millis) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(millis);
        return (calendar.get(Calendar.DAY_OF_WEEK) + 5) % DAY_OF_WEEK;
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

    private List<Tag> getTagList(List<Long> tagIdList) {
        List<Tag> tagList = new ArrayList<Tag>();
        if(tagIdList == null) {
            return null;
        }
        for (Long i : tagIdList) {
            tagList.add(tagDAO.getTag(i));
        }
        return tagList;
    }

    private void addLessonListNotRepeating(Training training, List<LessonModel> lessonModelList) {
        for (LessonModel lessonModel : lessonModelList) {
            Lesson lesson = new Lesson();
            lesson.setTraining(training);
            lesson.setDate(lessonModel.getDate());
            lessonDAO.addLesson(lesson);
            ApproveLesson approveLesson = new ApproveLesson();
            approveLesson.setLesson(lesson);
            approveLesson.setDate(lessonModel.getDate());
            lessonApproveDAO.addApprove(approveLesson);
        }
    }

    private void addLessonListRepeating(Training training, RepeatModel repeatModel) {
        LessonModel[] lessonModelList = repeatModel.getLessonList();
        int dayOfWeekStart = getDayOfWeek(repeatModel.getStartDate());
        for (int i = 0; i < 7; i++) {
            if (lessonModelList[i] == null) {
                continue;
            }
            int offset = (i - dayOfWeekStart + DAY_OF_WEEK) % DAY_OF_WEEK;
            long dateLesson = lessonModelList[i].getDate() + repeatModel.getStartDate() + offset * MILLIS_IN_DAY;
            for (; dateLesson < repeatModel.getEndDate(); dateLesson += MILLIS_IN_WEEK) {
                Lesson lesson = new Lesson();
                lesson.setTraining(training);
                lesson.setDate(dateLesson);
                lessonDAO.addLesson(lesson);
                ApproveLesson approveLesson = new ApproveLesson();
                approveLesson.setLesson(lesson);
                approveLesson.setDate(dateLesson);
                lessonApproveDAO.addApprove(approveLesson);
            }
        }
    }

    @Override
    public void createTraining(Long coachId, String title, String description, String shortInfo
            , Integer language, Integer maxSize, boolean isInner
            , List<Long> tagIdList, String additionalInfo, boolean isRepeating
            , List<LessonModel> lessonModelList, RepeatModel repeatModel) {

        User coach = userDAO.getUserByID(coachId);
        Training training = new Training(title
                , description
                , language
                , maxSize
                , isInner
                , shortInfo
                , isRepeating
                , coach);
        List<Tag> tagList = getTagList(tagIdList);
        training.setTagList(tagList);
        trainingDAO.addTraining(training);

        ApproveTraining approveTraining = getApproveTraining(title
                , description
                , shortInfo
                , language
                , maxSize
                , additionalInfo);
        approveTraining.setTraining(training);
        approveTraining.setTagList(tagList);
        trainingApproveDAO.addApprove(approveTraining);
        ApproveAction approveAction = new ApproveAction();
        approveAction.setDate(getTime());
        approveAction.setType(ApproveAction.Type.CREATE);
        approveAction.setActionId(approveTraining.getId());
        approveActionDAO.addApproveAction(approveAction);
        if (isRepeating) {
            addLessonListRepeating(training, repeatModel);
        } else {
            addLessonListNotRepeating(training, lessonModelList);
        }
    }

    private void removeApproveCreate(Training training, boolean removeLesson) {
        ApproveTraining approveTraining = training.getApproveTraining();
        //todo ApproveAction approveAction ;
        trainingApproveDAO.removeApprove(approveTraining);
        for(Lesson lesson : training.getLessonList()) {
            lessonApproveDAO.removeApprove(lesson.getApproveLesson());
            if(removeLesson) {
                lessonDAO.removeLesson(lesson);
            }
        }
        training.setState(Training.State.REMOVE);
        trainingDAO.changeTraining(training);
    }

    @Override
    public void confirmTraining(Long trainingId, String title, String description, String shortInfo
            , Integer language, Integer maxSize, boolean isInner, List<Long> tagIdList, String additionalInfo
            , List<LessonModel> lessonModelList, RepeatModel repeatModel) {
        Training training = trainingDAO.getTrainingById(trainingId);
        removeApproveCreate(training,true);

    }

    @Override
    public void cancelCreate(Long trainingId) {
        Training training = trainingDAO.getTrainingById(trainingId);
        removeApproveCreate(training,false);
    }

    @Override
    public void cancelChange(Long trainingId) {
        //todo
    }
}
