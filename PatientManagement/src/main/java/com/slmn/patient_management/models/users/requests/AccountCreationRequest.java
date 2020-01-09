package com.slmn.patient_management.models.users.requests;

import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.users.Patient;

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
        SystemDatabase.connect().patients.add(user);
    }
    public void completeRequest() {
        SystemDatabase.connect().accountRequests.remove(this);
        SystemDatabase.connect().writeAll();
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
