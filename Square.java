package chess;

public class Square {
    public Piece Piece;

    public Square(Piece p) {
        this.Piece = p;
    }
    
    public boolean isEmpty() { return this.Piece == null; }
    public boolean isOccupied() { return !isEmpty(); }
    public boolean isOccupiedByWhitePiece() {
        return isOccupied() && this.Piece.isWhite();
    }
    public boolean isOccupiedByBlackPiece() {
        return isOccupied() && this.Piece.isBlack();
    }

    public String toString() {
        return isEmpty() ? " " : Piece.toString();
    }
}
