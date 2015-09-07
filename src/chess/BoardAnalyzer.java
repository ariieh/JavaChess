package chess;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class BoardAnalyzer {
    
    public BoardAnalyzer() {
        
    }
    
    public HashMap<String, Double> pointValueForBoardAndColor(Board board) {
        double whitePointValue = 0;
        double blackPointValue = 0;
        
        for (Map.Entry<Piece, ArrayList<Square>> entry : board.pieceLocations().entrySet()) {
            Piece piece = entry.getKey();
            ArrayList<Square> squares = entry.getValue();
            
            double value = squares.size() * piece.pointValue();
            
            if (piece.isWhite()) whitePointValue += value;
            else blackPointValue += value;
        }
        
        HashMap<String, Double> hash = new HashMap();
        hash.put("W", whitePointValue);
        hash.put("B", blackPointValue);
        return hash;
    }
}
