package com.slmn.patient_management.models.notifications;

import com.slmn.patient_management.models.users.User;

public abstract class Notification {
    protected String content;

    public abstract boolean isApplicableToUser(User user);

    public String getContent() {
        return this.content;
    }

    public abstract void dismiss();
}
