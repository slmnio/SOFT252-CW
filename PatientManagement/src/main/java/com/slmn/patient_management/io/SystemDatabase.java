package com.slmn.patient_management.io;

import com.slmn.patient_management.user_structures.*;
import com.slmn.patient_management.drug_structures.*;
import com.slmn.patient_management.io.*;

import java.util.ArrayList;

public class SystemDatabase {
    public ArrayList<Administrator> admins;
    public ArrayList<Secretary> secretaries;
    public ArrayList<Doctor> doctors;
    public ArrayList<Patient> patients;

    public SystemDatabase() {
        this.admins = this.loadUsers("admins.json");
        this.secretaries = this.loadUsers("secretaries.json");
        this.doctors = this.loadUsers("doctors.json");
        this.patients = this.loadUsers("patients.json");
    }

    private ArrayList loadUsers(String filename) {
        JSONArrayDecoder decoder = new JSONArrayDecoder(filename, new UserDecoder());
        return decoder.decode();

    }
}
