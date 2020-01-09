package com.slmn.patient_management.views;

import com.slmn.patient_management.controllers.DrugController;
import com.slmn.patient_management.views.main_menu.AdminMainMenu;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MedicineCreatorView extends ViewWithFrame {
    private JPanel mainPanel;
    private JTextField txtName;
    private JButton btnCreate;

    public MedicineCreatorView() {
        DrugController controller = new DrugController();

        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                btnCreate.setEnabled(!txtName.getText().equals(""));
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.createMedicine(txtName.getText());
                close();
            }
        });
    }

    private void close() {
        this.getFrame().dispose();
        // cleans it for next time
    }
    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Medicine Creator", this.mainPanel, new AdminMainMenu());
    }
}
