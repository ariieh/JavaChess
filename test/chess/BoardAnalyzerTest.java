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
    public void testPointValueInStartingPosition() {
        Board board = new Board();
        BoardAnalyzer instance = new BoardAnalyzer();
        HashMap<String, Double> expResult = new HashMap();
        expResult.put("W", 43.5);
        expResult.put("B", 43.5);
        HashMap<String, Double> result = instance.pointValueForBoardAndColor(board);
        assertEquals(expResult, result);
    }
    
}
