package chess;

public class Square {
    private Piece Piece;
    public int Row;
    public int Col;

    public Square(Piece p, int row, int col) {
        this.Piece = p;
        this.Row = row;
        this.Col = col;
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
    public boolean equals(Square other) {
        return this.Row == other.Row && this.Col == other.Col;
    }
}
