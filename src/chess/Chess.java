package chess;
import java.util.Scanner;

public class Chess {

    public static void main(String[] args) {
        Board board = new Board();
        String colorToMove = "B";
        String opposingColor = "W";
        Scanner reader = new Scanner(System.in);
        
        // Game loop
        while (true) {
            // Swap colors
            String temp = colorToMove;
            colorToMove = opposingColor;
            opposingColor = temp;
            
            // Print the board
            System.out.println(board.toString());
            
            // Keep looping while White makes a valid move
            String from = "H0"; String to = "H0";
            String move = "H0H0";
            
            if (colorToMove.equals("W")) {
                while (!board.isValidMoveForColor(from, to, colorToMove)) {
                    System.out.println("White, please enter a valid move (e.g. E2E4):");
                    move = reader.next();
                    from = move.substring(0, 2);
                    to   = move.substring(2);
                }
            } else {
                move = BoardAnalyzer.bestMoveForColor(board, colorToMove);
                from = move.substring(0, 2);
                to   = move.substring(2);
            }

            // Make the move
            board.move(from, to);
            
            // Checkmate.
            if (board.isColorInCheckmate(opposingColor)) {
                System.out.println(colorToMove + " wins!"); break;
            }
        }
    }
    
}
