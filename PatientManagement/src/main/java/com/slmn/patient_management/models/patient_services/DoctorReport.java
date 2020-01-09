package com.slmn.patient_management.models.patient_services;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.users.Doctor;

public class DoctorReport {
    private Doctor doctor;
    private String doctor_id;
    private String userComment;
    private int userRating;
    private String adminFeedback;

    public DoctorReport(LinkedTreeMap map) {
        this.doctor_id = (String) map.get("doctor_id");
        this.userComment = (String) map.get("user_comment");
        this.userRating = (int) ((double) map.get("user_rating"));
        this.adminFeedback = (String) map.get("admin_feedback");
    }

    public DoctorReport(Doctor doctor, String userComment, int userRating) {
        this.doctor = doctor;
        this.userComment = userComment;
        this.userRating = userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public Doctor getDoctor() {
        if (this.doctor == null && this.doctor_id != null) {
            this.doctor = (Doctor) SystemDatabase.connect().getUser(this.doctor_id);
        }
        return this.doctor;
    }

    public int getUserRating() {
        return userRating;
    }

    public String getAdminFeedback() {
        return adminFeedback;
    }

    public String getUserComment() {
        return userComment;
    }

    public String getDoctorID() {
        return this.doctor_id;
    }

    public void giveAdminFeedback(String adminFeedback) {
        this.adminFeedback = adminFeedback;
    }
}
