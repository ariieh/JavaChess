package chess;

public class Square {
    private Piece Piece;

    public Square(Piece p) {
        this.Piece = p;
    }
    
    public Piece getPiece() { return this.Piece; }
    public void setPiece(Piece p) { this.Piece = p; }
    
    public boolean isEmpty() { return this.Piece == null; }
    public boolean isOccupied() { return !isEmpty(); }
    
    public Piece empty() {
        Piece oldPiece = this.Piece;
        setPiece(null);
        return oldPiece;
    }
    
    public boolean isOccupiedByPieceOfColor(String color) {
        return isOccupied() && this.Piece.isColor(color);
    }

    public String toString() {
        return isEmpty() ? " " : Piece.toString();
    }
}
