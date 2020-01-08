package com.slmn.patient_management.gui.views.main_menu;

import com.slmn.patient_management.gui.controllers.BasicRoutingController;
import com.slmn.patient_management.gui.structures.ClosableFrame;
import com.slmn.patient_management.gui.structures.SwitchableFrame;
import com.slmn.patient_management.gui.structures.ViewWithFrame;
import com.slmn.patient_management.gui.views.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public JFrame getFrame() {
        return new ClosableFrame("Main Menu", this.mainPanel);
    }
}
