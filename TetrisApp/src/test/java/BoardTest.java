import fi.tommijuslin.blocks.Shape;
import fi.tommijuslin.logic.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    Board board;

    @Before
    public void setUp() {
        board = new Board();
        board.initBoard();
        board.spawn(Shape.I);
    }

    @Test
    public void tetrominoMovesDownCorrectly() {
        board.move(0, 1);
        assertEquals(board.currentTetromino.getY(), 1);
    }
    
    @Test
    public void tetrominoMovesLeftCorrectly() {
        board.move(-1, 0);
        assertEquals(board.currentTetromino.getX(), 2);
    }
    
    @Test
    public void tetrominoMovesRightCorrectly() {
        board.move(1, 0);
        assertEquals(board.currentTetromino.getX(), 4);
    }
    
    @Test
    public void tetrominoCantGoOffscreenLeft() {
        board.move(-1, 0);
        board.move(-1, 0);
        board.move(-1, 0);
        board.move(-1, 0);
        board.move(-1, 0);
        board.move(-1, 0);
        assertEquals(board.currentTetromino.getX(), 0);
    }
    
    @Test
    public void tetrominoCantGoOffscreenRight() {
        board.move(1, 0);
        board.move(1, 0);
        board.move(1, 0);
        board.move(1, 0);
        board.move(1, 0);
        board.move(1, 0);
        assertEquals(board.currentTetromino.getX(), 6);
    }
    
    @Test
    public void tetrominoIsAddedToGridWhenLanding() {
        board.move(0, 18);
        board.move(0, 1);
        assertEquals(Board.grid[19][3], 1);
        assertEquals(Board.grid[19][4], 1);
        assertEquals(Board.grid[19][5], 1);
        assertEquals(Board.grid[19][6], 1);
    }
    
    @Test
    public void tetrominoIsAddedToGridWhenLandingOnAnotherTetromino() {
        board.move(0, 18);
        board.move(0, 1);
        board.spawn(Shape.I);
        board.move(0, 17);
        board.move(0, 1);
        assertEquals(Board.grid[18][3], 1);
        assertEquals(Board.grid[18][4], 1);
        assertEquals(Board.grid[18][5], 1);
        assertEquals(Board.grid[18][6], 1);
    }
    
    @Test
    public void tetrominoRotatesCorrectlyOnce() {
        board.rotate();
        assertEquals(board.patternIndex, 1);
    }
    
    @Test
    public void tetrominoRotatesCorrectlyTwice() {
        board.rotate();
        board.rotate();
        assertEquals(board.patternIndex, 2);
    }
    
    @Test
    public void tetrominoRotatesCorrectlyThreeTimes() {
        board.rotate();
        board.rotate();
        board.rotate();
        assertEquals(board.patternIndex, 3);
    }
    
    @Test
    public void tetrominoRotatesCorrectlyFourTimes() {
        board.rotate();
        board.rotate();
        board.rotate();
        board.rotate();
        assertEquals(board.patternIndex, 0);
    }
    
    @Test
    public void tetrominoCantRotateIfItMakesItGoOffscreenRight() {
        board.rotate();
        board.move(1, 0);
        board.move(1, 0);
        board.move(1, 0);
        board.move(1, 0);
        board.move(1, 0);
        board.move(1, 0);
        board.rotate();
        assertEquals(board.patternIndex, 1);
    }
    
    @Test
    public void tetrominoCantRotateIfItMakesItGoOffscreenLeft() {
        board.rotate();
        board.move(-1, 0);
        board.move(-1, 0);
        board.move(-1, 0);
        board.move(-1, 0);
        board.move(-1, 0);
        board.move(-1, 0);
        board.rotate();
        assertEquals(board.patternIndex, 1);
    }
    
    @Test
    public void tetrominoCantRotateToAPlaceThatsOccupied() {
        board.rotate();
        board.move(0, 15);
        board.move(0, 1);
        board.move(0, 1);
        board.spawn(Shape.I);
        board.rotate();
        board.move(1, 15);
        board.move(0, 1);
        board.rotate();
        assertEquals(board.patternIndex, 1);
        
    }
}