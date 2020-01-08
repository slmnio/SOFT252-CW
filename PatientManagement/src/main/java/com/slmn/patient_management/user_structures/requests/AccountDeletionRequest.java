package com.slmn.patient_management.user_structures.requests;

import com.slmn.patient_management.user_structures.User;

public class AccountDeletionRequest implements AccountRequest {

    @Override
    public void approve() {
        this.delete(user);
        this.completeRequest();
    }

    @Override
    public void decline() {
        this.completeRequest();
        // ignore
    }

    private void delete(User user) {

    }

    @Override
    public void completeRequest() {

    }
}
