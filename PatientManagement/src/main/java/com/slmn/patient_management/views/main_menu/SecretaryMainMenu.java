package com.slmn.patient_management.views.main_menu;

import com.slmn.patient_management.controllers.BasicRoutingController;
import com.slmn.patient_management.views.structures.ClosableFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecretaryMainMenu extends ViewWithFrame {

    private JButton btnAccountQueue;
    private JPanel mainPanel;
    private JButton btnNavDispense;
    private JButton btnNavMedStock;
    private JButton btnAccountManager;

    public SecretaryMainMenu() {
        BasicRoutingController controller = new BasicRoutingController();
        btnAccountQueue.addActionListener(e -> controller.routeToAccountQueue());
        btnNavDispense.addActionListener(e -> controller.routeToPrescriptionDispenser());
        btnNavMedStock.addActionListener(e -> controller.routeToMedicineStock());
        btnAccountManager.addActionListener(e -> controller.routeToSecretaryAccountManager());
    }

    @Override
    public SwitchableFrame getFrame() {
        return new ClosableFrame("Main Menu", this.mainPanel);
    }
}
