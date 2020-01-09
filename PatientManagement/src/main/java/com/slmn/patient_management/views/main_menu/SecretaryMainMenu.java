package com.slmn.patient_management.views.main_menu;

import com.slmn.patient_management.controllers.BasicRoutingController;
import com.slmn.patient_management.views.structures.ClosableFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;

public class SecretaryMainMenu extends ViewWithFrame {

    private JButton btnAccountQueue;
    private JPanel mainPanel;

    public SecretaryMainMenu() {
        BasicRoutingController controller = new BasicRoutingController();
        btnAccountQueue.addActionListener(e -> controller.routeToAccountQueue());
    }

    @Override
    public SwitchableFrame getFrame() {
        return new ClosableFrame("Main Menu", this.mainPanel);
    }
}
