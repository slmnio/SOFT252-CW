package com.slmn.patient_management.gui.views.main_menu;

import com.slmn.patient_management.gui.structures.SwitchableFrame;
import com.slmn.patient_management.gui.views.LoginView;

import javax.swing.*;

public class AdminMainMenu extends SwitchableFrame {


    private JLabel lblDescTitle;
    private JButton btnNavAccountCreator;
    private JButton btnNavDoctorRatings;
    private JPanel mainPanel;

    @Override
    public JFrame getFrame() {
        return SwitchableFrame.createFrame("Main Menu", new AdminMainMenu().mainPanel);
    }
}
