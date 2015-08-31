package chess;

public class King extends Piece {
    public King(String color) {
        super(color, "K");
    }
    
    public boolean canMoveLikeKnight() { return false; }
    public boolean canMoveLikePawn() { return false; }    
    public int movesDiagonal() { return 1; }
    public int movesUp() { return 1; }
    public int movesDown() { return 1; }
    public int movesSide() { return 1; }

}
