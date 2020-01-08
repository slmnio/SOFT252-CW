package com.slmn.patient_management.notifications;

public abstract class Notification {
    protected String content;

    public void dismiss() {
        // remove from notification collection
    }
}
