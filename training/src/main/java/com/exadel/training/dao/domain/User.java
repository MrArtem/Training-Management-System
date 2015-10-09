package com.exadel.training.dao.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ayudovin on 05.10.2015.
 */
@Entity
@Table
public class User {

    private  enum Role{
        ADMIN, USER, EXCOACH, EXUSER
    }

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Listener> trainingListListener;

    @OneToMany(mappedBy = "coach")
    private List<Training> trainingListCoach;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbackList;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Attendance> attendances;

    public  User(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Training> getTrainingsCoach() {
        return trainingListCoach;
    }

    public void setTrainingsCoach(List<Training> trainingListCoach) {
        this.trainingListCoach = trainingListCoach;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public List<Comment> getCommentList() {
        return comments;
    }

    public void setCommentList(List<Comment> commentList) {
        this.comments = commentList;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<Listener> getTrainingListListener() {
        return trainingListListener;
    }

    public void setTrainingListListener(List<Listener> trainingListListener) {
        this.trainingListListener = trainingListListener;
    }
}
