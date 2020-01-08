package com.slmn.patient_management.gui.controllers;

import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.gui.views.main_menu.AdminMainMenuView;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.user_structures.User;

import javax.swing.*;

public class AuthController extends Controller {
    private String username;
    private String password;

    public AuthController() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
        if (username == null) this.username = "";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
        if (password == null) this.password = "";
    }

    private void showMessage(String message, String title, int type) {
        System.out.println(String.format("[Message] %s", message));
        JOptionPane.showMessageDialog(null, message, title, type);
    }

    private void showErrorMessage(String message) {
        this.showMessage(message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void attemptAuth() {
        User user = SystemDatabase.connect().getUser(this.getUsername());
        if (user == null) {
            this.showErrorMessage("No user with that user ID");
            return;
        }

        if (!user.passwordMatches(this.getPassword())) {
            this.showErrorMessage("Password doesn't match");
            return;
        }

        // finish auth
        Main.setAuthenticatedUser(user);

        if (Main.authenticatedUser.isAdmin()) Main.switchView(new AdminMainMenuView());
        if (Main.authenticatedUser.isDoctor()) System.out.println("Doctor logged in");
        if (Main.authenticatedUser.isSecretary()) System.out.println("Secretary logged in");
        if (Main.authenticatedUser.isPatient()) System.out.println("Patient logged in");

    }
}