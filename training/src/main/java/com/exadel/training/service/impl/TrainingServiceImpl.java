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

    private List<ApproveLesson> addLessonListNotRepeating(Training training
            , List<LessonModel> lessonModelList, boolean isConfirmed) {
        List<ApproveLesson> approveLessonList = new ArrayList<ApproveLesson>();
        for (LessonModel lessonModel : lessonModelList) {
            Lesson lesson = new Lesson();
            lesson.setTraining(training);
            lesson.setDate(lessonModel.getDate());
            lesson.setPlace(lessonModel.getPlace());
            lessonDAO.addLesson(lesson);
            if(!isConfirmed) {
                ApproveLesson approveLesson = new ApproveLesson();
                approveLesson.setLesson(lesson);
                approveLesson.setDate(lessonModel.getDate());
                lessonApproveDAO.addApprove(approveLesson);
                approveLessonList.add(approveLesson);
            }
        }
        return approveLessonList;
    }

    private List<ApproveLesson> addLessonListRepeating(Training training, RepeatModel repeatModel, boolean isConfirmed) {
        List<ApproveLesson> approveLessonList = new ArrayList<ApproveLesson>();
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
                if(!isConfirmed) {
                    ApproveLesson approveLesson = new ApproveLesson();
                    approveLesson.setLesson(lesson);
                    approveLesson.setDate(dateLesson);
                    lessonApproveDAO.addApprove(approveLesson);
                    approveLessonList.add(approveLesson);
                }
            }
        }
        return approveLessonList;
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
        approveTraining.setTagList(tagList);
        trainingApproveDAO.addApprove(approveTraining);
        ApproveAction approveAction = new ApproveAction();
        approveAction.setDate(getTime());
        approveAction.setType(ApproveAction.Type.CREATE);
        approveAction.setTraining(training);
        List<ApproveLesson> approveLessonList  = null;
        if (isRepeating) {
            approveLessonList = addLessonListRepeating(training, repeatModel,false);
        } else {
            approveLessonList = addLessonListNotRepeating(training, lessonModelList,false);
        }
        approveAction.setApproveLessonList(approveLessonList);
        approveActionDAO.addApproveAction(approveAction);
    }

    private void removeApproveCreate(ApproveAction approveAction, boolean removeLesson) {
        Training training = approveAction.getTraining();
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
    public void confirmTraining(Long actionId, String title, String description, String shortInfo
            , Integer language, Integer maxSize, boolean isInner, List<Long> tagIdList
            , List<LessonModel> lessonModelList, RepeatModel repeatModel) {
        //todo
        ApproveAction approveAction = null ;
        removeApproveCreate(approveAction,true);
        Training training = approveAction.getTraining();
        training.setTitle(title);
        training.setDescription(description);
        training.setExcerpt(shortInfo);
        training.setLanguage(language);
        training.setMaxSize(maxSize);
        training.setIsInner(isInner);
        training.setTagList(getTagList(tagIdList));
        if(training.isRepeat()) {
            addLessonListRepeating(training,repeatModel,true);
        } else {
            addLessonListNotRepeating(training, lessonModelList, true);
        }
    }

    @Override
    public void cancelCreate(Long actionId) {
        //TODO
        ApproveAction approveAction = null;
        removeApproveCreate(approveAction,false);
    }

    @Override
    public void cancelChange(Long trainingId) {
        //todo
    }

    private void editTrainingWithPrevApprove(ApproveAction approveAction, String title
            , String description, String shortInfo
            , Integer language, Integer maxSize, boolean isInner, List<Long> tagIdList
            , String additionalInfo, List<LessonModel> lessonModelList, RepeatModel repeatModel) {

    }

    private void editTrainingNotPrevApprove(Training training, String title
            , String description, String shortInfo
            , Integer language, Integer maxSize, boolean isInner, List<Long> tagIdList
            , String additionalInfo, List<LessonModel> lessonModelList, RepeatModel repeatModel) {

        ApproveTraining approveTraining = getApproveTraining(title
                , description
                , shortInfo
                , language
                , maxSize
                , additionalInfo);
        ApproveAction approveAction = new ApproveAction();
        approveAction.setDate(getTime());
        approveAction.setTraining(training);
        if(training.isRepeat()) {

        }
    }

    @Override
    public void editTraining(Long trainingId, String title, String description, String shortInfo
            , Integer language, Integer maxSize, boolean isInner, List<Long> tagIdList
            , String additionalInfo, List<LessonModel> lessonModelList, RepeatModel repeatModel) {

    }
}
