package chess;
import java.util.Scanner;

public class Chess {

    public static void main(String[] args) {
        Board board = new Board();
        String color = "B";
        Scanner reader = new Scanner(System.in);
        
        // Game loop
        while (true) {
            // Swap colors
            color = (color.equals("W") ? "B" : "W");
            
            // Print the board
            System.out.println(board.toString());
                        
            // Keep looping while the user makes a valid move
            String from = "H0"; String to = "H0";
            
            while (!board.isValidMoveForColor(from, to, color)) {
                System.out.print(color.equals("W") ? "White" : "Black");
                System.out.println(", please enter a valid move (e.g. E2E4):");
                String move = reader.next();
                from = move.substring(0, 2);
                to   = move.substring(2);
            }
            
            // Make the move
            board.move(from, to);
            
            // Checkmate.
            if (board.isColorInCheckmate("W")) {
                System.out.println("White wins!"); break;
            }
            if (board.isColorInCheckmate("B")) {
                System.out.println("Black wins!"); break;
            }
        }
    }
    
}
