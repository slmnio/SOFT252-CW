package com.slmn.patient_management.core;

import com.slmn.patient_management.drug_structures.Medicine;
import com.slmn.patient_management.drug_structures.Prescription;
import com.slmn.patient_management.gui.structures.SwitchableFrame;
import com.slmn.patient_management.gui.views.LoginView;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.user_structures.*;

import javax.swing.*;

public class Main {

    public static User authenticatedUser = null;

    public static void setAuthenticatedUser(User user) {
        Main.authenticatedUser = user;
        System.out.println(String.format("New user authenticated: %s", user.describe()));
    }

    public static JFrame frame = null;
    public static void switchFrame(SwitchableFrame frameForm) {
        JFrame newFrame = frameForm.getFrame();
        if (frame != null) frame.dispose();
        newFrame.setVisible(true);
        frame = newFrame;
    }

    public static void main(String[] args) {
        // Main entry point of the program
        SystemDatabase.connect();
        //Main.test();
        Main.switchFrame(new LoginView());
    }
    public static void test() {

        for (User user: SystemDatabase.connect().doctors) {
            System.out.println(user.describe());
        }

        for (Prescription p: SystemDatabase.connect().prescriptions) {
            System.out.println(String.format("[Prescription for %s]: Take %sx %s (%s)", p.getPatient().getFullName(),p.getQuantity(), p.getMedicine().getName(), p.getDosage()));
        }

        // Database write tests

        Patient patient = SystemDatabase.connect().patients.get(0);
        Medicine medicine = new Medicine("Pentadusplan", 20);

        SystemDatabase.connect().medicines.add(medicine);

        Prescription prescription = patient.createPrescription(medicine, 2, "Use sparingly when needed");

        SystemDatabase.connect().prescriptions.add(prescription);
        SystemDatabase.connect().writeAll();
    }
}
