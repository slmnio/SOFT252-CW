package com.slmn.patient_management.views.creators;

import com.slmn.patient_management.controllers.AccountController;
import com.slmn.patient_management.views.structures.SubFrame;
import com.slmn.patient_management.views.structures.SwitchableFrame;
import com.slmn.patient_management.views.structures.ViewWithFrame;
import com.slmn.patient_management.views.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountRequestCreateView extends ViewWithFrame {

    private JPanel mainPanel;
    private JLabel lblDescTitle;
    private JTextField txtGivenName;
    private JTextField txtSurname;
    private JTextField txtAddress;
    private JTextField txtPassword;
    private JButton btnSubmit;
    private JTextField txtSex;
    private JTextField txtAge;

    private static SwitchableFrame frame;

    @Override
    public SwitchableFrame getFrame() {
        if (frame == null) frame = new SubFrame("Account Requester", this.mainPanel, new LoginView());
        return frame;
    }

    public AccountRequestCreateView() {
        AccountController controller = new AccountController();

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String code, String givenName, String surname, String address, String password
                controller.requestPatient(txtGivenName.getText(), txtSurname.getText(), txtAddress.getText(), txtPassword.getText(), txtAge.getText(),txtSex.getText());
                close();
            }
        });
    }
    private void close() {
        this.getFrame().dispose();
        // cleans it for next time
        frame = null;
    }
}
