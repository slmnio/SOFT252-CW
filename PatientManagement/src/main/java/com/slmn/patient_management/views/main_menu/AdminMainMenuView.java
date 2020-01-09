package com.slmn.patient_management.views.main_menu;

import com.slmn.patient_management.controllers.BasicRoutingController;
import com.slmn.patient_management.views.structures.ClosableFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;

public class AdminMainMenuView extends ViewWithFrame {


    private JLabel lblDescTitle;
    private JButton btnNavAccountCreator;
    private JButton btnNavDoctorRatings;
    private JPanel mainPanel;

    public AdminMainMenuView() {
        BasicRoutingController controller = new BasicRoutingController();

        btnNavAccountCreator.addActionListener(e -> controller.routeToAccountCreator());
    }

    @Override
    public SwitchableFrame getFrame() {
        return new ClosableFrame("Main Menu", this.mainPanel);
    }
}
