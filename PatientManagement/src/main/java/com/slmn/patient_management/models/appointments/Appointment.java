package com.slmn.patient_management.models.appointments;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.users.Doctor;
import com.slmn.patient_management.models.users.Patient;

public class Appointment {
    private String timeSlot;
    private String date;
    private Doctor doctor;
    private Patient patient;
    // importer _ids
    private String patient_id;
    private String doctor_id;

    public Appointment(Patient patient, Doctor doctor, String date, String timeSlot) {
        this.timeSlot = timeSlot;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Appointment(LinkedTreeMap map) {
        this.doctor_id = (String) map.get("doctor_id");
        this.patient_id = (String) map.get("patient_id");
        this.date = (String) map.get("date");
        this.timeSlot = (String) map.get("timeSlot");
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        if (this.doctor == null && this.doctor_id != null) {
            this.doctor = (Doctor) SystemDatabase.connect().getUser(this.doctor_id);
        }
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        if (this.patient == null && this.patient_id != null) {
            this.patient = (Patient) SystemDatabase.connect().getUser(this.patient_id);
        }
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
