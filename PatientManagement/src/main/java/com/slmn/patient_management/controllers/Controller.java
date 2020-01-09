package com.slmn.patient_management.controllers;

import javax.swing.*;

public abstract class Controller {
    void showMessage(String message, String title, int type) {
        System.out.println(String.format("[Message] %s", message));
        JOptionPane.showMessageDialog(null, message, title, type);
    }

    void showErrorMessage(String message) {
        this.showMessage(message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    void showInfoMessage(String message, String title) { this.showMessage(message, title, JOptionPane.INFORMATION_MESSAGE); }

    boolean confirmMessage(String message, String title) {
        int res = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        return (res == JOptionPane.YES_OPTION);
    }
}
