package com.slmn.patient_management.views.creators;

import com.slmn.patient_management.controllers.AccountController;
import com.slmn.patient_management.controllers.AppointmentController;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.appointments.AppointmentRequest;
import com.slmn.patient_management.models.users.requests.AccountRequest;
import com.slmn.patient_management.views.main_menu.SecretaryMainMenu;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppointmentQueueView extends ViewWithFrame {
    private JPanel mainPanel;
    private JComboBox cbxQueue;
    private JButton btnApprove;
    private JButton btnDecline;
    private AppointmentController controller = new AppointmentController();
    private AppointmentRequest request;

    public AppointmentQueueView() {
        updateBox();

        btnApprove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.approveAppointmentRequest(request);
                updateBox();
            }
        });
        btnDecline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.declineAppointmentRequest(request);
                updateBox();
            }
        });

        cbxQueue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRequest();
            }
        });
    }

    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Appointment Queue", this.mainPanel, new SecretaryMainMenu());
    }

    private void updateRequest() {
        if (cbxQueue.getSelectedIndex() == -1) {
            request = null;
            btnDecline.setEnabled(false);
            btnApprove.setEnabled(false);
        } else {
            request = SystemDatabase.connect().appointmentRequests.get(cbxQueue.getSelectedIndex());
            btnDecline.setEnabled(true);
            btnApprove.setEnabled(true);
        }
    }

    public void updateBox() {
        cbxQueue.removeAllItems();
        ArrayList<AppointmentRequest> requests = SystemDatabase.connect().appointmentRequests;
        for (AppointmentRequest request : requests) {
            cbxQueue.addItem(String.format("%s with Dr %s [%s at %s]", request.getAppointment().getPatient().getFullName(), request.getAppointment().getDoctor().getSurname(), request.getAppointment().getDate(), request.getAppointment().getTimeSlot()));
        }
        updateRequest();
    }
}

