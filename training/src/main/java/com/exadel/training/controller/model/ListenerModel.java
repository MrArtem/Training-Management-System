package com.exadel.training.controller.model;


import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.User;

public class ListenerModel {
    private Long participantId;
    private String name;
    private String email;
    private boolean isInternal;
    private boolean isWaiting;

    public ListenerModel() {
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isInternal() {
        return isInternal;
    }

    public void setIsInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public void setIsWaiting(boolean isWaiting) {
        this.isWaiting = isWaiting;
    }

    public ListenerModel(Listener listener) {
        User user = listener.getUser();
        name = user.getFirstName() + " " + user.getLastName();
        isInternal = true;
        if(user.getRole() == User.Role.EXUSER) {
            isInternal = false;
        }
        participantId = listener.getId();
        if (listener.getState() == Listener.State.WAITING) {
            isWaiting = true;
        }
        else {
            isWaiting = false;
        }
    }
}
