package com.slmn.patient_management.models.appointments;

public class TimeSlot {
    private boolean isOccupied;
    private String displayTime;

    public TimeSlot(String displayTime) {
        this.displayTime = displayTime;
    }

    @Override
    public String toString() {
        return displayTime;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
