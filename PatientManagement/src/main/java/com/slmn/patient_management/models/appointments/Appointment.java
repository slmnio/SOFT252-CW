package com.slmn.patient_management.models.appointments;

import com.slmn.patient_management.models.users.Doctor;
import com.slmn.patient_management.models.users.Patient;

public class Appointment {
    private String timeSlot;
    private String date;
    private Doctor doctor;
    private Patient patient;
}
