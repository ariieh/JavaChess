package chess;

public abstract class Piece {
    public final String Color;
    public final String Symbol;

    public Piece(String color, String symbol) {
        this.Color = color;
        this.Symbol = symbol;
    }
    
    public boolean isColor(String color) {
        return this.Color.equals(color);
    }
    
    public String toString() {
        return this.Symbol;
    }
    
    public boolean isWhite() { return this.Color.equals("W"); }
    public boolean isBlack() { return this.Color.equals("B"); }
    
    public String oppositeColor() {
        return this.isWhite() ? "B" : "W";
    }
    
    public abstract boolean canMoveLikeKnight();
    public abstract boolean canMoveLikePawn();
    
    public abstract int movesDiagonal();
    public abstract int movesUp();
    public abstract int movesDown();
    public abstract int movesSide();
    
    public abstract double pointValue();
}
