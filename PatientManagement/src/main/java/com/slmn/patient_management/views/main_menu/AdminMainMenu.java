package com.slmn.patient_management.views.main_menu;

import com.slmn.patient_management.controllers.BasicRoutingController;
import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.views.structures.ClosableFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainMenu extends ViewWithFrame {


    private JLabel lblDescTitle;
    private JButton btnNavAccountCreator;
    private JButton btnNavDoctorRatings;
    private JPanel mainPanel;
    private JButton btnNavAccountManager;
    private JButton btnLogout;

    public AdminMainMenu() {
        BasicRoutingController controller = new BasicRoutingController();

        btnNavAccountCreator.addActionListener(e -> controller.routeToAccountCreator());
        btnNavAccountManager.addActionListener(e -> controller.routeToAdminAccountManager());
        btnNavDoctorRatings.addActionListener(e -> controller.routeToDoctorRatingAdmin());
        btnLogout.addActionListener(e -> Main.logout());
    }

    @Override
    public SwitchableFrame getFrame() {
        return new ClosableFrame("Main Menu", this.mainPanel);
    }
}
