/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FrontEndGUI;

import ChessCore.ChessGame;
import ChessCore.ClassicBoardInitializer;
import ChessCore.ClassicChessGame;
import ChessCore.*;
import ChessCore.Pieces.Pawn;
import ChessCore.Pieces.Piece;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.JPanel;

/**
 *
 * @author aliel
 */
public class ChessBoard extends javax.swing.JFrame {

    private ChessGame chessGame;
    private final JButton[][] squares;
    private Square firstClick;
    private boolean isFirstClick = true;
    private final JButton undoButton;

    public ChessBoard() {
        super("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        // setLayout(new GridLayout(8, 8))
        chessGame = new ClassicChessGame();
        squares = new JButton[8][8];
        setLayout(new BorderLayout());
        // undo button

        undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // chessGame.undoMove();
                System.out.println("clicked");
                  // updateBoard();
                updateBoardUndo();
            }
        });
        add(undoButton, BorderLayout.SOUTH);
        // Create a panel with GridLayout for the chessboard
        JPanel panel = new JPanel(new GridLayout(8, 8));
        add(panel, BorderLayout.CENTER);
        // door meen
        JPanel panel2= new JPanel();
        
        initializeBoard(panel);

        setVisible(true);
    }

    private void initializeBoard(JPanel panel) {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new JButton();
                squares[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);

                // Add buttons to the panel
                panel.add(squares[row][col]);
                final int currentRow = row;
                final int currentCol = col;

                squares[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Square clicked = new Square(BoardFile.values()[currentCol], BoardRank.values()[currentRow]);
                        if (isFirstClick) {
                            firstClick = clicked;
                            isFirstClick = false;
                            // call hna function el highlight
                            hightlightSquare();

                        } else {
                            Square secondClick = clicked;
                            isFirstClick = true;
                            handleSquareClick(firstClick, secondClick);
                            clearHighlightSquare();
                            hightlightKing();
                        }
                    }
                });
            }
        }
        updateBoard();
    }

    private void hightlightKing() {
        Square whiteking = Utilities.getKingSquare(Player.WHITE, chessGame.getBoard());
        Square blackking = Utilities.getKingSquare(Player.BLACK, chessGame.getBoard());
        if (Utilities.isInCheck(Player.WHITE, chessGame.getBoard())) {
            int col = whiteking.getFile().ordinal();
            int row = whiteking.getRank().ordinal();
            squares[row][col].setBackground(Color.RED);
        } else if (Utilities.isInCheck(Player.BLACK, chessGame.getBoard())) {

            int row = blackking.getRank().ordinal();
            int col = blackking.getFile().ordinal();
            System.out.println("King in check");
            squares[row][col].setBackground(Color.RED);

        }
    }

    private void hightlightSquare() {
        List<Square> possibleMoves = chessGame.getAllValidMovesFromSquare(firstClick);
        // Highlight the squares corresponding to the possible moves
        for (Square move : possibleMoves) {
            int row = move.getRank().ordinal();
            int col = move.getFile().ordinal();
            squares[row][col].setBackground(Color.YELLOW); // Set your desired highlight color

        }

    }

    private void clearHighlightSquare() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
            }
        }
    }

    private void handleSquareClick(Square clickedSquare, Square SelectedSquare) {
        // Handle square click event here
//        List<Square> possibleMoves = chessGame.getAllValidMovesFromSquare(clickedSquare);
       
        Move move = new Move(clickedSquare, SelectedSquare);
        System.out.println("Clicked on square: " + clickedSquare.getFile() + clickedSquare.getRank());

//        if (chessGame.isValidMove(move)) 
        if (chessGame.getPieceAtSquare(clickedSquare) instanceof Pawn
                && ((SelectedSquare.getRank() == BoardRank.EIGHTH) || SelectedSquare.getRank() == BoardRank.FIRST)) {

            System.out.println("x");
            PawnPromotionGUI promotionDialog = new PawnPromotionGUI(this); // Assuming 'this' is your main JFrame
            PawnPromotion selectedPromotion = promotionDialog.getSelectedPromotion();
            move = new Move(clickedSquare, SelectedSquare, selectedPromotion);
        }
        if (chessGame.makeMove(move)) {
             chessGame.boardStates.push(this.chessGame.getBoard());
            updateBoard();
            if (chessGame.isGameEnded()) {
                GameStatusGUI end = new GameStatusGUI();
                end.checkGameStatus(this);
            }
        }
        else {
            // Handle invalid move
            new invalidmove(this);// make a pop up that says that

        }

    }

    private void updateBoardUndo() {

        if (!chessGame.boardStates.isEmpty()) {
//            System.out.println("Stack not empty");
//
//            ChessGame undo = new ClassicChessGame();
////            ChessCore.ChessBoard undo = chessGame.boardStates.peek();
////            ChessBoard undo = new ChessBoard(chessGame.boardStates.peek());
//            ChessCore.ChessBoard undoboard = chessGame.boardStates.peek();
//            undo.setBoard(undoboard);
//            System.out.println("undo applied");
            ChessCore.ChessBoard undoboard = chessGame.boardStates.pop();
            chessGame.setBoard(undoboard);  // Update the current game state in ChessGame
            chessGame.updateGameStatus();
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    PieceGUI piece = convertToChessPiece(chessGame.getPieceAtSquare(new Square(BoardFile.values()[col], BoardRank.values()[row])));
                    assignImageToSquare(squares[row][col], piece);
                    hightlightKing();
//                    System.out.println("loop");

                }
            }
            System.out.println("undo update");
        }

    }

    private void updateBoard() {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                PieceGUI piece = convertToChessPiece(chessGame.getPieceAtSquare(new Square(BoardFile.values()[col], BoardRank.values()[row])));
                assignImageToSquare(squares[row][col], piece);
                hightlightKing();

            }
        }
//        chessGame.boardStates.push(this.chessGame.getBoard());
        System.out.println("update board normal");
    }

    private void assignImageToSquare(JButton square, PieceGUI piece) {
        square.setIcon(null);  // Clear the icon

        if (piece != null) {
            String color = piece.getColor();
            String type = piece.getType();

            // Replace this path with the actual path to your image files
            String imagePath = "C:\\Users\\aliel\\Downloads\\ChessImages\\" + color + type + ".png";
            int scaledWidth = 55;  // Replace with your desired width
            int scaledHeight = 55;  // Replace with your desired height

            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            square.setIcon(scaledIcon);

        }
    }

    private PieceGUI convertToChessPiece(Piece backendPiece) {
        if (backendPiece == null) {
            return null;
        }

        // Map backend piece to GUI ChessPiece
        String color = backendPiece.getOwner() == Player.WHITE ? "white" : "black";
        String type = backendPiece.getClass().getSimpleName().toLowerCase();
        return new PieceGUI(color, type, backendPiece.getOwner());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        SwingUtilities.invokeLater(() -> new ChessBoard());

    }

}

//        
//        JFrame frame = new JFrame("Chess Board");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600, 600);
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(8, 8));
//        frame.add(panel);
//
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                JPanel square = new JPanel();
//                if ((i + j) % 2 == 0) {
//                    square.setBackground(Color.WHITE);
//                } else {
//                    square.setBackground(Color.BLACK);
//                }
//                panel.add(square);
//            }
//        }
//
//        frame.setVisible(true);
//    }
//}
//
//     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

