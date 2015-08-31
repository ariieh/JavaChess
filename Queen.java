package chess;

public class Queen extends Piece {
    public Queen(String color) {
        super(color, "Q");
    }
    
    public boolean canMoveLikeKnight() { return false; }
    public boolean canMoveLikePawn() { return false; }    
    public int movesDiagonal() { return 8; }
    public int movesUp() { return 8; }
    public int movesDown() { return 8; }
    public int movesSide() { return 8; }
}
