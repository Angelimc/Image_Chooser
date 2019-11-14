package com.mycompany.imagej;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;

/**
 * An ImageJ plugin for opening one or multiple images from a specified directory.
 *
 */
public class Image_Chooser implements PlugIn {

    @Override
    public void run(String arg) {
        openImageFile();
    }

    private void openImageFile() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                JFileChooser jfc = new JFileChooser();
                // Enable ability to open multiple files
                jfc.setMultiSelectionEnabled(true);
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File[] selectedFiles = jfc.getSelectedFiles();
                    Arrays.asList(selectedFiles).forEach(selectedFile -> {
                        ImagePlus image = IJ.openImage(selectedFile.getAbsolutePath());
                        if (image != null) image.show();
                    });
                }

            }
        };
        SwingUtilities.invokeLater(r);
    }

}
