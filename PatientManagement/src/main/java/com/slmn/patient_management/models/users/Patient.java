/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmn.patient_management.models.users;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.models.drugs.Medicine;
import com.slmn.patient_management.models.drugs.Prescription;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.notifications.Notification;
import com.slmn.patient_management.models.patient_services.PatientRecord;
import com.slmn.patient_management.models.users.requests.AccountRequest;

import java.util.ArrayList;

/**
 *
 * @author Jill
 */

public class Patient extends User {
    private int age;
    private String sex; 
    // "sex" is the medically correct term for "gender assigned at birth"
    
    public Patient (String givenName, String surname, String address, String password, int age, String sex) {
        super("P", givenName, surname, address, password);
        
        this.age = age;
        this.sex = sex;
    }

    public Patient(LinkedTreeMap object) {
        super(object);

        // Gson imports numbers as double, have to cast it from the object then cast again to the class
        this.age = (int)((double) object.get("age"));
        this.sex = (String) object.get("sex");
    }

    @Override
    public void destroyDependencies() {
        for (Notification notification: SystemDatabase.connect().specificUserNotifications) {
            if (notification.isApplicableToUser(this)) notification.dismiss();
        }

        for (AccountRequest request : SystemDatabase.connect().accountRequests) {
            if (request.getPatient().equals(this)) request.decline();
        }

        SystemDatabase.connect().prescriptions.removeIf(prescription -> prescription.getPatient().equals(this));

        // appointments

        SystemDatabase.connect().writeAll();
    }

    public ArrayList<PatientRecord> getPatientRecords() {
        ArrayList<PatientRecord> output = new ArrayList<>();
        for (PatientRecord record : SystemDatabase.connect().patientRecords) {
            if (record.getPatient().equals(this)) output.add(record);
        }
        return output;
    }

    public Prescription createPrescription(Medicine medicine, int quantity, String dosage) {
        return new Prescription(this, medicine, quantity, dosage);
    }
}