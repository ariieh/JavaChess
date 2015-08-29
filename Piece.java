package chess;

public class Piece {
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
}
