package com.slmn.patient_management.views;

import com.slmn.patient_management.controllers.PatientServicesController;
import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.patient_services.DoctorReport;
import com.slmn.patient_management.models.users.Doctor;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class DoctorRatingAdminView extends ViewWithFrame {
    private JPanel mainPanel;
    private JTable tableRating;
    private PatientServicesController controller;
    private boolean allowFeedbackEditing;
    private ViewWithFrame returnFrame;

    public DoctorRatingAdminView(boolean allowFeedbackEditing, ViewWithFrame returnFrame) {
        this.returnFrame = returnFrame;
        this.allowFeedbackEditing = allowFeedbackEditing;
        this.controller = new PatientServicesController();
        updateTable();
    }

    private ArrayList<DoctorReport> getReports() {
        if (allowFeedbackEditing) return SystemDatabase.connect().doctorReports;

        Doctor thisDoctor = (Doctor) Main.authenticatedUser;
        return thisDoctor.getDoctorReports();
    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel() {

            private String[] titles = new String[]{"Doctor", "Rating", "Comment", "Feedback"};

            @Override
            public boolean isCellEditable(int row, int column) {
                if (allowFeedbackEditing && column == 3) return true;
                return false;
            }

            @Override
            public String getColumnName(int column) {
                return this.titles[column];
            }
        };




        /*
        * Doctor | Rating | Comment | Feedback
        * */
        model.setColumnCount(4);
        model.setRowCount(getReports().size());

        for (int i = 0; i < getReports().size(); i++) {
            DoctorReport report = getReports().get(i);
            model.setValueAt(String.format("ID %s: Dr %s", report.getDoctor().getID(), report.getDoctor().getSurname()), i, 0);
            model.setValueAt(String.format("%d/10", report.getUserRating()), i, 1);
            model.setValueAt(report.getUserComment(), i, 2);
            model.setValueAt(report.getAdminFeedback(), i, 3);
        }

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                updateReport(getReports().get(row), (String) model.getValueAt(row, 3));
            }
        });

        tableRating.setModel(model);
        tableRating.getColumnModel().getColumn(0).setPreferredWidth(110);
        tableRating.getColumnModel().getColumn(1).setPreferredWidth(20);

        /*
        tableRating.setRowSorter(new TableRowSorter<>() {
            @Override
            public boolean isSortable(int column) {
                return false;
            }
        });

         */

    }

    private void updateReport(DoctorReport report, String adminComments) {
        if (allowFeedbackEditing) controller.addFeedback(report, adminComments);
    }

    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Doctor Rating", this.mainPanel, this.returnFrame);
    }
}
