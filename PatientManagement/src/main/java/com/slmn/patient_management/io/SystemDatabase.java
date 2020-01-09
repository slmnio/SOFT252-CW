package com.slmn.patient_management.io;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.drug_structures.Medicine;
import com.slmn.patient_management.drug_structures.Prescription;
import com.slmn.patient_management.io.decoders.*;
import com.slmn.patient_management.notifications.*;
import com.slmn.patient_management.user_structures.*;
import com.slmn.patient_management.user_structures.requests.AccountRequest;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SystemDatabase {
    public ArrayList<Administrator> admins;
    public ArrayList<Secretary> secretaries;
    public ArrayList<Doctor> doctors;
    public ArrayList<Patient> patients;

    public ArrayList<Medicine> medicines;
    public ArrayList<Prescription> prescriptions;

    public LinkedTreeMap env;

    public ArrayList<AccountRequest> accountRequests;

    public ArrayList<Notification> specificUserNotifications;
    public ArrayList<Notification> userTypeNotifications;

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

        this.accountRequests = this.load("account_requests.json", new AccountRequestDecoder());

        this.env = this.loadObject("env.json");

        this.specificUserNotifications = this.load("user_notifications.json", new UserNotificationDecoder());
        this.userTypeNotifications = this.load("usertype_notifications.json", new UserTypeNotificationDecoder());
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

        this.write("account_requests.json", this.accountRequests, new AccountRequestDecoder());

        this.writeObject("env.json", this.env);


        this.write("user_notifications.json", this.specificUserNotifications,new UserNotificationDecoder());
        this.write("usertype_notifications.json", this.userTypeNotifications, new UserTypeNotificationDecoder());
    }

    private void write(String filename, ArrayList users, JSONClassDecoder decoderPlugin) {
        JSONArrayDecoder decoder = new JSONArrayDecoder(filename, decoderPlugin);
        decoder.encode(users);
    }

    private ArrayList load(String filename, JSONClassDecoder decoderPlugin) {
        JSONArrayDecoder decoder = new JSONArrayDecoder(filename, decoderPlugin);
        return decoder.decode();
    }

    private LinkedTreeMap loadObject(String filename) {
        JSONObjectFile file = new JSONObjectFile(filename);
        return file.readOrCreateEmpty();
    }
    private void writeObject(String filename, LinkedTreeMap items) {
        JSONObjectFile file = new JSONObjectFile(filename);
        file.write(items);
    }

    public int getEnvWithDefault(String key, int fallback) {
        if (!this.env.containsKey(key)) {
            return fallback;
        }
        try {
            return (int) Math.floor((double) this.env.get(key));
        } catch (NullPointerException e) {
            System.out.println(String.format("No value for key [%s]", key));
            return fallback;
        }
    }
    public String getEnvWithDefault(String key, String fallback) {
        if (!this.env.containsKey(key)) {
            System.out.println(String.format("No value for key [%s]", key));
            return fallback;
        }
        try {
            return (String) this.env.get(key);
        } catch (NullPointerException e) {
            System.out.println(String.format("No value (error) for key [%s]", key));
            return fallback;
        }
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

    // Polymorphism to handle .pushUser() to whichever collection is correct
    public void pushUser(Administrator user) { this.admins.add(user); };
    public void pushUser(Doctor user) { this.doctors.add(user); };
    public void pushUser(Secretary user) { this.secretaries.add(user); };
    public void pushUser(Patient user) { this.patients.add(user); };
    public void pushUser(User user) {
        if (user.isAdmin()) SystemDatabase.connect().admins.add((Administrator) user);
        if (user.isDoctor()) SystemDatabase.connect().doctors.add((Doctor) user);
        if (user.isPatient()) SystemDatabase.connect().patients.add((Patient) user);
        if (user.isSecretary()) SystemDatabase.connect().secretaries.add((Secretary) user);
    }
}
