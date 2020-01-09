package com.slmn.patient_management.views;

import com.slmn.patient_management.controllers.AccountController;
import com.slmn.patient_management.controllers.DrugController;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.drugs.Prescription;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.views.main_menu.SecretaryMainMenu;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientManagerView extends ViewWithFrame {
    private JPanel mainPanel;
    private JComboBox cbxPatients;
    private JButton btnDelete;

    public PatientManagerView() {
        fillCombo();
        AccountController controller = new AccountController();
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.queryRemoveUser(SystemDatabase.connect().patients.get(cbxPatients.getSelectedIndex()));
                fillCombo();
            }
        });
    }
    private void fillCombo() {
        btnDelete.setEnabled(SystemDatabase.connect().patients.size() > 0);
        cbxPatients.removeAllItems();
        for (Patient patient: SystemDatabase.connect().patients) {
            cbxPatients.addItem(String.format("[ID %s] %s", patient.getID(), patient.getFullName()));
        }
    }

    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Prescription Dispenser", this.mainPanel, new SecretaryMainMenu());
    }
}
