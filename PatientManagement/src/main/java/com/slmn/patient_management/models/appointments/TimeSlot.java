package com.slmn.patient_management.models.appointments;

public class TimeSlot {
    private boolean isOccupied;
    private String displayTime;

    public TimeSlot(String displayTime) {
        this.displayTime = displayTime;
    }
}
