package SequentialFileRenamer;

import SequentialFileRenamer.RenameUtils.RenameFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenderWindow extends JFrame {

    private JTextField directoryField;
    private JTextField searchField;
    private JTextField newNamePrefixField;
    private JTextArea logArea;

    public RenderWindow() {
        setTitle("File Renamer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Directory Path:"));
        directoryField = new JTextField();
        inputPanel.add(directoryField);

        inputPanel.add(new JLabel("Search String:"));
        searchField = new JTextField();
        inputPanel.add(searchField);

        inputPanel.add(new JLabel("New Name Prefix:"));
        newNamePrefixField = new JTextField();
        inputPanel.add(newNamePrefixField);

        JButton renameButton = new JButton("Rename Files");
        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RenameFiles.renameFiles(directoryField.getText(), searchField.getText(), newNamePrefixField.getText(), logArea);
                } catch (Exception ex) {
                    RenameFiles.logMessage(logArea, "Error: " + ex.getMessage(), Color.RED);
                }
            }
        });
        inputPanel.add(renameButton);

        add(inputPanel, BorderLayout.NORTH);

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RenderWindow().setVisible(true);
            }
        });
    }
}
