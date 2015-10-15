package com.exadel.training.security.User.UserModel;

public class UserModelSecurity {

    private String username;
    private Long userId;
    private String accessRight;

    public UserModelSecurity(String username, Long userId, String accessRight) {
        this.username = username;
        this.userId = userId;
        this.accessRight = accessRight;
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

    public String getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(String accessRight) {
        this.accessRight = accessRight;
    }
}
