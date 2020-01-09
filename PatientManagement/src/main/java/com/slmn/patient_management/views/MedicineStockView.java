package com.slmn.patient_management.views;

import com.slmn.patient_management.controllers.DrugController;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.drugs.Medicine;
import com.slmn.patient_management.views.main_menu.SecretaryMainMenu;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicineStockView extends ViewWithFrame {
    private JPanel mainPanel;
    private JComboBox cbxMedicines;
    private JButton btnStock;

    public MedicineStockView() {
        update();
        DrugController controller = new DrugController();
        btnStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Medicine med = SystemDatabase.connect().medicines.get(cbxMedicines.getSelectedIndex());
                controller.stockMedicine(med, JOptionPane.showInputDialog(String.format("How much do you want to re-stock %s?", med.getName())));
                update();
            }
        });
    }

    public void update() {
        cbxMedicines.removeAllItems();
        for (Medicine medicine : SystemDatabase.connect().medicines) {
            cbxMedicines.addItem(String.format("%s (%d in stock)", medicine.getName(), medicine.getStockCount()));
        }
    }

    @Override
    public SwitchableFrame getFrame() {
        return  new SubFrame("Medicine Stockroom", this.mainPanel, new SecretaryMainMenu());
    }
}
