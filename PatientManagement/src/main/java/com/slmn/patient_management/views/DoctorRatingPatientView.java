package com.slmn.patient_management.views;

import com.slmn.patient_management.controllers.PatientServicesController;
import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.users.Doctor;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.views.main_menu.PatientMainMenu;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DoctorRatingPatientView extends ViewWithFrame {
    private JPanel mainPanel;
    private JComboBox cbxDoctors;
    private JButton btnSubmit;
    private JTextField txtRating;
    private JTextField txtComment;

    public DoctorRatingPatientView() {
        fillBox();
        checkButton();

        PatientServicesController controller = new PatientServicesController();

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.rateDoctor(SystemDatabase.connect().doctors.get(cbxDoctors.getSelectedIndex()), txtRating.getText(), txtComment.getText());
                close();
            }
        });
        cbxDoctors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkButton();
            }
        });
        txtComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkButton();
            }
        });
        txtRating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkButton();
            }
        });
        txtComment.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkButton();
            }
        });
        txtRating.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkButton();
            }
        });
    }


    private void close() {
        this.getFrame().dispose();
        // cleans it for next time
    }

    private void checkButton() {
        System.out.println(String.format("Button checks: %s %s %s %s",(SystemDatabase.connect().doctors.size() > 0), !txtRating.getText().equals(""), !txtComment.getText().equals(""), cbxDoctors.getSelectedIndex() != -1));
        btnSubmit.setEnabled((SystemDatabase.connect().doctors.size() > 0) && !txtRating.getText().equals("") && !txtComment.getText().equals("") && cbxDoctors.getSelectedIndex() != -1);
    }

    private void fillBox() {
        cbxDoctors.removeAllItems();
        for(Doctor doctor: SystemDatabase.connect().doctors) {
            cbxDoctors.addItem(String.format("%s Dr %s", doctor.getID(),doctor.getFullName()));
        }
    }

    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Doctor Rating", this.mainPanel, new PatientMainMenu());
    }
}
