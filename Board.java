package chess;

import java.util.ArrayList;

public class Board {
    private Square[][] Board;
    
    public Board() {
        Board = new Square[8][8];
        String c = "B";
        
        for (int i = 0; i < 8; i++) {
            if (i > 5) c = "W";

            if (i == 0 || i == 7) {
                Board[i] = new Square[] {
                    new Square(new Rook(c)),
                    new Square(new Knight(c)),
                    new Square(new Bishop(c)),
                    new Square(new Queen(c)),
                    new Square(new King(c)),
                    new Square(new Bishop(c)),
                    new Square(new Knight(c)),
                    new Square(new Rook(c))
                };
            } else if (i == 1 || i == 6) {
                for (int j = 0; j < 8; j++)
                    Board[i][j] = new Square(new Pawn(c));
            } else {
                for (int j = 0; j < 8; j++)
                    Board[i][j] = new Square(null);
            }
        }
    }
    
    public String toString() {
        String board = "";
        String horizontalLabel = "  A B C D E F G H\n";
        for (int i = 0; i < 8; i++) {
            if (i == 0) board += horizontalLabel;
            for (int j = 0; j < 8; j++) {
                if (j == 0) board += Integer.toString(8 - i);
                Square square = Board[i][j];
                board += "|";
                if (square.isOccupiedByPieceOfColor("W")) board += "\033[31;1m";
                board += square.toString();
                if (square.isOccupiedByPieceOfColor("W")) board += "\033[0m";
                if (j == 7) board += "|" + Integer.toString(8 - i) + "\n";
            }
            if (i == 7) board += horizontalLabel;
        }
        return board;
    }
}
