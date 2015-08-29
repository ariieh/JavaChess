package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private Square[][] Board;
    private List<String> Columns =
        Arrays.asList("A","B","C","D","E","F","G","H");
    
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
    
    public boolean isValidMoveForColor(String from, String to, String color) {
        if (from.equals(to)) return false;
        
        Square fromSquare = getSquareAtCoordinates(from);
        if (fromSquare == null) return false;
        
        Square toSquare = getSquareAtCoordinates(to);
        if (toSquare == null) return false;

        if (!fromSquare.isOccupiedByPieceOfColor(color)) return false;
        if (toSquare.isOccupiedByPieceOfColor(color)) return false;

        return true;
        
    }
    
    public void move(String from, String to) {
        Square fromSquare = getSquareAtCoordinates(from);
        Square toSquare = getSquareAtCoordinates(to);
        
        toSquare.setPiece(fromSquare.empty());
    }
    
    private Square getSquareAtCoordinates(String coords) {
        int col = Columns.indexOf(coords.substring(0, 1));
        int row = 8 - Integer.parseInt(coords.substring(1, 2));

        if (col < 0 || col > 7 || row < 0 || row > 7) return null;
        
        return Board[row][col];
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
