/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontEndGUI;

/**
 *
 * @author aliel
 */
import ChessCore.ChessGame;
import ChessCore.GameStatus;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameStatusGUI {

    // Assuming this method is part of your GUI class
    public void checkGameStatus(JFrame parent) {
        
        GameStatus gameStatus = ChessGame.getGameStatus();

        switch (gameStatus) {
            case WHITE_WON:
                showGameEndDialog(parent,"White player won!");
                break;
            case BLACK_WON:
                showGameEndDialog(parent,"Black player won!");
                break;
            case STALEMATE:
                showGameEndDialog(parent,"The game is a stalemate!");
                break;
            case INSUFFICIENT_MATERIAL:
                showGameEndDialog(parent,"The game ended due to insufficient material.");
                break;
            case IN_PROGRESS:
                // Game is still in progress, do nothing or handle accordingly
                break;
            default:
                // Handle other cases if needed
                break;
        }
    }

    public void showGameEndDialog(JFrame parent,String message) {
        JOptionPane.showMessageDialog(parent, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
}
