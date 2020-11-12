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

    public void moveDown() {
        if (currentBlock.getY() == BOARD_HEIGHT - 1) {
            this.spawnBlock(4, 0);
        } else if (grid[currentBlock.getX()][currentBlock.getY() + 1] == 1) {
            this.spawnBlock(4, 0);
        } else if (currentBlock.getY() < BOARD_HEIGHT - 1) {
            removeBlock(currentBlock);
            currentBlock.setY(currentBlock.getY() + 1);
            addBlock(currentBlock);
        }
    }

    public void moveRight() {
        if (currentBlock.getX() < BOARD_WIDTH - 1) {
            removeBlock(currentBlock);
            currentBlock.setX(currentBlock.getX() + 1);
            addBlock(currentBlock);
        }
    }

    public void moveLeft() {
        if (currentBlock.getX() > 0) {
            removeBlock(currentBlock);
            currentBlock.setX(currentBlock.getX() - 1);
            addBlock(currentBlock);
        }
    }
}
