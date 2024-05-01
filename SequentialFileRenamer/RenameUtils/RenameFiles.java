package SequentialFileRenamer.RenameUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RenameFiles {

    public static void renameFiles(String directoryPath, String searchString, String newNamePrefix, JTextArea logArea) throws Exception {
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new Exception("Invalid directory path!");
        }

        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            throw new Exception("No files found in the directory!");
        }

        int fileCount = 0;
        for (File file : files) {
            if (file.isFile() && file.getName().contains(searchString)) {
                String extension = getFileExtension(file);
                String newName = newNamePrefix + fileCount + extension;
                File renamedFile = new File(directory, newName);
                if (file.renameTo(renamedFile)) {
                    logMessage(logArea, "Renamed: " + file.getName() + " to " + newName, Color.BLUE);
                    fileCount++;
                } else {
                    logMessage(logArea, "Failed to rename: " + file.getName(), Color.RED);
                }
            }
        }

        logMessage(logArea, "Total files renamed: " + fileCount, Color.GREEN);
    }

    public static void logMessage(JTextArea logArea, String message, Color color) {
        logArea.append(message + "\n");
        logArea.setForeground(color);
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOfDot = name.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return "";
        }
        return name.substring(lastIndexOfDot);
    }
}
