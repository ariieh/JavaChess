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
    
    public boolean containsPawnOnStartingRow() {
        return isOccupied() && this.Piece.Symbol.equals("P")
            &&    ((this.Piece.Color.equals("W") && this.Row == 6)
                || (this.Piece.Color.equals("B") && this.Row == 1));
    }
    
    public boolean equals(Object obj) {
        if (!(obj instanceof Square)) return false;
        Square otherSquare = (Square) obj;
        return this.Row == otherSquare.Row && this.Col == otherSquare.Col;
    }
}
