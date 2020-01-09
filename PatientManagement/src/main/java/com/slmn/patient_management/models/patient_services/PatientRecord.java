package com.slmn.patient_management.models.patient_services;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.users.Patient;

public class PatientRecord {
    private String content;
    private Patient patient;
    private String patient_id;

    public PatientRecord(Patient patient, String content) {
        this.content = content;
        this.patient = patient;
    }

    public PatientRecord(LinkedTreeMap map) {
        this.patient_id = (String) map.get("patient_id");
        this.content = (String) map.get("content");
    }

    public String getContent() {
        return content;
    }

    public Patient getPatient() {
        if (this.patient == null && this.patient_id != null) {
            this.patient = (Patient) SystemDatabase.connect().getUser(this.patient_id);
        }
        return patient;
    }
}
