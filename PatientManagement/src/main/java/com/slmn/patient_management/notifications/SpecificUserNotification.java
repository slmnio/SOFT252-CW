package com.slmn.patient_management.notifications;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.user_structures.User;

public class SpecificUserNotification extends Notification {
    private String userID;

    public SpecificUserNotification(String userID, String content) {
        this.userID = userID;
        this.content = content;
    }
    public SpecificUserNotification(LinkedTreeMap map) {
        this.userID = (String) map.get("userID");
        this.content = (String) map.get("content");
    }

    @Override
    public boolean isApplicableToUser(User user) { return user.getID().equals(this.userID); }
}
