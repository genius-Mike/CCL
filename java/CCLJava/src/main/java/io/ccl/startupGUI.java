package io.ccl;

import javax.swing.*;
import java.awt.*;

import static io.ccl.GUI.flowpanelled;
import static io.ccl.Main.selectedDirectory;

public class startupGUI extends JPanel {
    private static GUI mainWindow;

    public startupGUI(GUI mainWindow) {
        this.mainWindow = mainWindow;

        setLayout(new BorderLayout());
        JLabel info = new JLabel("Select a directory to get started", SwingConstants.CENTER);
        info.setFont(new Font("Arial", Font.BOLD, 15));
        JButton go = new JButton("Choose Directory");

        add(info, BorderLayout.NORTH);
        add(flowpanelled(go), BorderLayout.CENTER);

        go.addActionListener(l -> {
            new directorySelector(mainWindow);
        });
    }

    public Dimension getPreferredSize(){
        return new Dimension(200, 200);
    }
}
