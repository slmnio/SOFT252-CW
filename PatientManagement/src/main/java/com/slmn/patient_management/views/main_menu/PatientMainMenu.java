package com.slmn.patient_management.views.main_menu;

import com.slmn.patient_management.controllers.BasicRoutingController;
import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.views.structures.ClosableFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;

public class PatientMainMenu extends ViewWithFrame {

    private JPanel mainPanel;
    private JButton btnPrescriptions;

    public PatientMainMenu() {
        BasicRoutingController controller = new BasicRoutingController();
        btnPrescriptions.addActionListener(e -> controller.routeToPrescriptionViewer((Patient) Main.authenticatedUser, this));
    }

    @Override
    public SwitchableFrame getFrame() {
        return new ClosableFrame("Main Menu", this.mainPanel);
    }
}
