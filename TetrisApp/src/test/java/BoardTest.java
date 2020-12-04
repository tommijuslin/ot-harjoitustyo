import fi.tommijuslin.blocks.Shape;
import fi.tommijuslin.logic.Board;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    Board board;
    Pane pane;

    @Before
    public void setUp() {
        board = new Board(pane);
        board.initBoard();
    }

    @Test
    public void tetrominoMovesDownCorrectly() {
        board.spawn(Shape.I);
        board.move(0, 1);
        assertEquals(board.currentTetromino.getY(), 1);
    }
    
    @Test
    public void tetrominoMovesLeftCorrectly() {
        board.spawn(Shape.I);
        board.move(-1, 0);
        assertEquals(board.currentTetromino.getX(), 2);
    }
    
    @Test
    public void tetrominoMovesRightCorrectly() {
        board.spawn(Shape.I);
        board.move(1, 0);
        assertEquals(board.currentTetromino.getX(), 4);
    }
    
    @Test
    public void tetrominoCantGoOffscreenLeft() {
        board.spawn(Shape.I);
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
        board.spawn(Shape.I);
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
        board.spawn(Shape.I);
        board.move(0, 18);
        board.move(0, 1);
        assertEquals(Board.grid[19][3], 1);
        assertEquals(Board.grid[19][4], 1);
        assertEquals(Board.grid[19][5], 1);
        assertEquals(Board.grid[19][6], 1);
    }
    
    @Test
    public void tetrominoIsAddedToGridWhenLandingOnAnotherTetromino() {
        board.spawn(Shape.I);
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
        board.spawn(Shape.I);
        board.rotate();
        assertEquals(board.patternIndex, 1);
    }
    
    @Test
    public void tetrominoRotatesCorrectlyTwice() {
        board.spawn(Shape.I);
        board.rotate();
        board.rotate();
        assertEquals(board.patternIndex, 2);
    }
    
    @Test
    public void tetrominoRotatesCorrectlyThreeTimes() {
        board.spawn(Shape.I);
        board.rotate();
        board.rotate();
        board.rotate();
        assertEquals(board.patternIndex, 3);
    }
    
    @Test
    public void tetrominoRotatesCorrectlyFourTimes() {
        board.spawn(Shape.I);
        board.rotate();
        board.rotate();
        board.rotate();
        board.rotate();
        assertEquals(board.patternIndex, 0);
    }
    
    @Test
    public void tetrominoCantRotateIfItMakesItGoOffscreenRight() {
        board.spawn(Shape.I);
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
        board.spawn(Shape.I);
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
        board.spawn(Shape.I);
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
    
    @Test
    public void rowsAreDeletedCorrectly() {
        board.spawn(Shape.I);
        board.move(-3, 18);
        board.move(0, 1);
        board.spawn(Shape.I);
        board.move(1, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(5, 18);
        board.move(0, 1);
        assertEquals(Board.grid[19][0], 0);
        assertEquals(Board.grid[19][1], 0);
        assertEquals(Board.grid[19][2], 0);
        assertEquals(Board.grid[19][3], 0);
        assertEquals(Board.grid[19][4], 0);
        assertEquals(Board.grid[19][5], 0);
        assertEquals(Board.grid[19][6], 0);
        assertEquals(Board.grid[19][7], 0);
        assertEquals(Board.grid[19][8], 1);
        assertEquals(Board.grid[19][9], 1);
    }
}