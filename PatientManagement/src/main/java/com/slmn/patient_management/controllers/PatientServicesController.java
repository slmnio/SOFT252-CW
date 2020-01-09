package com.slmn.patient_management.controllers;

import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.patient_services.DoctorReport;
import com.slmn.patient_management.models.patient_services.PatientRecord;
import com.slmn.patient_management.models.users.Doctor;
import com.slmn.patient_management.models.users.Patient;

public class PatientServicesController extends Controller {
    public PatientServicesController() {

    }

    public DoctorReport rateDoctor(Doctor doctor, String ratingInput, String comment) {
        int rating = Integer.parseInt(ratingInput);
        DoctorReport report = new DoctorReport(doctor, comment, rating);
        SystemDatabase.connect().doctorReports.add(report);
        SystemDatabase.connect().writeAll();
        return report;
    }

    public void addFeedback(DoctorReport report, String feedback) {
        report.giveAdminFeedback(feedback);
    }

    public PatientRecord createPatientRecord(Patient patient, String content) {
        PatientRecord patientRecord = new PatientRecord(patient, content);
        SystemDatabase.connect().patientRecords.add(patientRecord);
        SystemDatabase.connect().writeAll();

        return patientRecord;
    }


}
