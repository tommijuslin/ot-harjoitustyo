import fi.tommijuslin.blocks.Shape;
import fi.tommijuslin.logic.Board;
import fi.tommijuslin.logic.Grid;
import fi.tommijuslin.logic.Score;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    Board board;
    Pane pane;
    Grid grid = new Grid();
    Score score = new Score("0");

    @Before
    public void setUp() {
        board = new Board(pane, grid, score);
        grid.initGrid();
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
        assertEquals(grid.getGrid()[19][3], 1);
        assertEquals(grid.getGrid()[19][4], 1);
        assertEquals(grid.getGrid()[19][5], 1);
        assertEquals(grid.getGrid()[19][6], 1);
    }
    
    @Test
    public void tetrominoIsAddedToGridWhenLandingOnAnotherTetromino() {
        board.spawn(Shape.I);
        board.move(0, 18);
        board.move(0, 1);
        board.spawn(Shape.I);
        board.move(0, 17);
        board.move(0, 1);
        board.move(0, 1);
        assertEquals(grid.getGrid()[18][3], 1);
        assertEquals(grid.getGrid()[18][4], 1);
        assertEquals(grid.getGrid()[18][5], 1);
        assertEquals(grid.getGrid()[18][6], 1);
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
        board.move(4, 18);
        board.move(0, 1);
        assertEquals(grid.getGrid()[19][0], 0);
        assertEquals(grid.getGrid()[19][1], 0);
        assertEquals(grid.getGrid()[19][2], 0);
        assertEquals(grid.getGrid()[19][3], 0);
        assertEquals(grid.getGrid()[19][4], 0);
        assertEquals(grid.getGrid()[19][5], 0);
        assertEquals(grid.getGrid()[19][6], 0);
        assertEquals(grid.getGrid()[19][7], 0);
        assertEquals(grid.getGrid()[19][8], 1);
        assertEquals(grid.getGrid()[19][9], 1);
    }
    
    @Test
    public void scoreIsIncrementedCorrectlyWhenClearingOneRow() {
        board.spawn(Shape.I);
        board.move(-3, 18);
        board.move(0, 1);
        board.spawn(Shape.I);
        board.move(1, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(4, 18);
        board.move(0, 1);
        assertEquals(score.getScore(), 40);
    }
    
    // tähän on varmaan joku parempikin tapa
    
    @Test
    public void scoreIsIncrementedCorrectlyWhenClearingTwoRows() {
        board.spawn(Shape.O);
        board.move(-4, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(-2, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(0, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(2, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(4, 18);
        board.move(0, 1);
        assertEquals(score.getScore(), 100);
    }
    
    // no ei voi mitään...
    
    @Test
    public void scoreIsIncrementedCorrectlyWhenClearingThreeRows() {
        board.spawn(Shape.O);
        board.move(-4, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(-2, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(0, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(2, 18);
        board.move(0, 1);
        board.spawn(Shape.I);
        board.move(-3, 16);
        board.move(0, 1);
        board.spawn(Shape.I);
        board.move(1, 16);
        board.move(0, 1);
        board.spawn(Shape.I);
        board.rotate();
        board.move(3, 16);
        board.move(0, 1);
        board.spawn(Shape.I);
        board.rotate();
        board.move(4, 16);
        board.move(0, 1);
        assertEquals(score.getScore(), 300);
    }
    
    // alkaa menemään aavistuksen kömpelöksi
    
    @Test
    public void scoreIsIncrementedCorrectlyWhenClearingFourRows() {
        board.spawn(Shape.O);
        board.move(-4, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(-2, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(0, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(2, 18);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(-4, 16);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(-2, 16);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(0, 16);
        board.move(0, 1);
        board.spawn(Shape.O);
        board.move(2, 16);
        board.move(0, 1);
        board.spawn(Shape.I);
        board.rotate();
        board.move(3, 16);
        board.move(0, 1);
        board.spawn(Shape.I);
        board.rotate();
        board.move(4, 16);
        board.move(0, 1);
        assertEquals(score.getScore(), 1200);
    }
}