package com.slmn.patient_management.models.users.requests;

import com.slmn.patient_management.models.users.Patient;

public interface AccountRequest {
    public void approve();

    public void decline();

    public void completeRequest();

    public String getType();

    public Patient getPatient();
}
