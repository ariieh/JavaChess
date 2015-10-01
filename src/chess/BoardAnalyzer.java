package chess;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class BoardAnalyzer {
    
    public static String bestMoveForColor(Board board, String color) {
        return moveWithMinimumPointValueForColor(board, color);
    }
    
    public static HashMap<String, Double> pointValueForBoard(Board board) {
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
    
    private static String moveWithMinimumPointValueForColor(Board board, String color) {
        HashMap<Piece, ArrayList<Square>> PieceLocations =
                color.equals("W") ? board.WhitePieceLocations : board.BlackPieceLocations;
        String opposingColor = color.equals("W") ? "B" : "W";
        double minPointValue = pointValueForBoard(board).get(opposingColor);
        
        HashMap<Double, ArrayList<String>> pointValuesAndSquares = new HashMap();
        
        for (Map.Entry<Piece, ArrayList<Square>> entry : PieceLocations.entrySet()) {
            // For every piece of the color on the board in question
            for (Square fromSquare : new ArrayList<Square>(entry.getValue())) {
                // Iterate over every possible move
                for (Square toSquare : board.validMovesForPieceOnSquare(fromSquare)) {
                    // Move the board to the move under consideration
                    board.move(fromSquare, toSquare);                    
                    
                    // Calculate the point value at the current position
                    double pointValue = pointValueForBoard(board).get(opposingColor);
                    
                    // Add the move to the list of moves of that point value
                    ArrayList<String> squaresAtPointValues = pointValuesAndSquares.get(pointValue);
                    if (squaresAtPointValues != null)
                        { squaresAtPointValues = new ArrayList<String>(); }
                    squaresAtPointValues.add(fromSquare.location() + toSquare.location());
                    
                    if (pointValue < minPointValue)
                        { minPointValue = pointValue; }
                    
                    pointValuesAndSquares.put(pointValue, squaresAtPointValues);
                    
                    // Move the board back
                    board.move(toSquare, fromSquare);
                }
            }
        }
        
        ArrayList<String> bestMoves = pointValuesAndSquares.get(minPointValue);
        int randIndex = new Random().nextInt(bestMoves.size());
        return bestMoves.get(randIndex);
    }
    
}
