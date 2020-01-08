package com.slmn.patient_management.user_structures.requests;

import com.slmn.patient_management.user_structures.Patient;

public class AccountCreationRequest implements AccountRequest {
    private Patient user = null;
    public String type = "Creation";

    public AccountCreationRequest(Patient user) {
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

    private void create(Patient user) {
        // do user

    }

    @Override
    public void completeRequest() {

    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public Patient getPatient() {
        return this.user;
    }
}
