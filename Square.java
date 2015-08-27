package chess;

public class Square {
    public Piece Piece;

    public Square(Piece p) {
        this.Piece = p;
    }
    
    public boolean isEmpty() { return this.Piece == null; }
    
    public String toString() {
        return isEmpty() ? " " : Piece.toString();
    }
}
