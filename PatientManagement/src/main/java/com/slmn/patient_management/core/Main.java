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
        Main.switchView(new LoginView());
    }
}
