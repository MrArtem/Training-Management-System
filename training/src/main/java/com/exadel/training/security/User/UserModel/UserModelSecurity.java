package com.exadel.training.security.User.UserModel;

public class UserModelSecurity {

    private String userName;
    private Long userId;
    private String role;

    public UserModelSecurity(String userName, Long userId, String role) {
        this.userName = userName;
        this.userId = userId;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
