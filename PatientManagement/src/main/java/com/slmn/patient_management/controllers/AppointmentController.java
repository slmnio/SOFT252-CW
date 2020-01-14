package com.slmn.patient_management.controllers;

import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.appointments.Appointment;
import com.slmn.patient_management.models.appointments.AppointmentRequest;
import com.slmn.patient_management.models.appointments.SurgeryDay;
import com.slmn.patient_management.models.appointments.TimeSlot;
import com.slmn.patient_management.models.notifications.NotificationHandler;
import com.slmn.patient_management.models.users.Doctor;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.views.PatientRecordsDoctorView;
import com.slmn.patient_management.views.PrescriberView;

import java.util.ArrayList;

public class AppointmentController extends Controller {

    public ArrayList<TimeSlot> generateTimeSlots(String date) {
        SurgeryDay day = new SurgeryDay(date);

        day.generateEmpty();

        return day.getTimeSlots();
    }

    public ArrayList<TimeSlot> generateOccupiedTimeSlots(String date, Doctor doctor) {
        SurgeryDay day = new SurgeryDay(date);
        day.generateEmpty();
        day.setDoctorOccupied(doctor);
        return day.getTimeSlots();
    }

    public AppointmentRequest requestAppointment(Patient patient, Doctor doctor, String date, TimeSlot timeSlot) {
        if (doctor.hasAppointmentAt(date, timeSlot)) {
            showErrorMessage(String.format("Dr %s already has an appointment at that time.", doctor.getFullName()));
            return null;
        }

        AppointmentRequest request = new AppointmentRequest(new Appointment(patient, doctor, date, timeSlot.toString()));
        SystemDatabase.connect().appointmentRequests.add(request);
        SystemDatabase.connect().writeAll();
        showInfoMessage(String.format("Your appointment with Dr %s has been requested.", request.getAppointment().getDoctor().getFullName()), "Request submitted");
        NotificationHandler.notifySecretaries(String.format("A new appointment has been requested by %s %s", patient.getID(), patient.getFullName()));

        return request;
    }

    public Appointment createAppointment(Patient patient, Doctor doctor, String date, TimeSlot timeSlot) {
        if (doctor.hasAppointmentAt(date, timeSlot)) {
            showErrorMessage(String.format("Dr %s already has an appointment at that time.", doctor.getFullName()));
            return null;
        }

        Appointment appointment = new Appointment(patient, doctor, date, timeSlot.toString());
        SystemDatabase.connect().appointments.add(appointment);
        SystemDatabase.connect().writeAll();
        showInfoMessage(String.format("The appointment with Dr %s has been created.", appointment.getDoctor().getFullName()), "Appointment created");

        return appointment;
    }

    public Appointment approveAppointmentRequest(AppointmentRequest appointmentRequest) {
        if (appointmentRequest.getAppointment().getDoctor().hasAppointmentAt(appointmentRequest.getAppointment().getDate(), appointmentRequest.getAppointment().getTimeSlot())) {
            showErrorMessage(String.format("Dr %s already has an appointment at that time.", appointmentRequest.getAppointment().getDoctor().getFullName()));
            return null;
        }
        appointmentRequest.approve();
        showInfoMessage(String.format("The appointment with Dr %s has been approved.", appointmentRequest.getAppointment().getDoctor().getFullName()), "Appointment created");
        return appointmentRequest.getAppointment();
    }

    public void declineAppointmentRequest(AppointmentRequest appointmentRequest) {
        showInfoMessage(String.format("The appointment request with Dr %s has been declined.", appointmentRequest.getAppointment().getDoctor().getFullName()), "Appointment request declined");
        appointmentRequest.decline();
    }

    public void routeAppointmentPrescription(Appointment appointment) {
        Main.popupFrame(new PrescriberView(appointment));
    }

    public void routeAppointmentNotes(Appointment appointment) {
        Main.popupFrame(new PatientRecordsDoctorView(appointment));
    }
}
