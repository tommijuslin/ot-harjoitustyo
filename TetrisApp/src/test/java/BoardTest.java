import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

//    @Test
//    public void blockIsCorrectlyAddedToGridWhenSpawned() {
//        board.spawnTetromino();
//        assertEquals(Board.grid[5][5], 1);
//    }
//
//    @Test
//    public void blockMovesDownCorrectlyOnGrid() {
//        board.spawnTetromino();
//        board.move(Board.Direction.DOWN);
//        assertEquals(Board.grid[0][1], 1);
//    }
//
//    @Test
//    public void blockMovesLeftCorrectlyOnGrid() {
//        board.spawnBlock(5, 1);
//        board.move(Board.Direction.LEFT);
//        assertEquals(Board.grid[4][1], 1);
//    }
//
//    @Test
//    public void blockMovesRightCorrectlyOnGrid() {
//        board.spawnBlock(5, 0);
//        board.move(Board.Direction.RIGHT);
//        assertEquals(Board.grid[6][0], 1);
//    }
//
//    @Test
//    public void blockCantGoOffScreenBelow() {
//        board.spawnBlock(0,18);
//        board.move(Board.Direction.DOWN);
//        board.move(Board.Direction.DOWN);
//        assertEquals(Board.grid[0][19], 1);
//    }
//
//    @Test
//    public void blockCantGoOffScreenLeft() {
//        board.spawnBlock(1,0);
//        board.move(Board.Direction.LEFT);
//        board.move(Board.Direction.LEFT);
//        assertEquals(Board.grid[0][0], 1);
//    }
//
//    @Test
//    public void blockCantGoOffScreenRight() {
//        board.spawnBlock(8,0);
//        board.move(Board.Direction.RIGHT);
//        board.move(Board.Direction.RIGHT);
//        assertEquals(Board.grid[9][0], 1);
//    }
}