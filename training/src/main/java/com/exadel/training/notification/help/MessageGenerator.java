package com.exadel.training.notification.help;

/**
 * Created by ayudovin on 13.10.2015.
 */
public interface MessageGenerator {
    String getTextActionTrainer(String title, String place, long time);
    String getTextActionListener(String title, String place, long time, boolean isHour);
    String getTextChangeListener(String title, long id);
    String getTextConfirmTrainer(String title, long id, String state, boolean answer);
    String getTextRemoveTrainingListener(String title, long id);
    String getTextRequestFeedback(String title, long id, String nameListener);
    String getTextResponseFeedback(String title, long id, String nameListener, String nameTrainer);
    String getTextNotificationAdminAboutShortage(String title, long id, boolean isDay, int listenerNumber);
    String getTextRaceMoreThreeHours(String title, long id, long idEmployee, long time);
    String getTextRaceLessThreeHours(String title, long id, long idEmployee, long time);
    String getTextPasswordForExCoach(String name, long idTraining, String password);
}
