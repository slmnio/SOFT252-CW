package com.slmn.patient_management.models.reports;

import com.slmn.patient_management.models.users.Doctor;

public class DoctorReport {
    private Doctor doctor;
    private String doctor_id;
    private String userComment;
    private int userRating;
    private String adminFeedback;

    public DoctorReport(Doctor doctor, String userComment, int userRating) {
        this.doctor = doctor;
        this.userComment = userComment;
        this.userRating = userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public Doctor getDoctor() {
        return doctor;
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
}
