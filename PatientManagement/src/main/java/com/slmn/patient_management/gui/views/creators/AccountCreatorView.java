package com.slmn.patient_management.gui.views.creators;

import com.slmn.patient_management.gui.structures.SubFrame;
import com.slmn.patient_management.gui.structures.SwitchableFrame;
import com.slmn.patient_management.gui.structures.ViewWithFrame;
import com.slmn.patient_management.gui.views.main_menu.AdminMainMenuView;

import javax.swing.*;

public class AccountCreatorView extends ViewWithFrame {

    private JPanel mainPanel;

    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Account Creator", this.mainPanel, new AdminMainMenuView());
    }
}
