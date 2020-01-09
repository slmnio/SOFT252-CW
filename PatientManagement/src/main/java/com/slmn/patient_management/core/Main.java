package com.slmn.patient_management.core;

import com.slmn.patient_management.models.drugs.Prescription;
import com.slmn.patient_management.views.structures.ViewWithFrame;
import com.slmn.patient_management.views.LoginView;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.users.*;
import com.slmn.patient_management.models.users.requests.AccountRequest;

import javax.swing.*;

public class Main {

    public static User authenticatedUser = null;

    public static void setAuthenticatedUser(User user) {
        Main.authenticatedUser = user;
        System.out.println(String.format("New user authenticated: %s", user.describe()));
    }

    public static JFrame frame = null;
    public static void switchView(ViewWithFrame frameForm) {
        JFrame newFrame = frameForm.getFrame();
        if (frame != null) frame.dispose();
        newFrame.setVisible(true);
        frame = newFrame;
    }

    public static void main(String[] args) {
        // Main entry point of the program
        SystemDatabase.connect();
        Main.test();
        Main.switchView(new LoginView());
    }
    public static void test() {

        /*
        AccountRequest r = new AccountDeletionRequest(SystemDatabase.connect().patients.get(0));
        SystemDatabase.connect().accountRequests.add(r);
        SystemDatabase.connect().writeAll();

         */

        for (AccountRequest request: SystemDatabase.connect().accountRequests) {
            System.out.println(String.format("Request (%s): %s", request.getType(), request.getPatient().describe()));
        }


        for (Prescription p: SystemDatabase.connect().prescriptions) {
            System.out.println(String.format("[Prescription for %s]: Take %sx %s (%s)", p.getPatient().getFullName(),p.getQuantity(), p.getMedicine().getName(), p.getDosage()));
        }

        // Database write tests
        /*
        Patient patient = new Patient("Stirling", "Davis", "40 Belgrave Avenue", "password", 28, "Male");
        System.out.println(patient.describe());
        SystemDatabase.connect().patients.add(patient);
        SystemDatabase.connect().writeAll();

        for (Patient p: SystemDatabase.connect().patients) {
            System.out.println(p.describe());
        }
         */
    }
}
