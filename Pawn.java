package chess;

public class Pawn extends Piece {
    public Pawn(String color) {
        super(color, "P");
    }
    
    public boolean canMoveLikeKnight() { return false; }
    public boolean canMoveLikePawn() { return true; }    
    public int movesDiagonal() { return 0; }
    public int movesUp() { return this.Color.equals("W") ? 1 : 0; }
    public int movesDown() { return this.Color.equals("W") ? 0 : 1; }
    public int movesSide() { return 0; }
}
