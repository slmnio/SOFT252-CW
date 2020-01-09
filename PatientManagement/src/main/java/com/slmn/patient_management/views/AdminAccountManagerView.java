package com.slmn.patient_management.views;

import com.slmn.patient_management.controllers.AccountController;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.users.User;
import com.slmn.patient_management.views.main_menu.AdminMainMenu;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminAccountManagerView extends ViewWithFrame {
    private JPanel mainPanel;
    private JComboBox cbxAccounts;
    private JButton btnDelete;

    public AdminAccountManagerView() {
        AccountController controller = new AccountController();
        updateBox();
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                User user = getUsers().get(cbxAccounts.getSelectedIndex());
                int res = JOptionPane.showConfirmDialog(null, String.format("Are you sure you want to delete\n%s (ID: %s) %s", user.getClass().getSimpleName(), user.getID(), user.getFullName()), "Confirm deletion", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    controller.removeUser(user);
                    updateBox();
                }

            }
        });
    }

    private ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(SystemDatabase.connect().doctors);
        users.addAll(SystemDatabase.connect().secretaries);
        return users;
    }

    public void updateBox() {
        cbxAccounts.removeAllItems();

        for (User user : getUsers()) {
            cbxAccounts.addItem(String.format("[%s] ID %s %s", user.getClass().getSimpleName(), user.getID(), user.getFullName()));
        }
    }

    @Override
    public SwitchableFrame getFrame() {
        return new SubFrame("Account Manager", this.mainPanel, new AdminMainMenu());
    }
}
