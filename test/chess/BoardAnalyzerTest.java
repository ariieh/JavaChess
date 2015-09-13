package chess;

import java.util.HashMap;
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
public class BoardAnalyzerTest {
    
    public BoardAnalyzerTest() {
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
    public void testPointValue() {
        Board board = new Board();
        BoardAnalyzer instance = new BoardAnalyzer();
        HashMap<String, Double> expResult = new HashMap();
        
        // Starting position
        expResult.put("W", 43.5);
        expResult.put("B", 43.5);
        HashMap<String, Double> result = instance.pointValueForBoard(board);
        assertEquals(expResult, result);
        
        // After one pawn capture
        board.move("E2", "E4");
        board.move("D7", "D5");
        board.move("E4", "D5");
        expResult.put("B", 42.5);
        result = instance.pointValueForBoard(board);
        assertEquals(expResult, result);
        
        // After bishop capture
        board.move("C8", "E6");
        board.move("D5", "E6");
        expResult.put("B", 39.25);
        result = instance.pointValueForBoard(board);
        assertEquals(expResult, result);
        
        // After pawn takeback
        board.move("F7", "E6");
        expResult.put("W", 42.5);
        result = instance.pointValueForBoard(board);
        assertEquals(expResult, result);
    }
}
