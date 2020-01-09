package com.slmn.patient_management.views;

import com.slmn.patient_management.controllers.DrugController;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.drugs.Prescription;
import com.slmn.patient_management.views.main_menu.SecretaryMainMenu;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrescriptionDispenserView extends ViewWithFrame {
    private JPanel mainPanel;
    private JComboBox cbxPrescriptions;
    private JButton btnDispense;
    private JTextField txtMedicine;
    private JTextField txtQuantity;
    private JTextField txtPatient;
    private JTextField txtDosage;

    private Prescription prescription;

    public PrescriptionDispenserView() {
        fillCombo();
        updateBoxes();
        cbxPrescriptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBoxes();
            }
        });
        DrugController controller = new DrugController();
        btnDispense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleDispensePrescription(prescription);
                fillCombo();
                updateBoxes();
            }
        });
    }
    private void fillCombo() {
        cbxPrescriptions.removeAllItems();
        for (Prescription prescription: SystemDatabase.connect().prescriptions) {
            cbxPrescriptions.addItem(String.format("[ID %s] %s: %s", prescription.getPatient().getID(), prescription.getPatient().getSurname().toUpperCase(), prescription.getMedicine()));
        }
    }

    private void updateBoxes() {
        if (cbxPrescriptions.getSelectedIndex() == -1) {
            this.prescription = null;
            txtMedicine.setText("");
            txtPatient.setText("");
            txtQuantity.setText("");
            txtDosage.setText("");
            return;
        }
        this.prescription = SystemDatabase.connect().prescriptions.get(cbxPrescriptions.getSelectedIndex());
        txtMedicine.setText(prescription.getMedicine().getName());
        txtPatient.setText(prescription.getPatient().getFullName());
        txtQuantity.setText(String.valueOf(prescription.getQuantity()));
        txtDosage.setText(prescription.getDosage());
    }

    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Prescription Dispenser", this.mainPanel, new SecretaryMainMenu());
    }
}
