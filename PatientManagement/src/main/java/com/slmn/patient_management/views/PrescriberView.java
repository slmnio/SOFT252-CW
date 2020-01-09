package com.slmn.patient_management.views;

import com.slmn.patient_management.views.main_menu.DoctorMainMenu;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;

public class PrescriberView extends ViewWithFrame {
    private JPanel mainPanel;

    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Prescriber", this.mainPanel, new DoctorMainMenu());
    }
}
