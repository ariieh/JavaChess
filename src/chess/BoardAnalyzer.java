package chess;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class BoardAnalyzer {
    
    public BoardAnalyzer() {
        
    }
    
    public HashMap<String, Double> pointValueForBoard(Board board) {
        double whitePointValue = 0;
        double blackPointValue = 0;
                
        for (Map.Entry<Piece, ArrayList<Square>> entry : board.WhitePieceLocations.entrySet())
            whitePointValue += entry.getValue().size() * entry.getKey().pointValue();
        for (Map.Entry<Piece, ArrayList<Square>> entry : board.BlackPieceLocations.entrySet())
            blackPointValue += entry.getValue().size() * entry.getKey().pointValue();
        
        HashMap<String, Double> hash = new HashMap();
        hash.put("W", whitePointValue);
        hash.put("B", blackPointValue);
        return hash;
    }
    
}
