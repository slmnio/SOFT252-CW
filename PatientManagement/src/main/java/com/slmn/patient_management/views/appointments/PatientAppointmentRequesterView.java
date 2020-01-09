package com.slmn.patient_management.views.appointments;

import com.slmn.patient_management.controllers.AppointmentController;
import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.appointments.Appointment;
import com.slmn.patient_management.models.appointments.TimeSlot;
import com.slmn.patient_management.models.users.Doctor;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.views.main_menu.PatientMainMenu;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientAppointmentRequesterView  extends ViewWithFrame  {
    private JPanel mainPanel;
    private JComboBox cbxDoctor;
    private JComboBox cbxTimeslot;
    private JButton btnRequest;
    private JComboBox cbxDay;
    private JComboBox cbxMonth;
    private JComboBox cbxYear;

    private Doctor doctor = null;
    private AppointmentController controller = new AppointmentController();

    private String zeropad(int input) {
        String string = String.valueOf(input);
        if (string.length() == 0) return "00";
        if (string.length() == 1) return "0" + string;
        if (string.length() == 2) return string;
        return string;
    }

    private int getYear() {
        return cbxYear.getSelectedIndex() + 2020;
    }
    private int getMonth() {
        return cbxMonth.getSelectedIndex() + 1;
    }
    private int getDay() {
        return cbxDay.getSelectedIndex() + 1;
    }

    private String getDate() {
        return String.format("%s-%s-%s", zeropad(getYear()), zeropad(getMonth()), zeropad(getDay()));
    }


    private void close() {
        this.getFrame().dispose();
        // cleans it for next time
    }



    public PatientAppointmentRequesterView() {
        loadBoxes();

        cbxDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctor = SystemDatabase.connect().doctors.get(cbxDoctor.getSelectedIndex());
                regenTimeslots();
            }
        });
        cbxTimeslot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.requestAppointment((Patient) Main.authenticatedUser, doctor, getDate(), controller.generateTimeSlots(getDate()).get(cbxTimeslot.getSelectedIndex()));
                close();
            }
        });
        cbxDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regenTimeslots();
            }
        });
        cbxMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regenTimeslots();
            }
        });
        cbxYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regenTimeslots();
            }
        });
    }

    public void regenTimeslots() {
        cbxTimeslot.removeAllItems();
        if (this.doctor == null) {
            for (TimeSlot timeslot: this.controller.generateTimeSlots(getDate())) {
                cbxTimeslot.addItem(String.format("%s", timeslot.toString()));
            }
        } else {
            for (TimeSlot timeslot : this.controller.generateOccupiedTimeSlots(getDate(), this.doctor)) {
                cbxTimeslot.addItem(String.format("%s: %s", timeslot.toString(), timeslot.isOccupied() ? "Taken" : "Free"));
            }
        }
    }
    public void loadBoxes() {
        cbxDay.removeAllItems();
        for (int i = 1; i <= 31; i++) {
            cbxDay.addItem(i);
        }
        cbxMonth.removeAllItems();
        for (int i = 1; i <= 12; i++) {
            cbxMonth.addItem(i);
        }
        cbxYear.removeAllItems();
        for (int i = 2020; i <= 2022; i++) {
            cbxYear.addItem(i);
        }


        cbxDoctor.removeAllItems();
        for (Doctor doctor: SystemDatabase.connect().doctors) {
            cbxDoctor.addItem(String.format("Dr %s (Avg: %s)", doctor.getFullName(), doctor.getAverageRating()));
        }

        this.doctor = SystemDatabase.connect().doctors.get(cbxDoctor.getSelectedIndex());

        regenTimeslots();
        //

    }

    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Appointment Requester", this.mainPanel, new PatientMainMenu());
    }
}
