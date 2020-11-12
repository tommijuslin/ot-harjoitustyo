package tetrisapp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void blockIsCorrectlyAddedToGridWhenSpawned() {
        board.spawnBlock(5,5);
        assertEquals(Board.grid[5][5], 1);
    }

    @Test
    public void blockMovesDownCorrectlyOnGrid() {
        board.spawnBlock(0, 0);
        board.moveDown();
        assertEquals(Board.grid[0][1], 1);
    }

    @Test
    public void blockMovesLeftCorrectlyOnGrid() {
        board.spawnBlock(5, 1);
        board.moveLeft();
        assertEquals(Board.grid[4][1], 1);
    }

    @Test
    public void blockMovesRightCorrectlyOnGrid() {
        board.spawnBlock(5, 0);
        board.moveRight();
        assertEquals(Board.grid[6][0], 1);
    }

    @Test
    public void blockCantGoOffScreenBelow() {
        board.spawnBlock(0,18);
        board.moveDown();
        board.moveDown();
        assertEquals(Board.grid[0][19], 1);
    }

    @Test
    public void blockCantGoOffScreenLeft() {
        board.spawnBlock(1,0);
        board.moveLeft();
        board.moveLeft();
        assertEquals(Board.grid[0][0], 1);
    }

    @Test
    public void blockCantGoOffScreenRight() {
        board.spawnBlock(8,0);
        board.moveRight();
        board.moveRight();
        assertEquals(Board.grid[9][0], 1);
    }
}