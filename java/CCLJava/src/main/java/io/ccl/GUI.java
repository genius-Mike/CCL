package io.ccl;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public static final String title = "CCL v4.0";
    private Container pane = getContentPane();

    public GUI(){
        this.setTitle(title);
        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("C:\\Users\\koper\\OneDrive\\Pulpit\\CCL\\java\\CCLJava\\src\\main\\resources\\ccllogo.png");
        this.setIconImage(icon.getImage());

        pane.setLayout(new BorderLayout());

        selectDirectoryScreen();



        this.setVisible(true);
    }

    public void selectDirectoryScreen(){
        pane.removeAll();
        pane.add(new startupGUI(this), BorderLayout.CENTER);
    }

    public void buildClassScreen(){
        pane.removeAll();
        pane.add(new compilerGUI(this), BorderLayout.CENTER);
    }

    public static JPanel flowpanelled(Component c){
        JPanel panel = new JPanel(); panel.add(c);
        return panel;
    }

}
