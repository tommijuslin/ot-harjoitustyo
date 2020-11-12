package tetrisapp;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;
    public static final int BLOCK_SIZE = 40;
    public static int[][] grid = new int[BOARD_WIDTH][BOARD_HEIGHT];
    private final List<Block> blocks = new ArrayList<>();
    private Block currentBlock;
    private boolean isValid;
    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    public void updateBoard(GraphicsContext gc) {
        gc.clearRect(0, 0, BOARD_WIDTH * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE);

        this.drawBlocks(gc);

        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
    }

    public void spawnBlock(int x, int y) {
        Block block = new Block(x, y);
        blocks.add(block);
        currentBlock = block;
        addBlock(block);
    }

    public void addBlock(Block block) {
        grid[block.getX()][block.getY()]++;
    }

    public void removeBlock(Block block) {
        grid[block.getX()][block.getY()]--;
    }

    public void drawBlocks(GraphicsContext gc) {
        blocks.forEach(b -> b.draw(gc));
    }

    public void move(Direction direction) {
        removeBlock(currentBlock);

        int x = 0;
        int y = 0;

        switch (direction) {
            case LEFT:
                x = -1;
                break;
            case RIGHT:
                x = 1;
                break;
            case DOWN:
                y = 1;
                break;
        }

        isValid = checkCollisions(x, y);

        if (isValid) {
            currentBlock.setX(currentBlock.getX() + x);
            currentBlock.setY(currentBlock.getY() + y);
        }

        addBlock(currentBlock);

        if (y == 1 && !isValid) {
            spawnBlock(4, 0);
        }
    }

    public boolean checkCollisions(int x, int y) {
        if (x == 1) {
            if (currentBlock.getX() == BOARD_WIDTH - 1) {
                return false;
            }

            if (grid[currentBlock.getX() + 1][currentBlock.getY()] > 0) {
                return false;
            }
        }

        if (x == -1) {
            if (currentBlock.getX() == 0) {
                return false;
            }

            if (grid[currentBlock.getX() - 1][currentBlock.getY()] > 0) {
                return false;
            }
        }

        if (y == 1) {
            if (currentBlock.getY() == BOARD_HEIGHT - 1) {
                return false;
            }

            if (grid[currentBlock.getX()][currentBlock.getY() + 1] > 0) {
                return false;
            }
        }

        return true;
    }
}
