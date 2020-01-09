package com.slmn.patient_management.views;

import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.models.patient_services.PatientRecord;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.views.main_menu.PatientMainMenu;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PatientRecordsSelfView extends ViewWithFrame {
    private JPanel mainPanel;
    private JTable tblRecords;

    public PatientRecordsSelfView() {
        updateTable();
    }
    public ArrayList<PatientRecord> getRecords() {
        return ((Patient) Main.authenticatedUser).getPatientRecords();
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
    }

    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Your records", this.mainPanel, new PatientMainMenu());
    }
}
