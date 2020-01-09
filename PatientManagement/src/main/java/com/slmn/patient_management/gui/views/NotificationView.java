package com.slmn.patient_management.gui.views;

import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.gui.structures.SubFrame;
import com.slmn.patient_management.gui.structures.SwitchableFrame;
import com.slmn.patient_management.gui.structures.ViewWithFrame;
import com.slmn.patient_management.notifications.Notification;
import com.slmn.patient_management.notifications.NotificationHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NotificationView extends ViewWithFrame {
    private JPanel mainPanel;
    private JLabel txtDescTitle;
    private JList lstNotifications;
    private JButton btnDismissAll;
    private JButton btnContinue;
    private ViewWithFrame returnView;

    public NotificationView(ViewWithFrame returnView) {
        this.returnView = returnView;
        ArrayList<Notification> notifications = NotificationHandler.applicableNotifications(Main.authenticatedUser);

        DefaultListModel list = new DefaultListModel();
        for (Notification notification: notifications) {
            list.addElement(notification.getContent());
        }
        lstNotifications.setModel(list);
        btnDismissAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Destroy all notifications
                NotificationHandler.dismissAll(notifications);
                close();
            }
        });
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Continue without doing anything else
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
        return new SubFrame("Notifications", this.mainPanel, this.returnView);
    }
}
