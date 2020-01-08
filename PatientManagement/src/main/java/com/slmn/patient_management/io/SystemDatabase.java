package com.slmn.patient_management.io;

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
        this.admins = this.loadUsers("admins.json");
        this.secretaries = this.loadUsers("secretaries.json");
        this.doctors = this.loadUsers("doctors.json");
        this.patients = this.loadUsers("patients.json");
        this.medicines = this.loadMedicines();
        this.prescriptions = this.loadPrescriptions();
    }

    public void write() {
        /*
         * Write everything back to their files.
         * Won't be large enough to cause any problems. Also avoids problems of part-saving data with dependencies
         * */

    }

    private ArrayList loadMedicines() {
        JSONArrayDecoder decoder = new JSONArrayDecoder("medicines.json", new MedicineDecoder());
        return decoder.decode();
    }

    private ArrayList loadUsers(String filename) {
        JSONArrayDecoder decoder = new JSONArrayDecoder(filename, new UserDecoder());
        return decoder.decode();
    }

    private ArrayList loadPrescriptions() {
        JSONArrayDecoder decoder = new JSONArrayDecoder("prescriptions.json", new PrescriptionDecoder());
        return decoder.decode();
    }

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
