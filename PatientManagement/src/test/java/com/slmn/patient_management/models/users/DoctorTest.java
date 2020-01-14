package com.slmn.patient_management.models.users;

import com.slmn.patient_management.controllers.PatientServicesController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    private Doctor doctor;

    public DoctorTest() {
        doctor = new Doctor("Test", "Doctor", "1 Surgery Rd", "password");

        PatientServicesController controller = new PatientServicesController();
        controller.rateDoctor(doctor, "2", "Bad");
        controller.rateDoctor(doctor, "10", "Best doctor ever");
        controller.rateDoctor(doctor, "6", "Good time");

        // Average should be 2+10+6 -> 6
    }

    @Test
    void getAverageRating() {
        assertEquals("6.00", doctor.getAverageRating(), "Average calculates properly");
    }
}