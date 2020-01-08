package com.slmn.patient_management.user_structures.requests;

import com.slmn.patient_management.user_structures.User;

public interface AccountRequest {
    User user = null;

    public void approve();
    public void decline();
    public void completeRequest();
}
