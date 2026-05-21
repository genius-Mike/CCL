package io.ccl;

import javax.swing.*;
import java.awt.*;

import static io.ccl.GUI.flowpanelled;
import static io.ccl.Main.selectedDirectory;

public class directorySelect extends JPanel {

    private JFileChooser chooser;
    private String choosertitle;
    private static GUI mainWindow;

    public directorySelect(GUI mainWindow) {
        this.mainWindow = mainWindow;

        setLayout(new BorderLayout());
        JLabel info = new JLabel("Select a directory to get started", SwingConstants.CENTER);
        info.setFont(new Font("Arial", Font.BOLD, 15));
        JButton go = new JButton("Choose Directory");

        add(info, BorderLayout.NORTH);
        add(flowpanelled(go), BorderLayout.CENTER);

        go.addActionListener(l -> {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle(choosertitle);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //
            // disable the "All files" option.
            //
            chooser.setAcceptAllFileFilterUsed(false);
            //
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                selectedDirectory = chooser.getSelectedFile();
            }
            else {
                System.out.println("No Selection");
            }
        });
    }

    public Dimension getPreferredSize(){
        return new Dimension(200, 200);
    }
}
