/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontEndGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class invalidmove extends JDialog {
    public invalidmove(JFrame parent) {
        super(parent, "Invalid Move", true);
        setSize(300, 100);
        setLayout(new BorderLayout());

        // Create a label with the error message
        JLabel errorMessage = new JLabel("Invalid Move!");
        errorMessage.setHorizontalAlignment(JLabel.CENTER);

        // Create an OK button to close the dialog
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });

        // Add components to the dialog
        add(errorMessage, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
