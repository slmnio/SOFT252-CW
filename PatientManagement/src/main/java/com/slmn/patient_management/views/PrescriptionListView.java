package com.slmn.patient_management.views;

import com.slmn.patient_management.controllers.DrugController;
import com.slmn.patient_management.models.drugs.Prescription;
import com.slmn.patient_management.models.notifications.Notification;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;

public class PrescriptionListView extends ViewWithFrame {
    private JPanel mainPanel;
    private JList listPrescriptions;
    private ViewWithFrame returnView;
    private Patient user;
    private DrugController controller;

    public PrescriptionListView(Patient user, ViewWithFrame returnView) {
        this.controller = new DrugController();
        this.user = user;
        this.returnView = returnView;
        loadList();
    }

    public void loadList() {
        DefaultListModel list = new DefaultListModel();
        for (Prescription prescription: controller.getPrescriptions(this.user)) {
            list.addElement(String.format("%dx %s: %s", prescription.getQuantity(), prescription.getMedicine().getName(), prescription.getDosage()));
        }
        listPrescriptions.setModel(list);
    }


    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Prescription List", this.mainPanel, this.returnView);
    }
}
