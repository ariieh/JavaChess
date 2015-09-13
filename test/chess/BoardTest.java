package chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ariehsmith
 */
public class BoardTest {
    
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testColorInCheckmate() {
        Board board = new Board();
        
        // No one in checkmate in beginning position
        assertEquals(false, board.isColorInCheckmate("W"));
        assertEquals(false, board.isColorInCheckmate("B"));
        
        // Fool's mate -- white is checkmated
        board.move("F2", "F3");
        board.move("E7", "E5");
        board.move("G2", "G4");
        board.move("D8", "H4");
        assertEquals(true, board.isColorInCheckmate("W"));
    }
    
    @Test
    public void testIsColorInCheck() {
        Board board = new Board();
        
        // No one in check in beginning position
        assertEquals(false, board.isColorInCheck("W"));
        assertEquals(false, board.isColorInCheck("B"));
        
        // Fool's mate -- white in chcck
        board.move("F2", "F3");
        board.move("E7", "E5");
        board.move("G2", "G4");
        board.move("D8", "H4");
        assertEquals(true, board.isColorInCheck("W"));
        assertEquals(false, board.isColorInCheck("B"));
    }
    
}
