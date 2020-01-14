package com.slmn.patient_management.core;

import com.slmn.patient_management.controllers.AppointmentController;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.appointments.Appointment;
import com.slmn.patient_management.models.appointments.AppointmentRequest;
import com.slmn.patient_management.models.appointments.SurgeryDay;
import com.slmn.patient_management.models.drugs.Prescription;
import com.slmn.patient_management.models.users.User;
import com.slmn.patient_management.models.users.requests.AccountRequest;
import com.slmn.patient_management.views.LoginView;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;

public class Main {

    public static User authenticatedUser = null;

    public static void setAuthenticatedUser(User user) {
        Main.authenticatedUser = user;
        System.out.println(String.format("New user authenticated: %s", user.describe()));
    }

    public static void logout() {
        Main.authenticatedUser = null;
        System.out.println("User logged out, returning to login");
        Main.switchView(new LoginView());
    }

    public static JFrame frame = null;

    public static void switchView(ViewWithFrame frameForm) {
        JFrame newFrame = frameForm.getFrame();
        if (frame != null) frame.dispose();
        newFrame.setVisible(true);
        frame = newFrame;
    }

    public static void popupFrame(ViewWithFrame frameForm) {
        // No destruction
        JFrame newFrame = frameForm.getFrame();
        newFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Main entry point of the program
        SystemDatabase.connect();
        Main.test();
        Main.switchView(new LoginView());
    }

    public static void test() {

        //SurgeryDay day = new SurgeryDay();
        //day.generateEmpty();

        /*
        AccountRequest r = new AccountDeletionRequest(SystemDatabase.connect().patients.get(0));
        SystemDatabase.connect().accountRequests.add(r);
        SystemDatabase.connect().writeAll();

         */
/*
        AppointmentController controller = new AppointmentController();

        Appointment a = controller.createAppo
        AppointmentRequest ar = new AppointmentRequest(a);
        */


        for (AppointmentRequest request : SystemDatabase.connect().appointmentRequests) {
            System.out.println(request);
            System.out.println(request.getAppointment());
        }


        for (Prescription p : SystemDatabase.connect().prescriptions) {
            System.out.println(String.format("[Prescription for %s]: Take %sx %s (%s)", p.getPatient().getFullName(), p.getQuantity(), p.getMedicine().getName(), p.getDosage()));
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
