package com.slmn.patient_management.notifications;

import com.slmn.patient_management.user_structures.User;

public abstract class Notification {
    protected String content;

    public void dismiss() {
        // remove from notification collection
    }

    public abstract boolean isApplicableToUser(User user);

    public String getContent() { return this.content; }
}
