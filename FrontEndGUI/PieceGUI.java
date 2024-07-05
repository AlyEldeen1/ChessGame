/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontEndGUI;

import ChessCore.ChessBoard;
import ChessCore.ChessGame;
import ChessCore.ClassicBoardInitializer;
import ChessCore.Move;
import ChessCore.Pieces.Piece;
import ChessCore.Player;
import ChessCore.Square;
import javax.swing.ImageIcon;

/**
 *
 * @author aliel
 */
public class PieceGUI extends Piece {
    
    String type;
    String color;

    public PieceGUI(String color, String type, Player owner) {
        super(owner);
        this.color = color;
        this.type = type;
    }
    
     
    @Override
    public boolean isValidMove(Move move, ChessGame game) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String getColor() {
            return color;
    }

    String getType() {
        return type;
    }
}

