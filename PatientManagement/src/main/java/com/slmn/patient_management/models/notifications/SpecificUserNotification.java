package com.slmn.patient_management.models.notifications;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.users.User;

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
    public boolean isApplicableToUser(User user) {
        return user.getID().equals(this.userID);
    }

    @Override
    public void dismiss() {
        SystemDatabase.connect().specificUserNotifications.remove(this);
    }
}
