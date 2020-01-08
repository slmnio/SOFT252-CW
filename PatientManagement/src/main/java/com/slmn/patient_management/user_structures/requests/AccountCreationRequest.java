package com.slmn.patient_management.user_structures.requests;

import com.slmn.patient_management.user_structures.User;

public class AccountCreationRequest implements AccountRequest {
    private User user = null;

    public AccountCreationRequest(User user) {
        this.user = user;
    }

    @Override
    public void approve() {
        this.create(user);
        this.completeRequest();
    }

    @Override
    public void decline() {
        this.completeRequest();
        // ignore
    }

    private void create(User user) {
        // do user

    }

    @Override
    public void completeRequest() {

    }
}
