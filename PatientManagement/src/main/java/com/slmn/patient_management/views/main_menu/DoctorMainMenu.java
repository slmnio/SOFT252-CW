package com.slmn.patient_management.views.main_menu;

import com.slmn.patient_management.controllers.BasicRoutingController;
import com.slmn.patient_management.views.structures.ClosableFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;

public class DoctorMainMenu extends ViewWithFrame {

    private JButton btnPrescribe;
    private JPanel mainPanel;

    public DoctorMainMenu() {
        BasicRoutingController controller = new BasicRoutingController();
        btnPrescribe.addActionListener(e -> controller.routeToPrescriber());
    }

    @Override
    public SwitchableFrame getFrame() {
        return new ClosableFrame("Main Menu", this.mainPanel);
    }
}
