package chess;

public class Rook extends Piece {
    public Rook(String color) {
        super(color, "R");
    }
    
    public boolean canMoveLikeKnight() { return false; }
    public boolean canMoveLikePawn() { return false; }    
    public int movesDiagonal() { return 0; }
    public int movesUp() { return 8; }
    public int movesDown() { return 8; }
    public int movesSide() { return 8; }
}
