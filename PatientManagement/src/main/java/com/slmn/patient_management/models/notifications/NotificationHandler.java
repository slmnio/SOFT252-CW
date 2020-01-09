package com.slmn.patient_management.models.notifications;

import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.users.*;

import java.util.ArrayList;

public class NotificationHandler {
    public NotificationHandler() {

    }

    public static SpecificUserNotification createUserNotification(String userID, String content) {
        SpecificUserNotification notification = new SpecificUserNotification(userID, content);
        SystemDatabase.connect().specificUserNotifications.add(notification);
        SystemDatabase.connect().writeAll();
        return notification;
    }

    public static UserTypeNotification createUserNotification(Class userClass, String content) {
        UserTypeNotification notification = new UserTypeNotification(userClass.getSimpleName(), content);
        SystemDatabase.connect().userTypeNotifications.add(notification);
        SystemDatabase.connect().writeAll();
        return notification;
    }

    public static UserTypeNotification notifyAdmins(String content) {
        return createUserNotification(Administrator.class, content);
    }

    public static UserTypeNotification notifyDoctors(String content) {
        return createUserNotification(Doctor.class, content);
    }

    public static UserTypeNotification notifyPatients(String content) {
        return createUserNotification(Patient.class, content);
    }

    public static UserTypeNotification notifySecretaries(String content) {
        return createUserNotification(Secretary.class, content);
    }

    public static ArrayList<Notification> applicableNotifications(User user) {
        ArrayList<Notification> output = new ArrayList<>();

        for (Notification notification : getNotifications()) {
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

    public static void dismissAll(ArrayList<Notification> notifications) {
        for (Notification notification : notifications) {
            notification.dismiss();
        }
        SystemDatabase.connect().writeAll();
    }
}