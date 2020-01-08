package com.slmn.patient_management.gui.controllers;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.user_structures.Administrator;
import com.slmn.patient_management.user_structures.User;

import javax.swing.*;

public class AccountCreatorController extends Controller {

    public User createUser(String className, String givenName, String surname, String address, String password) {
        String code = className.substring(0,1);
        User user = User.createAppropriateUser(code, givenName, surname, address, password);
        System.out.println(String.format("Created user: %s", user.describe()));

        // Using polymorphism to push the user to the correct collection
        SystemDatabase.connect().pushUser(user);
        SystemDatabase.connect().writeAll();

        this.showMessage(String.format("[ID: %s] %s has been created.", user.getID(), user.getFullName()), String.format("New %s created", className), JOptionPane.PLAIN_MESSAGE);
        return user;
    }
}
