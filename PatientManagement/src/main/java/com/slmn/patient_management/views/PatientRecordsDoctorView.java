package com.slmn.patient_management.views;

import com.slmn.patient_management.controllers.PatientServicesController;
import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.models.patient_services.PatientRecord;
import com.slmn.patient_management.models.users.Doctor;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.views.main_menu.DoctorMainMenu;
import com.slmn.patient_management.views.main_menu.PatientMainMenu;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PatientRecordsDoctorView extends ViewWithFrame {
    private JPanel mainPanel;
    private JTable tblRecords;
    private JLabel lblDescTitle;
    private JButton btnSubmitNote;
    private JTextField txtRecord;
    private Patient patient;

    public PatientRecordsDoctorView(Patient patient) {
        this.patient = patient;
        updateTable();
        lblDescTitle.setText(String.format("%s %s - Patient Records", patient.getID(), patient.getFullName()));
        btnSubmitNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientServicesController psc = new PatientServicesController();
                psc.createPatientRecord(patient, txtRecord.getText());
                updateTable();
            }
        });
        txtRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSubmitNote.setEnabled(!txtRecord.getText().equals(""));
            }
        });
    }
    public ArrayList<PatientRecord> getRecords() {
        return (this.patient).getPatientRecords();
    }

    public void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnCount(1);
        model.setRowCount(getRecords().size());

        for (int i = 0; i < getRecords().size(); i++) {
            PatientRecord record = getRecords().get(i);
            model.setValueAt(record.getContent(), i, 0);
        }

        tblRecords.setModel(model);
        txtRecord.setText("");
    }

    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Patient records", this.mainPanel, new DoctorMainMenu());
    }
}
