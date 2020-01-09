package com.slmn.patient_management.views.structures;

import javax.swing.*;

public class ClosableFrame extends SwitchableFrame {
    /*
     * Frame that can close the program when it's closed
     * */
    public ClosableFrame(String title, JPanel panel) {
        this.setTitle(title);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }
}
