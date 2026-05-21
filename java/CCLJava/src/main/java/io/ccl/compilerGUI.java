package io.ccl;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static io.ccl.GUI.flowpanelled;
import static io.ccl.Main.selectedDirectory;

public class compilerGUI extends JPanel {
    private static GUI mainWindow;
    private static Font font = new Font("Arial", Font.PLAIN, 18);
    private static Font smallfont = new Font("Arial", Font.PLAIN, 14);

    public compilerGUI(GUI mainWindow){
        this.mainWindow = mainWindow;
        mainWindow.setSize(700, 500);
        mainWindow.setLocationRelativeTo(null);

        setLayout(new BorderLayout(0, 20));

        JPanel topPanel = new JPanel(new BorderLayout());

        //JPanel directoryPanel = new JPanel(new GridLayout(1, 1));
        JLabel directory = new JLabel("Current Directory: " + selectedDirectory.toString()); directory.setFont(smallfont);
        // JButton changeDir = new JButton("Change"); changeDir.setFont(smallfont);
        // directoryPanel.add(new JPanel());
        // directoryPanel.add(flowpanelled(directory)); // directoryPanel.add(flowpanelled(changeDir));
        // directoryPanel.add(new JPanel());

        JPanel namePanel = new JPanel(new GridLayout(1, 3));
        JTextField name = new JTextField(); name.setFont(font);
        JLabel nameLabel = new JLabel("Class: ", SwingConstants.RIGHT); nameLabel.setFont(font);
        namePanel.add(nameLabel); namePanel.add(name); namePanel.add(new JPanel());

        topPanel.add(flowpanelled(directory), BorderLayout.NORTH);
        topPanel.add(namePanel, BorderLayout.CENTER);

        JPanel attributePanel = new JPanel(new BorderLayout());
        JTextPane attributes = new JTextPane(); attributes.setFont(font);
        JToolTip tip = new JToolTip(); tip.setTipText("Use UML notation to write your attributes"); tip.setComponent(attributes);
        JLabel attributeLabel = new JLabel("Attributes", SwingConstants.CENTER); attributeLabel.setFont(font);
        attributePanel.add(attributeLabel, BorderLayout.NORTH); attributePanel.add(attributes, BorderLayout.CENTER);

        JButton docs = new JButton("Documentation");
        JButton compile = new JButton("Compile Java Class");
        JPanel btns = new JPanel(); btns.add(docs); btns.add(compile);
        docs.setFont(font);
        compile.setFont(font);

        add(topPanel, BorderLayout.NORTH);
        add(attributePanel, BorderLayout.CENTER);
        add(btns, BorderLayout.SOUTH);

//        System.out.println();
//        File f = new File(selectedDirectory.toString() + "/esempio.java");
//        f.
    }
}
