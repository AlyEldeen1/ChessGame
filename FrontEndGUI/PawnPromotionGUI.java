/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontEndGUI;

import ChessCore.PawnPromotion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PawnPromotionGUI extends JDialog {
    private PawnPromotion selectedPromotion;

    public PawnPromotionGUI(JFrame parent) {
        super(parent, "Pawn Promotion", true);
        setSize(300, 150);
        setLayout(new GridLayout(1, 4));

        // Create buttons for promotion choices
        JButton knightButton = new JButton("Knight");
        JButton bishopButton = new JButton("Bishop");
        JButton rookButton = new JButton("Rook");
        JButton queenButton = new JButton("Queen");

        // Add action listeners to the buttons
        knightButton.addActionListener(new PromotionButtonListener(PawnPromotion.Knight));
        bishopButton.addActionListener(new PromotionButtonListener(PawnPromotion.Bishop));
        rookButton.addActionListener(new PromotionButtonListener(PawnPromotion.Rook));
        queenButton.addActionListener(new PromotionButtonListener(PawnPromotion.Queen));

        // Add buttons to the dialog
        add(knightButton);
        add(bishopButton);
        add(rookButton);
        add(queenButton);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public PawnPromotion getSelectedPromotion() {
        return selectedPromotion;
    }

    private class PromotionButtonListener implements ActionListener {
        private final PawnPromotion promotion;

        public PromotionButtonListener(PawnPromotion promotion) {
            this.promotion = promotion;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedPromotion = promotion;
            dispose(); // Close the dialog
        }
    }
}
