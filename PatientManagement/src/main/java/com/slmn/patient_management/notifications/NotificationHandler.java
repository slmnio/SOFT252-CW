package com.slmn.patient_management.notifications;

import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.user_structures.User;

import java.util.ArrayList;

public class NotificationHandler {
    public NotificationHandler() {

    }

    public static ArrayList<Notification> applicableNotifications(User user) {
        ArrayList<Notification> output = new ArrayList<>();

        for (Notification notification: getNotifications()) {
            if (notification.isApplicableToUser(user)) {
                output.add(notification);
            }
        }

        return output;
    }

    private static ArrayList<Notification> getNotifications() {
        ArrayList<Notification> combined = new ArrayList<>();
        combined.addAll(SystemDatabase.connect().specificUserNotifications);
        combined.addAll(SystemDatabase.connect().userTypeNotifications);

        return combined;
    }
}