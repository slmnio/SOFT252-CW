package com.slmn.patient_management.notifications;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.user_structures.*;

public class UserTypeNotification extends Notification {
    private String userType;

    public UserTypeNotification(String userType, String content) {
        this.userType = userType;
        this.content = content;
    }

    public UserTypeNotification(String content) {
        this.content = content;
    }

    /* Instead of typing "Administrator" - getting the class name means the IDE can rename it too if it gets changed
    * */
    public void notifyAdmins() { this.userType = Administrator.class.getSimpleName(); }
    public void notifyDoctors() { this.userType = Doctor.class.getSimpleName(); }
    public void notifyPatients() { this.userType = Patient.class.getSimpleName(); }
    public void notifySecretaries() { this.userType = Secretary.class.getSimpleName(); }

    public UserTypeNotification(LinkedTreeMap map) {
        this.userType = (String) map.get("userType");
        this.content = (String) map.get("content");
    }

    @Override
    public boolean isApplicableToUser(User user) { return user.getClass().getSimpleName().equals(this.userType); }
}
