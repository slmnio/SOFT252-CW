package com.slmn.patient_management.gui.views.creators;

import com.slmn.patient_management.gui.structures.SubFrame;
import com.slmn.patient_management.gui.structures.SwitchableFrame;
import com.slmn.patient_management.gui.structures.ViewWithFrame;
import com.slmn.patient_management.gui.views.main_menu.AdminMainMenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountCreatorView extends ViewWithFrame {

    private JPanel mainPanel;
    private JLabel lblDescTitle;
    private JRadioButton rbtnAdmin;
    private JRadioButton rbtnDoctor;
    private JRadioButton rbtnSecretary;
    private JTextField txtGivenName;
    private JTextField txtSurname;
    private JTextField txtAddress;
    private JTextField txtPassword;
    private JButton btnSubmit;
    private ButtonGroup btngpAccountType;

    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Account Creator", this.mainPanel, new AdminMainMenuView());
    }

    public AccountCreatorView() {
        rbtnAdmin.addActionListener(this.radioButtonListener());
        rbtnDoctor.addActionListener(this.radioButtonListener());
        rbtnSecretary.addActionListener(this.radioButtonListener());

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private ActionListener radioButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSubmit.setEnabled(true);
            }
        };
    }
}
