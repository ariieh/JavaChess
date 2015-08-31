package chess;

public class Knight extends Piece {
    public Knight(String color) {
        super(color, "N");
    }
    
    public boolean canMoveLikeKnight() { return true; }
    public boolean canMoveLikePawn() { return false; }    
    public int movesDiagonal() { return 0; }
    public int movesUp() { return 0; }
    public int movesDown() { return 0; }
    public int movesSide() { return 0; }

}
