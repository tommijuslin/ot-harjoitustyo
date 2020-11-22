import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    Board board;

    @Before
    public void setUp() {
        board = new Board();
        board.initBoard();
    }

    @Test
    public void tetrominoIsCorrectlyAddedToGridWhenSpawned() {
        int x = 3;
        int y = 0;
        board.spawnSpecificTetromino(Shape.S, x, y);
        assertEquals(Board.grid[1+x][0+y], 1);
        assertEquals(Board.grid[2+x][0+y], 1);
        assertEquals(Board.grid[0+x][1+y], 1);
        assertEquals(Board.grid[1+x][1+y], 1);
    }

    @Test
    public void tetrominoMovesDownCorrectlyOnGrid() {
        int x = 3;
        int y = 0;
        board.spawnSpecificTetromino(Shape.S, x, y);
        board.move(Board.Direction.DOWN);
        assertEquals(Board.grid[1+x][0+y+1], 1);
        assertEquals(Board.grid[2+x][0+y+1], 1);
        assertEquals(Board.grid[0+x][1+y+1], 1);
        assertEquals(Board.grid[1+x][1+y+1], 1);
    }

    @Test
    public void tetrominoMovesLeftCorrectlyOnGrid() {
        int x = 3;
        int y = 0;
        board.spawnSpecificTetromino(Shape.S, x, y);
        board.move(Board.Direction.LEFT);
        assertEquals(Board.grid[1+x-1][0+y], 1);
        assertEquals(Board.grid[2+x-1][0+y], 1);
        assertEquals(Board.grid[0+x-1][1+y], 1);
        assertEquals(Board.grid[1+x-1][1+y], 1);
    }
    
    @Test
    public void tetrominoMovesRightCorrectlyOnGrid() {
        int x = 3;
        int y = 0;
        board.spawnSpecificTetromino(Shape.S, x, y);
        board.move(Board.Direction.RIGHT);
        assertEquals(Board.grid[1+x+1][0+y], 1);
        assertEquals(Board.grid[2+x+1][0+y], 1);
        assertEquals(Board.grid[0+x+1][1+y], 1);
        assertEquals(Board.grid[1+x+1][1+y], 1);
    }

    @Test
    public void tetrominoCantGoOffScreenBelow() {
        int x = 3;
        int y = 18;
        board.spawnSpecificTetromino(Shape.S, x, y);
        board.move(Board.Direction.DOWN);
        assertEquals(Board.grid[1+x][0+y], 1);
        assertEquals(Board.grid[2+x][0+y], 1);
        assertEquals(Board.grid[0+x][1+y], 1);
        assertEquals(Board.grid[1+x][1+y], 1);
    }

    @Test
    public void tetrominoCantGoOffScreenLeft() {
        int x = 0;
        int y = 0;
        board.spawnSpecificTetromino(Shape.S, x, y);
        board.move(Board.Direction.LEFT);
        assertEquals(Board.grid[1+x][0+y], 1);
        assertEquals(Board.grid[2+x][0+y], 1);
        assertEquals(Board.grid[0+x][1+y], 1);
        assertEquals(Board.grid[1+x][1+y], 1);
    }

    @Test
    public void tetrominoCantGoOffScreenRight() {
        int x = 7;
        int y = 0;
        board.spawnSpecificTetromino(Shape.S, x, y);
        board.move(Board.Direction.RIGHT);
        assertEquals(Board.grid[1+x][0+y], 1);
        assertEquals(Board.grid[2+x][0+y], 1);
        assertEquals(Board.grid[0+x][1+y], 1);
        assertEquals(Board.grid[1+x][1+y], 1);
    }
}