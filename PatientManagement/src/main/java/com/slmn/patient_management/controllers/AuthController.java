package com.slmn.patient_management.controllers;

import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.views.structures.ViewWithFrame;
import com.slmn.patient_management.views.NotificationView;
import com.slmn.patient_management.views.main_menu.AdminMainMenuView;
import com.slmn.patient_management.views.main_menu.SecretaryMainMenu;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.notifications.Notification;
import com.slmn.patient_management.models.users.User;

import java.util.ArrayList;

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

        ViewWithFrame next = null;

        if (Main.authenticatedUser.isAdmin()) next = (new AdminMainMenuView());
        if (Main.authenticatedUser.isDoctor()) System.out.println("Doctor logged in");
        if (Main.authenticatedUser.isSecretary()) next = (new SecretaryMainMenu());
        if (Main.authenticatedUser.isPatient()) System.out.println("Patient logged in");


        ArrayList<Notification> notifications = MedicineMain.authenticatedUser);

        if (notifications.size() > 0) {
            Main.switchView(new NotificationView(next));
        } else {
            Main.switchView(next);
        }

    }
}