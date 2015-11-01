package com.exadel.training.security.User.UserModel;

public class UserModelSecurity {

    private String username;
    private Long userId;
    private String accessRights;

    public UserModelSecurity(String username, Long userId, String accessRights) {
        this.username = username;
        this.userId = userId;
        this.accessRights = accessRights;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }
}
