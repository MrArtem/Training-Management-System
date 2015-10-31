package com.exadel.training.service.impl;

import com.exadel.training.controller.model.trainingModels.ApproveLessonModel;
import com.exadel.training.controller.model.trainingModels.LessonModel;
import com.exadel.training.controller.model.trainingModels.RepeatModel;
import com.exadel.training.dao.*;
import com.exadel.training.dao.domain.*;
import com.exadel.training.service.NewsService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.utils.Utils;
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
    @Autowired
    private NewsService newsService;
    @Autowired
    private AttendanceDAO attendanceDAO;

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
            , String description, String shortInfo, Integer language, Integer maxSize, String additionalInfo, boolean isInner) {
        ApproveTraining approveTraining = new ApproveTraining();
        approveTraining.setTitle(title);
        approveTraining.setDescription(description);
        approveTraining.setExcerpt(shortInfo);
        approveTraining.setAdditionalInfo(additionalInfo);
        approveTraining.setMaxSize(maxSize);
        approveTraining.setLanguage(language);
        approveTraining.setIsInner(isInner);
        return approveTraining;
    }

    private List<Tag> getTagList(List<Long> tagIdList) {
        List<Tag> tagList = new ArrayList<Tag>();
        if (tagIdList == null) {
            return null;
        }
        for (Long i : tagIdList) {
            tagList.add(tagDAO.getTag(i));
        }
        return tagList;
    }


    private List<ApproveLesson> addLessonListNotRepeating(Training training
            , List<LessonModel> lessonModelList, boolean isConfirmed, boolean createLesson, String place) {
        List<ApproveLesson> approveLessonList = new ArrayList<ApproveLesson>();
        for (LessonModel lessonModel : lessonModelList) {
            Lesson lesson = null;
            if (createLesson) {
                lesson = new Lesson();
                lesson.setTraining(training);
                lesson.setDate(lessonModel.getDate());
                if (place == null) {
                    lesson.setPlace(lessonModel.getPlace());
                } else {
                    lesson.setPlace(place);
                }
                lessonDAO.addLesson(lesson);
                for (Listener listener : Utils.emptyIfNull(training.getListenerList())) {
                    Attendance attendance = new Attendance();
                    attendance.setLesson(lesson);
                    attendance.setUser(listener.getUser());
                    attendanceDAO.save(attendance);
                }
            }
            if (!isConfirmed) {
                ApproveLesson approveLesson = new ApproveLesson();
                approveLesson.setLesson(lesson);
                approveLesson.setDate(lessonModel.getDate());
                if (place == null) {
                    approveLesson.setPlace(lessonModel.getPlace());
                } else {
                    approveLesson.setPlace(place);
                }
                lessonApproveDAO.addApprove(approveLesson);
                if(lesson != null) {
                    lesson.setApproveLesson(approveLesson);
                }
                approveLessonList.add(approveLesson);
            }
        }
        return approveLessonList;
    }

    private List<ApproveLesson> addLessonListRepeating(Training training, RepeatModel repeatModel
            , boolean isConfirmed, boolean createLesson, String place) {
        List<ApproveLesson> approveLessonList = new ArrayList<ApproveLesson>();
        LessonModel[] lessonModelList = repeatModel.getLessonList();
        int dayOfWeekStart = Utils. getDayOfWeek(repeatModel.getStartDate());
        for (int i = 0; i < 7; i++) {
            if (lessonModelList[i] == null) {
                continue;
            }
            int offset = (i - dayOfWeekStart + DAY_OF_WEEK) % DAY_OF_WEEK;
            long dateLesson = lessonModelList[i].getDate() + repeatModel.getStartDate() + offset * MILLIS_IN_DAY;
            for (; dateLesson < repeatModel.getEndDate(); dateLesson += MILLIS_IN_WEEK) {
                Lesson lesson = null;
                if (createLesson) {
                    lesson = new Lesson();
                    lesson.setTraining(training);
                    lesson.setDate(dateLesson);
                    if (place == null) {
                        lesson.setPlace(lessonModelList[i].getPlace());
                    } else {
                        lesson.setPlace(place);
                    }
                    lessonDAO.addLesson(lesson);
                    for (Listener listener : Utils.emptyIfNull(training.getListenerList())) {
                        Attendance attendance = new Attendance();
                        attendance.setLesson(lesson);
                        attendance.setUser(listener.getUser());
                        attendanceDAO.save(attendance);
                    }
                }
                if (!isConfirmed) {
                    ApproveLesson approveLesson = new ApproveLesson();
                    approveLesson.setLesson(lesson);
                    approveLesson.setDate(dateLesson);
                    if (place == null) {
                        approveLesson.setPlace(lessonModelList[i].getPlace());
                    } else {
                        approveLesson.setPlace(place);
                    }
                    lessonApproveDAO.addApprove(approveLesson);
                    approveLessonList.add(approveLesson);

                    if(lesson != null) {
                        lesson.setApproveLesson(approveLesson);
                    }
                }
            }
        }
        return approveLessonList;
    }

    @Override
    public void createTraining(Long coachId, String title, String description, String shortInfo
            , Integer language, Integer maxSize, boolean isInner, String place
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
                , additionalInfo
                , isInner);
        approveTraining.setTagList(tagList);
        trainingApproveDAO.addApprove(approveTraining);
        ApproveAction approveAction = new ApproveAction();
        approveAction.setDate(Utils.getTime());
        approveAction.setType(ApproveAction.Type.CREATE);
        approveAction.setTraining(training);
        List<ApproveLesson> approveLessonList = null;
        if (isRepeating) {
            approveLessonList = addLessonListRepeating(training, repeatModel, false, true, place);
        } else {
            approveLessonList = addLessonListNotRepeating(training, lessonModelList, false, true, place);
        }
        approveAction.setApproveLessonList(approveLessonList);
        approveAction.setType(ApproveAction.Type.CREATE);
        approveActionDAO.addApproveAction(approveAction);
        //todo
        newsService.addNews(coach, News.TableName.TRAINING, News.ActionType.CREATE, training.getId());
    }

    private void removeApproveLessonList(ApproveAction approveAction, boolean removeLesson) {
        Training training = approveAction.getTraining();
        List<Lesson> lessonList = training.getLessonList();
        for (Lesson lesson : Utils.emptyIfNull(training.getLessonList())) {
            lessonApproveDAO.removeApprove(lesson.getApproveLesson());
            if ( removeLesson) {
                lessonDAO.removeLesson(lesson);
            }
        }
        if(removeLesson && lessonList != null) {
            lessonList.clear();
        }
        trainingDAO.changeTraining(training);
        approveActionDAO.removeApproveAction(approveAction);
    }

    @Override
    public void confirmTraining(Long actionId, String title, String description, String shortInfo
            , Integer language, Integer maxSize, boolean isInner, String place, List<Long> tagIdList
            , List<LessonModel> lessonModelList, RepeatModel repeatModel) {
        ApproveAction approveAction = approveActionDAO.getApproveAction(actionId);
        removeApproveLessonList(approveAction, true);
        Training training = approveAction.getTraining();
        training.setTitle(title);
        training.setDescription(description);
        training.setExcerpt(shortInfo);
        training.setLanguage(language);
        training.setMaxSize(maxSize);
        training.setIsInner(isInner);
        training.setTagList(getTagList(tagIdList));
        if (training.isRepeat()) {
            addLessonListRepeating(training, repeatModel, true, true, place);
        } else {
            addLessonListNotRepeating(training, lessonModelList, true, true, place);
        }
        training.setState(Training.State.NONE);
        trainingDAO.changeTraining(training);
    }

    @Override
    public void cancelCreate(Long actionId) {
        ApproveAction approveAction = approveActionDAO.getApproveAction(actionId);
        removeApproveLessonList(approveAction, false);
        Training training = approveAction.getTraining();
        training.setState(Training.State.REMOVE);
        trainingDAO.changeTraining(training);
    }

    @Override
    public void cancelChange(Long actionId) {
        ApproveAction approveAction = approveActionDAO.getApproveAction(actionId);
        ApproveTraining approveTraining = approveAction.getApproveTraining();
        if (approveTraining != null) {
            trainingApproveDAO.removeApprove(approveTraining);
        }
        removeApproveLessonList(approveAction, false);
    }

    private void editTrainingWithPrevApprove(ApproveAction approveAction, String title
            , String description, String shortInfo
            , Integer language, Integer maxSize, boolean isInner, String place, List<Long> tagIdList
            , String additionalInfo, List<LessonModel> lessonModelList, RepeatModel repeatModel) {
        approveAction.setDate(Utils.getTime());
        ApproveTraining approveTraining = approveAction.getApproveTraining();

        Training training = approveAction.getTraining();
        if (!training.getTitle().equals(title)) {
            approveTraining.setTitle(title);
        }
        if (!training.getDescription().equals(description)) {
            approveTraining.setDescription(description);
        }
        if (!training.getExcerpt().equals(shortInfo)) {
            approveTraining.setExcerpt(shortInfo);
        }
        if (training.getLanguage() != language) {
            approveTraining.setLanguage(language);
        }
        if (training.getMaxSize() != maxSize) {
            approveTraining.setMaxSize(maxSize);
        }
        if (training.isInner() != isInner) {
            approveTraining.setIsInner(isInner);
        }
        approveTraining.setAdditionalInfo(additionalInfo);
        approveTraining.setTagList(getTagList(tagIdList));

        removeApproveLessonList(approveAction, false);

        List<ApproveLesson> approveLessonList;
        if (training.isRepeat()) {
            approveLessonList = addLessonListRepeating(training, repeatModel, false, false, place);
        } else {
            approveLessonList = addLessonListNotRepeating(training, lessonModelList, false, false, place);
        }
        approveAction.setApproveLessonList(approveLessonList);
    }

    private void editTrainingNotPrevApprove(Training training, String title
            , String description, String shortInfo
            , Integer language, Integer maxSize, boolean isInner, String place, List<Long> tagIdList
            , String additionalInfo, List<LessonModel> lessonModelList, RepeatModel repeatModel) {

        ApproveTraining approveTraining = getApproveTraining(title
                , description
                , shortInfo
                , language
                , maxSize
                , additionalInfo
                , isInner);
        ApproveAction approveAction = new ApproveAction();
        approveAction.setDate(Utils.getTime());
        approveAction.setTraining(training);
        approveAction.setApproveTraining(approveTraining);
        approveAction.setType(ApproveAction.Type.EDIT);
        List<ApproveLesson> approveLessonList;
        if (training.isRepeat()) {
            approveLessonList = addLessonListRepeating(training, repeatModel, false, false, place);
        } else {
            approveLessonList = addLessonListNotRepeating(training, lessonModelList, false, false, place);
        }

        approveAction.setApproveLessonList(approveLessonList);
        approveActionDAO.addApproveAction(approveAction);

    }

    @Override
    public void editTraining(Long trainingId, String title, String description, String shortInfo
            , Integer language, Integer maxSize, boolean isInner, String place, List<Long> tagIdList
            , String additionalInfo, List<LessonModel> lessonModelList, RepeatModel repeatModel) {
        ApproveAction approveAction = approveActionDAO.getApproveActionByTrainingId(trainingId);
        if (approveAction == null) {
            editTrainingNotPrevApprove(trainingDAO.getTrainingById(trainingId), title, description
                    , shortInfo, language, maxSize, isInner, place, tagIdList
                    , additionalInfo, lessonModelList, repeatModel);
        } else {
            editTrainingWithPrevApprove(approveAction, title, description
                    , shortInfo, language, maxSize, isInner, place, tagIdList
                    , additionalInfo, lessonModelList, repeatModel);
        }
    }

    @Override
    public ApproveAction getApproveAction(long actionId) {
        return approveActionDAO.getApproveAction(actionId);
    }

    @Override
    public List<ApproveLesson> getApproveLessonList(long actionId) {
        ApproveAction approveAction = approveActionDAO.getApproveAction(actionId);
        return approveAction.getApproveLessonList();
    }

    @Override
    public RepeatModel getApproveRepeatModel(long actionId) {
        ApproveAction approveAction = approveActionDAO.getApproveAction(actionId);
        List<ApproveLesson> approveLessonList = approveAction.getApproveLessonList();
        RepeatModel repeatModel = new RepeatModel();
        LessonModel[] lessonModelList = new LessonModel[7];
        Long startDate = null;
        Long endDate = null;
        for (ApproveLesson approveLesson : Utils.emptyIfNull(approveLessonList)) {
            int dayOfWeek = Utils.getDayOfWeek(approveLesson.getDate());
            LessonModel lessonModel = new LessonModel();
            lessonModel.setDate(approveLesson.getDate());
            lessonModel.setPlace(approveLesson.getPlace());
            lessonModelList[dayOfWeek] = lessonModel;
            if (startDate == null) {
                startDate = approveLesson.getDate();
                endDate = approveLesson.getDate();
            }
            if (startDate > approveLesson.getDate()) {
                startDate = approveLesson.getDate();
            }
            if (endDate < approveLesson.getDate()) {
                endDate = approveLesson.getDate();
            }
        }
        repeatModel.setLessonList(lessonModelList);
        repeatModel.setStartDate(startDate);
        repeatModel.setEndDate(endDate);
        return repeatModel;
    }

    @Override
    public void editLesson(long trainingId, LessonModel lessonModel) {
        Training training = trainingDAO.getTrainingById(trainingId);
        ApproveAction approveAction = new ApproveAction();
        approveAction.setDate(Utils.getTime());
        approveAction.setTraining(training);
        approveAction.setType(ApproveAction.Type.EDIT);

        Lesson lesson = lessonDAO.getLessonById(lessonModel.getPrevLessonId());

        ApproveLesson approveLesson = new ApproveLesson();
        approveLesson.setLesson(lesson);
        approveLesson.setDate(lessonModel.getDate());
        approveLesson.setPlace(lessonModel.getPlace());
        lessonApproveDAO.addApprove(approveLesson);

        List<ApproveLesson> approveLessonList = new ArrayList<ApproveLesson>();
        approveLessonList.add(approveLesson);
        approveAction.setApproveLessonList(approveLessonList);
        approveActionDAO.addApproveAction(approveAction);
    }

    @Override
    public void addLesson(long trainingId, LessonModel lessonModel) {
        Training training = trainingDAO.getTrainingById(trainingId);

        Lesson lesson = new Lesson();
        lesson.setState(Lesson.State.REMOVAL);
        lesson.setTraining(training);
        lessonDAO.addLesson(lesson);

        ApproveLesson approveLesson = new ApproveLesson();
        approveLesson.setDate(lessonModel.getDate());
        approveLesson.setPlace(lessonModel.getPlace());
        approveLesson.setLesson(lesson);
        lessonApproveDAO.addApprove(approveLesson);

        ApproveAction approveAction = new ApproveAction();
        approveAction.setTraining(training);
        approveAction.setDate(Utils.getTime());
        approveAction.setType(ApproveAction.Type.EDIT);

        List<ApproveLesson> approveLessonList = new ArrayList<ApproveLesson>();
        approveLessonList.add(approveLesson);
        approveAction.setApproveLessonList(approveLessonList);
        approveActionDAO.addApproveAction(approveAction);
    }

    @Override
    public void removeLesson(long trainingId, LessonModel lessonModel) {
        Training training = trainingDAO.getTrainingById(trainingId);

        Lesson lesson = lessonDAO.getLessonById(lessonModel.getPrevLessonId());

        ApproveLesson approveLesson = new ApproveLesson();
        approveLesson.setLesson(lesson);
        lessonApproveDAO.addApprove(approveLesson);

        ApproveAction approveAction = new ApproveAction();
        approveAction.setTraining(training);
        approveAction.setDate(Utils.getTime());
        approveAction.setType(ApproveAction.Type.EDIT);

        List<ApproveLesson> approveLessonList = new ArrayList<ApproveLesson>();
        approveLessonList.add(approveLesson);
        approveAction.setApproveLessonList(approveLessonList);
        approveActionDAO.addApproveAction(approveAction);
    }

    @Override
    public void confirmLesson(long actionId, ApproveLessonModel approveLessonModel) {
        ApproveAction approveAction = approveActionDAO.getApproveAction(actionId);
        ApproveLesson approveLesson = approveAction.getApproveLessonList().get(0);
        Lesson lesson = approveLesson.getLesson();
        Training training = approveAction.getTraining();
        if(lesson.getState() == Lesson.State.REMOVAL) {
            lesson.setPlace(approveLessonModel.getNewPlace());
            lesson.setDate(approveLessonModel.getNewDate());
            lesson.setState(Lesson.State.ADD);
            lessonDAO.changeLesson(lesson);
            for( Listener listener : Utils.emptyIfNull(training.getListenerList())) {
                Attendance attendance = new Attendance();
                attendance.setUser(listener.getUser());
                attendance.setLesson(lesson);
                attendanceDAO.save(attendance);
            }
        }
        if(lesson.getState() == Lesson.State.NONE) {
            lesson.setState(Lesson.State.REMOVAL);
            for( Attendance attendance : Utils.emptyIfNull(lesson.getAttendanceList()) ) {
                attendanceDAO.delete(attendance);
            }
            if( approveLesson.getDate() != null) {
                Lesson newLesson = new Lesson();
                newLesson.setDate(approveLessonModel.getNewDate());
                newLesson.setPlace(approveLessonModel.getNewPlace());
                newLesson.setTraining(training);
                newLesson.setState(Lesson.State.ADD);
                lessonDAO.addLesson(newLesson);
                for( Listener listener : Utils.emptyIfNull(training.getListenerList())) {
                    Attendance attendance = new Attendance();
                    attendance.setUser(listener.getUser());
                    attendance.setLesson(lesson);
                    attendanceDAO.save(attendance);
                }
            }
        }
        lessonApproveDAO.removeApprove(approveLesson);
        approveActionDAO.removeApproveAction(approveAction);
    }

    @Override
    public List<Training> getTrainingListByTagList(Integer page, Integer pageSize, Boolean isActual, List<Tag> tagList) {
        return trainingDAO.getTrainingListByTagList(page, pageSize, isActual, tagList);
    }

    @Override
    public double setRating(long trainingId, int rating, long userId) {
        Listener listener = listenerDAO.getListenerByTrainingUser(trainingId, userId);
        if (listener != null && listener.isCanRate()) {
            Training training = trainingDAO.getTrainingById(trainingId);
            int sumRating = training.getSumRating() + rating;
            training.setSumRating(sumRating);
            int count = training.getCountListenerRating() + 1;
            training.setCountListenerRating(count);
            trainingDAO.changeTraining(training);
            listener.setCanRate(false);
            listenerDAO.changeListener(listener);
            return (double) sumRating / count;
        }
        return -1;
    }

    @Override
    public ApproveLesson getApproveLesson(long actionId) {
        ApproveAction approveAction = approveActionDAO.getApproveAction(actionId);
        return  approveAction.getApproveLessonList().get(0);
    }

    @Override
    public void canceledLesson(long actionId) {
        ApproveAction approveAction = approveActionDAO.getApproveAction(actionId);
        ApproveLesson approveLesson = approveAction.getApproveLessonList().get(0);
        Lesson lesson = approveLesson.getLesson();
        lessonApproveDAO.removeApprove(approveLesson);
        approveActionDAO.removeApproveAction(approveAction);
        if(lesson.getState() == Lesson.State.REMOVAL) {
            lessonDAO.removeLesson(lesson);
        } else {
            lesson.setState(Lesson.State.NONE);
            lessonDAO.changeLesson(lesson);
        }
    }
}
