package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Board {
    // Board as multidimensional array of squares that contain pieces
    private Square[][] Board;
    
    // Convienence hash map for storing pieces (e.g. white pawn, black king) and their locations
    public HashMap<Piece, ArrayList<Square>> WhitePieceLocations = new HashMap();
    public HashMap<Piece, ArrayList<Square>> BlackPieceLocations = new HashMap();
    
    private List<String> Columns =
        Arrays.asList("A","B","C","D","E","F","G","H");
    
    public Board() {
        Board = new Square[8][8];
        String c = "B";
        
        for (int i = 0; i < 8; i++) {
            if (i > 5) c = "W";

            if (i == 0 || i == 7) {
                Square[] squares = new Square[] {
                    new Square(new Rook(c), i, 0),
                    new Square(new Knight(c), i, 1),
                    new Square(new Bishop(c), i, 2),
                    new Square(new Queen(c), i, 3),
                    new Square(new King(c), i, 4),
                    new Square(new Bishop(c), i, 5),
                    new Square(new Knight(c), i, 6),
                    new Square(new Rook(c), i, 7)
                };
                Board[i] = squares;
                for (int k = 0; k < squares.length; k++)
                    addPieceAndSquareToPieceLocations(squares[k].getPiece(), squares[k]);
            } else if (i == 1 || i == 6) {
                for (int j = 0; j < 8; j++) {
                    Piece piece = new Pawn(c);
                    Square square = new Square(piece, i, j);
                    Board[i][j] = square;
                    addPieceAndSquareToPieceLocations(piece, square);
                }
            } else {
                for (int j = 0; j < 8; j++)
                    Board[i][j] = new Square(null, i, j);
            }
        }
    }
    
    public boolean isValidMoveForColor(String from, String to, String color) {
        // TODO: castling, en passant
        if (from.equals(to)) return false;

        Square fromSquare = getSquareAtCoordinates(from);
        if (fromSquare == null) return false;

        Square toSquare = getSquareAtCoordinates(to);
        if (toSquare == null) return false;

        if (!fromSquare.isOccupiedByPieceOfColor(color)) return false;
        if (toSquare.isOccupiedByPieceOfColor(color)) return false;

        if (validMovesForPieceOnSquare(fromSquare).contains(toSquare)) return true;
        else return false;
    }
    
    public void move(String from, String to) {
        Square fromSquare = getSquareAtCoordinates(from);
        Square toSquare = getSquareAtCoordinates(to);
        Piece pieceToMove = fromSquare.empty();
        Piece pieceToRemove = toSquare.empty();
        
        if (pieceToMove == null) throw new IllegalArgumentException("No piece at " + from);
        
        addPieceAndSquareToPieceLocations(pieceToMove, toSquare);
        removePieceAndSquareFromPieceLocations(pieceToMove, fromSquare);
        removePieceAndSquareFromPieceLocations(pieceToRemove, toSquare);
        toSquare.setPiece(pieceToMove);
    }
        
    public boolean isColorInCheckmate(String color) {
        // TODO get isValid to consider check
        if (color.equals("W")) {
            Square whiteKingSquare = WhitePieceLocations.get(new King("W")).get(0);
            if (validMovesForPieceOnSquare(whiteKingSquare).isEmpty() && isColorInCheck("W")) return true;
        } else {
            Square blackKingSquare = BlackPieceLocations.get(new King("B")).get(0);
            if (validMovesForPieceOnSquare(blackKingSquare).isEmpty() && isColorInCheck("B")) return true;            
        }
        return false;
    }
    
    public boolean isColorInCheck(String color) {
        boolean colorIsWhite = color.equals("W");
        String attackingColor = colorIsWhite ? "B" : "W";
        HashMap<Piece, ArrayList<Square>> EnemyPieceLocations =
                colorIsWhite ? BlackPieceLocations : WhitePieceLocations;
        
        for (Map.Entry<Piece, ArrayList<Square>> entry : EnemyPieceLocations.entrySet()) {
            Piece piece = entry.getKey();
            ArrayList<Square> squares = entry.getValue();
            
            for (Square s : squares) {
                for (Square move : validMovesForPieceOnSquareExCheck(s))
                    if (move.containsKingOfColor(color)) return true;
            }
        }
        
        return false;
    }
    
    private Square getSquareAtCoordinates(String coords) {
        int col = Columns.indexOf(coords.substring(0, 1));
        int row = 8 - Integer.parseInt(coords.substring(1, 2));

        if (!rowAndColAreWithinBounds(row, col)) return null;
        
        return Board[row][col];
    }
    
    private boolean rowAndColAreWithinBounds(int row, int col) {
        return col >= 0 && col <= 7 && row >= 0 && row <= 7;
    }
    
    private void removePieceAndSquareFromPieceLocations(Piece piece, Square squareToRemove) {
        if (piece == null) return;
        HashMap<Piece, ArrayList<Square>> PieceLocations =
                piece.isWhite() ? WhitePieceLocations : BlackPieceLocations;
        ArrayList<Square> squares = PieceLocations.get(piece);
        if (squares == null) return;
        Iterator<Square> i = squares.iterator();
        while (i.hasNext()) {
            Square s = i.next();
            if (s.equals(squareToRemove)) {
                i.remove();
                break;
            }
        }
    }
    
    private void addPieceAndSquareToPieceLocations(Piece piece, Square squareToAdd) {
        HashMap<Piece, ArrayList<Square>> PieceLocations =
                piece.isWhite() ? WhitePieceLocations : BlackPieceLocations;
        ArrayList<Square> pieceLocations = PieceLocations.get(piece);
        if (pieceLocations == null) pieceLocations = new ArrayList<Square>();
        pieceLocations.add(squareToAdd);
        PieceLocations.put(piece, pieceLocations);
    }
    
    public List<Square> validMovesForPieceOnSquare(Square square) {
        Piece piece = square.getPiece();
        if (piece == null) return null;

        List<Square> validMovesForSquare = validMovesForPieceOnSquareExCheck(square);
        Iterator<Square> i = validMovesForSquare.iterator();
        while (i.hasNext()) {
            Square s = i.next();
            this.move(square.location(), s.location());
            if (this.isColorInCheck(piece.Color)) i.remove();
            this.move(s.location(), square.location());
        }
        return validMovesForSquare;
    }

    public List<Square> validMovesForPieceOnSquareExCheck(Square square) {
        Piece piece = square.getPiece();
        List<Square> validMoves = new ArrayList<Square>();
        
        // Special moves the piece is a pawn or a knight
        if (piece.canMoveLikePawn()) return validMovesForPawn(square);
        if (piece.canMoveLikeKnight()) return validMovesForKnight(square);
        
        // Add all normal directional moves
        validMoves.addAll(validMovesForSquareInDirection(square, piece.movesUp(), 1, 0, 0, 0));
        validMoves.addAll(validMovesForSquareInDirection(square, piece.movesSide(), 0, 1, 0, 0));
        validMoves.addAll(validMovesForSquareInDirection(square, piece.movesDown(), 0, 0, 1, 0));
        validMoves.addAll(validMovesForSquareInDirection(square, piece.movesSide(), 0, 0, 0, 1));
        validMoves.addAll(validMovesForSquareInDirection(square, piece.movesDiagonal(), 1, 1, 0, 0));
        validMoves.addAll(validMovesForSquareInDirection(square, piece.movesDiagonal(), 1, 0, 0, 1));
        validMoves.addAll(validMovesForSquareInDirection(square, piece.movesDiagonal(), 0, 1, 1, 0));
        validMoves.addAll(validMovesForSquareInDirection(square, piece.movesDiagonal(), 0, 0, 1, 1));
        
        return validMoves;
    }
    
    private List<Square> validMovesForSquareInDirection(Square square, int length, int up, int right, int down, int left) {
        
        List<Square> validMoves = new ArrayList<Square>();
        for (int i = 1; i <= length; i++) {
            if (up != 0) up = i;
            if (down != 0) down = i;
            if (right != 0) right = i;
            if (left != 0) left = i;

            int row = square.Row - up + down;
            int col = square.Col + right - left;
            
            if (!rowAndColAreWithinBounds(row, col)) break;
            Square s = Board[row][col];
            
            if (s.isOccupied()) {
                if (!s.isOccupiedByPieceOfColor(square.Piece.Color)) validMoves.add(s);
                break;
            } else { validMoves.add(s); }
        }
        return validMoves;
    }
    
    private List<Square> validMovesForKnight(Square square) {
        List<Square> validMoves = new ArrayList<Square>();
        
        // Up two, right one. Up two, left one.
        // Down two, right one. Down two, left one.
        // Right two, up one. Right two, down one.
        // Left two, up one. Left two, down one.        
        
        int rowColPairs[][] = {
            {-2,1}, {-2,-1},
            {2,1}, {2,-1},
            {-1, 2}, {1, 2},
            {-1, -2}, {1, -2}
        };

        for (int i = 0; i < rowColPairs.length; i++) {
            int row = square.Row + rowColPairs[i][0];
            int col = square.Col + rowColPairs[i][1];
            if (rowAndColAreWithinBounds(row, col)) {
                Square s = Board[row][col];
                if (!s.isOccupiedByPieceOfColor(square.getPiece().Color))
                    validMoves.add(s);
            }
        }

        return validMoves;
    }
    
    public List<Square> validMovesForPawn(Square square) {
        List<Square> validMoves = new ArrayList<Square>();
        
        // Regular moves, 1 or 2 up or down depending on color
        Piece piece = square.getPiece();
        if (piece.isWhite()) {
            int movesUp = piece.movesUp();
            if (square.containsPawnOnStartingRow()) movesUp += 1;
            validMoves.addAll(validMovesForSquareInDirection(square, movesUp, 1, 0, 0, 0));   
        } else {
            int movesDown = piece.movesDown();
            if (square.containsPawnOnStartingRow()) movesDown += 1;
            validMoves.addAll(validMovesForSquareInDirection(square, movesDown, 0, 0, 1, 0));   
        }

        // Attacking move
        int rowColPairs[][] = { {-1,-1}, {-1,1} };
        for (int i = 0; i < rowColPairs.length; i++) {
            int row = square.Row + rowColPairs[i][0];
            int col = square.Col + rowColPairs[i][1];
            if (rowAndColAreWithinBounds(row, col)) {
                Square s = Board[row][col];
                if (s.isOccupiedByPieceOfColor(square.getPiece().oppositeColor()))
                    validMoves.add(s);
            }
        }

        return validMoves;
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
