package io.ccl;

import javax.swing.*;

import java.io.File;

import static io.ccl.Main.selectedDirectory;

public class directorySelector extends JFileChooser {

    public directorySelector(GUI mainWindow){
        this.setCurrentDirectory(new File("."));
        this.setDialogTitle("Choose Directory");
        this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        this.setAcceptAllFileFilterUsed(false);
        //
        if (this.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedDirectory = this.getSelectedFile();
            mainWindow.buildClassScreen();
        }
        else {
            System.out.println("No Selection");
        }
    }

}
