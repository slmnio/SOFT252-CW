package com.slmn.patient_management.gui.views;

import com.slmn.patient_management.gui.controllers.AuthController;
import com.slmn.patient_management.gui.structures.SwitchableFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends SwitchableFrame {
    private JPanel panel1;
    private JTextField txtUsername;
    private JLabel lblTitle;
    private JLabel lblDescUsername;
    private JButton btnLogin;
    private JButton btnRequestAccount;
    private JLabel lblDescPassword;
    private JTextField txtPassword;

    public LoginView() {
        AuthController authController = new AuthController();

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getData(authController);
                authController.attemptAuth();
            }
        });
    }

    public JFrame getFrame() {
        JFrame frame = new JFrame("LoginView");
        frame.setContentPane(new LoginView().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }


    public void setData(AuthController data) {
        txtUsername.setText(data.getUsername());
        txtPassword.setText(data.getPassword());
    }

    public void getData(AuthController data) {
        data.setUsername(txtUsername.getText());
        data.setPassword(txtPassword.getText());
    }

    public boolean isModified(AuthController data) {
        if (txtUsername.getText() != null ? !txtUsername.getText().equals(data.getUsername()) : data.getUsername() != null)
            return true;
        if (txtPassword.getText() != null ? !txtPassword.getText().equals(data.getPassword()) : data.getPassword() != null)
            return true;
        return false;
    }
}
