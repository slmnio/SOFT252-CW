package com.slmn.patient_management.gui.views;

import com.slmn.patient_management.gui.controllers.AuthController;
import com.slmn.patient_management.gui.controllers.BasicRoutingController;
import com.slmn.patient_management.gui.structures.ClosableFrame;
import com.slmn.patient_management.gui.structures.SwitchableFrame;
import com.slmn.patient_management.gui.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginView extends ViewWithFrame {
    private JPanel mainPanel;
    private JTextField txtUsername;
    private JLabel lblTitle;
    private JLabel lblDescUsername;
    private JButton btnLogin;
    private JButton btnRequestAccount;
    private JLabel lblDescPassword;
    private JPasswordField txtPassword;

    public LoginView() {
        AuthController authController = new AuthController();

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitForm(authController);
            }
        });
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    submitForm(authController);
                }
            }
        });
        txtUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    submitForm(authController);
                }
            }
        });

        BasicRoutingController router = new BasicRoutingController();
        btnRequestAccount.addActionListener(e -> router.routeToAccountRequester());
    }

    private void submitForm(AuthController authController) {
        getData(authController);
        authController.attemptAuth();
    }


    @Override
    public SwitchableFrame getFrame() {
        return new ClosableFrame("Login", this.mainPanel);
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
