package com.slmn.patient_management.views.structures;

import com.slmn.patient_management.core.Main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PopupFrame extends SwitchableFrame {
    private String title;
    private JPanel panel;
    private ViewWithFrame returnView;
    /*
     * Frame that should return to another frame when it's closed
     * */

    public PopupFrame(String title, JPanel panel) {
        this.title = title;
        this.panel = panel;
        this.setTitle(title);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
    }
}
