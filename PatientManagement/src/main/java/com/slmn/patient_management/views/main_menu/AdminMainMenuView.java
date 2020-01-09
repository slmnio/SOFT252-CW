package com.slmn.patient_management.views.main_menu;

import com.slmn.patient_management.controllers.BasicRoutingController;
import com.slmn.patient_management.views.structures.ClosableFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainMenuView extends ViewWithFrame {


    private JLabel lblDescTitle;
    private JButton btnNavAccountCreator;
    private JButton btnNavDoctorRatings;
    private JPanel mainPanel;
    private JButton btnNavAccountManager;

    public AdminMainMenuView() {
        BasicRoutingController controller = new BasicRoutingController();

        btnNavAccountCreator.addActionListener(e -> controller.routeToAccountCreator());
        btnNavAccountManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.routeToAdminAccountManager();
            }
        });
    }

    @Override
    public SwitchableFrame getFrame() {
        return new ClosableFrame("Main Menu", this.mainPanel);
    }
}
