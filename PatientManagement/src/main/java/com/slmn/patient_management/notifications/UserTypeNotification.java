package com.slmn.patient_management.notifications;

public class UserTypeNotification extends Notification {
    private String userType;

    public UserTypeNotification(String userType, String content) {
        this.userType = userType;
        this.content = content;
    }
}
