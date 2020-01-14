package com.slmn.patient_management.views.main_menu;

import com.slmn.patient_management.controllers.BasicRoutingController;
import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.users.Doctor;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.views.structures.ClosableFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorMainMenu extends ViewWithFrame {

    private JButton btnPrescribe;
    private JPanel mainPanel;
    private JButton btnViewRecords;
    private JComboBox cbxPatients;
    private JButton btnCreateMed;
    private JButton btnViewFeedback;
    private JButton btnViewAppointments;
    private JButton btnLogout;
    private JButton btnRequestAppointment;

    public DoctorMainMenu() {
        update();
        BasicRoutingController controller = new BasicRoutingController();
        btnPrescribe.addActionListener(e -> controller.routeToPrescriber());
        btnViewRecords.addActionListener(e -> controller.routeToRecordsDoctorView(SystemDatabase.connect().patients.get(cbxPatients.getSelectedIndex())));
        btnCreateMed.addActionListener(e -> controller.routeToMedicineCreator());
        btnViewFeedback.addActionListener(e -> controller.routeToDoctorRatingDoctor());
        btnViewAppointments.addActionListener(e -> controller.routeToAppointmentView((Doctor) Main.authenticatedUser));
        btnLogout.addActionListener(e -> Main.logout());

        btnRequestAppointment.addActionListener(e -> controller.routeToDoctorAppointmentRequester());
    }

    private void update() {
        cbxPatients.removeAllItems();
        btnViewRecords.setEnabled(SystemDatabase.connect().patients.size() > 0);
        for (Patient patient: SystemDatabase.connect().patients) {
            cbxPatients.addItem(String.format("[ID %s] %s", patient.getID(), patient.getFullName()));
        }
    }

    @Override
    public SwitchableFrame getFrame() {
        return new ClosableFrame("Main Menu", this.mainPanel);
    }
}
