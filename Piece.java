package chess;

public class Piece {
    public final String Color;
    public final String Symbol;

    public Piece(String color, String symbol) {
        this.Color = color;
        this.Symbol = symbol;
    }
    
    public boolean isWhite() { return this.Color.equals("W"); }
    public boolean isBlack() { return this.Color.equals("B"); }
    
    public String toString() {
        return this.Symbol;
    }
}
