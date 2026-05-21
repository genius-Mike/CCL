package io.ccl;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GUI extends JFrame {
    public static final String title = "CCL v4.0";

    public GUI(){
        this.setTitle(title);
        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("C:\\Users\\koper\\OneDrive\\Pulpit\\CCL\\java\\CCLJava\\src\\main\\resources\\ccllogo.png");
        this.setIconImage(icon.getImage());

        Container pane = this.getContentPane();
        pane.setLayout(new BorderLayout());

        selectDirectoryScreen();



        this.setVisible(true);
    }

    private void selectDirectoryScreen(){
        Container pane = getContentPane();
        pane.add(new directorySelect(this), BorderLayout.CENTER);
    }

    public static JPanel flowpanelled(Component c){
        JPanel panel = new JPanel(); panel.add(c);
        return panel;
    }

}
