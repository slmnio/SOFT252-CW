package com.slmn.patient_management.notifications;

public class SpecificUserNotification extends Notification {
    private String userID;

    public SpecificUserNotification(String userID, String content) {
        this.userID = userID;
        this.content = content;
    }
}
