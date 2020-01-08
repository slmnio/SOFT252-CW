package com.slmn.patient_management.gui.structures;

import com.slmn.patient_management.gui.views.main_menu.AdminMainMenu;

import javax.swing.*;

public abstract class SwitchableFrame {
    public abstract JFrame getFrame();
    public static JFrame createFrame(String title, JPanel panel) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
}
