package com.slmn.patient_management.gui.views.main_menu;

import com.slmn.patient_management.gui.controllers.BasicRoutingController;
import com.slmn.patient_management.gui.structures.ClosableFrame;
import com.slmn.patient_management.gui.structures.SwitchableFrame;
import com.slmn.patient_management.gui.structures.ViewWithFrame;

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
