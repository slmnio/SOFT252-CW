/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmn.patient_management.models.users;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.appointments.Appointment;
import com.slmn.patient_management.models.appointments.TimeSlot;
import com.slmn.patient_management.models.notifications.Notification;
import com.slmn.patient_management.models.patient_services.DoctorReport;

import java.util.ArrayList;

/**
 * @author Jill
 */
public class Doctor extends User {
    public Doctor(String givenName, String surname, String address, String password) {
        super("D", givenName, surname, address, password);
    }

    public Doctor(LinkedTreeMap object) {
        super(object);
    }

    @Override
    public void destroyDependencies() {
        for (Notification notification : SystemDatabase.connect().specificUserNotifications) {
            if (notification.isApplicableToUser(this)) notification.dismiss();
        }
        SystemDatabase.connect().doctorReports.removeIf(report -> report.getDoctor().equals(this));
    }

    public ArrayList<DoctorReport> getDoctorReports() {
        ArrayList<DoctorReport> output = new ArrayList<>();
        for (DoctorReport report : SystemDatabase.connect().doctorReports) {
            if (report.getDoctor().equals(this)) output.add(report);
        }
        return output;
    }

    public String getAverageRating() {
        double sum = 0;
        for (DoctorReport report : this.getDoctorReports()) {
            sum += report.getUserRating();
        }
        double average = sum / this.getDoctorReports().size();

        return String.format("%.2f", average);
    }

    private ArrayList<Appointment> getAppointments() {
        ArrayList<Appointment> output = new ArrayList<>();
        for (Appointment appointment : SystemDatabase.connect().appointments) {
            if (appointment.getDoctor().equals(this)) output.add(appointment);
        }
        return output;
    }

    public boolean hasAppointmentAt(String date, TimeSlot timeslot) {
        // TODO: this lmao
        return false;
    }
}