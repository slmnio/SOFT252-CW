package com.slmn.patient_management.io;

import com.slmn.patient_management.io.decoders.JSONClassDecoder;
import com.slmn.patient_management.io.decoders.MedicineDecoder;
import com.slmn.patient_management.io.decoders.PrescriptionDecoder;
import com.slmn.patient_management.io.decoders.UserDecoder;
import com.slmn.patient_management.user_structures.*;
import com.slmn.patient_management.drug_structures.*;

import java.util.ArrayList;

public class SystemDatabase {
    public ArrayList<Administrator> admins;
    public ArrayList<Secretary> secretaries;
    public ArrayList<Doctor> doctors;
    public ArrayList<Patient> patients;

    public ArrayList<Medicine> medicines;
    public ArrayList<Prescription> prescriptions;

    // Singleton

    private static SystemDatabase instance = null;
    private static boolean loaded = false;

    public static SystemDatabase connect() {
        if (loaded == false) {
            loaded = true;
            instance = new SystemDatabase();
        }
        return instance;
    }

    private SystemDatabase() {
        // Load all data into the system
        /*
        Shouldn't need to load the data at any other point other than start, as long as it gets written back timely
        * */
        this.admins = this.load("admins.json", new UserDecoder());
        this.secretaries = this.load("secretaries.json", new UserDecoder());
        this.doctors = this.load("doctors.json", new UserDecoder());
        this.patients = this.load("patients.json", new UserDecoder());
        this.medicines = this.load("medicines.json", new MedicineDecoder());
        this.prescriptions = this.load("prescriptions.json", new PrescriptionDecoder());
    }

    public void writeAll() {
        /*
         * Write everything back to their files.
         * Won't be large enough to cause any problems. Also avoids problems of part-saving data with dependencies
         * */
        this.write("admins.json", this.admins, new UserDecoder());
        this.write("secretaries.json", this.secretaries, new UserDecoder());
        this.write("doctors.json", this.doctors, new UserDecoder());
        this.write("patients.json", this.patients, new UserDecoder());
        this.write("medicines.json", this.medicines, new MedicineDecoder());
        this.write("prescriptions.json", this.prescriptions, new PrescriptionDecoder());
    }

    private void write(String filename, ArrayList users, JSONClassDecoder decoderPlugin) {
        JSONArrayDecoder decoder = new JSONArrayDecoder(filename, decoderPlugin);
        decoder.encode(users);
    }

    private ArrayList load(String filename, JSONClassDecoder decoderPlugin) {
        JSONArrayDecoder decoder = new JSONArrayDecoder(filename, decoderPlugin);
        return decoder.decode();
    }

    // Collection searching
    public User getUser(String ID) {
        ArrayList<User> users = new ArrayList<User>();
        users.addAll(admins);
        users.addAll(doctors);
        users.addAll(secretaries);
        users.addAll(patients);

        for (User user: users) {
            if (user.getID().toUpperCase().equals(ID.toUpperCase())) {
                return user;
            }
        }

        return null;
    }

    public Medicine getMedicine(String medicineName) {
        for (Medicine medicine: this.medicines) {
            if (medicineName.equals(medicine.getName())) {
                return medicine;
            }
        }
        return null;
    }
}
