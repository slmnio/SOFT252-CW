package com.slmn.patient_management.gui.structures;

import com.slmn.patient_management.core.Main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SubFrame extends SwitchableFrame {
    /*
    * Frame that should return to another frame when it's closed
    * */

    public SubFrame(String title, JPanel panel, ViewWithFrame returnView) {
        this.setTitle(title);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                Main.switchView(returnView);
            }
        });
    }




    public static JFrame createFrame(String title, JPanel panel) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        return frame;
    }
}
