package com.slmn.patient_management.user_structures.requests;

import com.slmn.patient_management.user_structures.Patient;

public interface AccountRequest {
    public void approve();
    public void decline();
    public void completeRequest();
    public String type = "";
    
    public Patient getPatient();
}
