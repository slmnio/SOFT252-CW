package com.slmn.patient_management.views;

import com.slmn.patient_management.controllers.DrugController;
import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.appointments.Appointment;
import com.slmn.patient_management.models.drugs.Medicine;
import com.slmn.patient_management.models.users.Doctor;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.views.main_menu.DoctorMainMenu;
import com.slmn.patient_management.views.structures.PopupFrame;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrescriberView extends ViewWithFrame {
    private JPanel mainPanel;
    private JComboBox cbxPatients;
    private JComboBox cbxMedicines;
    private JTextField txtQuantity;
    private JTextField txtDosage;
    private JButton btnSubmit;
    private Appointment activeAppointment = null;

    public PrescriberView(Appointment activeAppointment) {
        // Code alters when an appointment is given
        this();
        // Boxes & controller will have been initialised

        this.activeAppointment = activeAppointment;
        for (int i = 0; i < getPatients().size(); i++) {
            Patient patient = getPatients().get(i);
            if (activeAppointment.getPatient().equals(patient)) {
                // Get the patient from the appointment & lock it to that patient
                cbxPatients.setSelectedIndex(i);
                cbxPatients.setEnabled(false);
            }
        }

    }

    public PrescriberView() {
        DrugController controller = new DrugController();
        updateBoxes();
        btnSubmit.addActionListener(e -> {
            controller.createPrescription(getPatients().get(cbxPatients.getSelectedIndex()), getMedicines().get(cbxMedicines.getSelectedIndex()), txtQuantity.getText(), txtDosage.getText());
            close();
        });
    }
    private ArrayList<Patient> getPatients() {
        return SystemDatabase.connect().patients;
    }
    private ArrayList<Medicine> getMedicines() {
        return SystemDatabase.connect().medicines;
    }
    private void close() {
        this.getFrame().dispose();
        // cleans it for next time
    }

    private void updateBoxes() {
        cbxPatients.removeAllItems();
        for (Patient patient: getPatients()) {
            cbxPatients.addItem(String.format("[ID %s] %s", patient.getID(), patient.getFullName()));
        }

        cbxMedicines.removeAllItems();
        for (Medicine medicine: getMedicines()) {
            cbxMedicines.addItem(medicine.getName());
        }
    }

    @Override
    public SwitchableFrame getFrame() {
        if (this.activeAppointment == null) {
            return new SubFrame("Prescriber", this.mainPanel, new DoctorMainMenu());
        } else {
            return new PopupFrame("Prescriber", this.mainPanel);
        }
    }
}
