package chess;

public class Bishop extends Piece {
    public Bishop(String color) {
        super(color, "B");
    }
    
    public boolean canMoveLikeKnight() { return false; }
    public boolean canMoveLikePawn() { return false; }    
    public int movesDiagonal() { return 8; }
    public int movesUp() { return 0; }
    public int movesDown() { return 0; }
    public int movesSide() { return 0; }
    
    public double pointValue() { return 3.25; };
}
