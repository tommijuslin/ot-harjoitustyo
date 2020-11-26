package fi.tommijuslin.logic;

import fi.tommijuslin.blocks.Tetromino;
import fi.tommijuslin.blocks.Shape;
import fi.tommijuslin.blocks.Block;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Group;

public class Board {

    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;
    public static final int BLOCK_SIZE = 40;
    public static int[][] grid = new int[BOARD_HEIGHT][BOARD_WIDTH];
    private final List<Tetromino> tetrominos = new ArrayList<>();
    public Tetromino currentTetromino;
    private boolean isValid;
    public int patternIndex = 0;
    private int[][] pattern;
    private Shape shape;
    
    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }
        
    public void initBoard() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }
    }
    
    public void updateBoard(Group g) {
        g.getChildren().clear();     
        tetrominos.forEach(t -> t.draw(g));
//        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
    }
    
    public void spawn(Shape s) {
        patternIndex = 0;
        shape = s;
        pattern = shape.array[0];
        Block[] tetrominoBlocks = new Block[4];
        int blockIndex = 0;
        
        for (int row = 0; row < pattern.length; row++) {
            for (int col = 0; col < pattern[row].length; col++) {
                if (pattern[row][col] == 1) {
                    Block block = new Block(col, row);
                    tetrominoBlocks[blockIndex] = block;
                    blockIndex++;
                }
            }
        }
        
        Tetromino tetromino = new Tetromino(tetrominoBlocks);
        tetrominos.add(tetromino);
        currentTetromino = tetromino;
        
        move(3, 0);
    }
    
    private void addToGrid(Block block) {
        grid[block.getY()][block.getX()]++;
    }
    
    private void removeFromGrid(Block block) {
        grid[block.getY()][block.getX()]--;
    }
    
    public void move(int x, int y) {
        for (Block b : currentTetromino.blocks) {
            isValid = checkCollisions(x, y, b);
            if (!isValid) {
                break;
            }
        }
        
        if (isValid) {
            for (Block b : currentTetromino.blocks) {
                b.setX(b.getX() + x);
                b.setY(b.getY() + y);
            }
            
            currentTetromino.setX(currentTetromino.getX() + x);
            currentTetromino.setY(currentTetromino.getY() + y);
            
        }
        
        if (y == 1 && !isValid) {
            for (Block b : currentTetromino.blocks) {
                addToGrid(b);
            }
            spawn(Shape.getRandomShape());
        }
        
    }
    
    public boolean checkCollisions(int x, int y, Block block) {
        if (x == 1) {
            if (block.getX() == BOARD_WIDTH - 1) {
                return false;
            }
            
            if (grid[block.getY()][block.getX() + 1] > 0) {
                return false;
            }
        }
        
        if (x == -1) {
            if (block.getX() == 0) {
                return false;
            }

            if (grid[block.getY()][block.getX() - 1] > 0) {
                return false;
            }
        }

        if (y == 1) {
            if (block.getY() == BOARD_HEIGHT - 1) {
                return false;
            }
            
            if (grid[block.getY() + 1][block.getX()] > 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public void rotate() {
        if (shape == Shape.O) {
            return;
        }
        
        patternIndex++;
        if (patternIndex == 4) {
            patternIndex = 0;
        }
        
        int[][] potentialRotation = shape.array[patternIndex];
        Block[] replacementBlocks = new Block[4];
        int blockIndex = 0;
        isValid = true;
        
        for (int row = 0; row < potentialRotation.length; row++) {
            for (int col = 0; col < potentialRotation[row].length; col++) {
                if (potentialRotation[row][col] == 1) {
                    if (currentTetromino.getX() + col < 0 || currentTetromino.getX() + col > BOARD_WIDTH - 1) {
                        isValid = false;
                        break;
                    } else if (grid[currentTetromino.getY() + row][currentTetromino.getX() + col] == 1) {
                        isValid = false;
                        break;
                    } else {
                        Block block = new Block(currentTetromino.getX() + col, currentTetromino.getY() + row);
                        replacementBlocks[blockIndex] = block;
                        blockIndex++;
                    }
                }
            }
        }
        
        if (isValid) {
            currentTetromino.blocks = replacementBlocks;
        } else {
            patternIndex--;
        }
    }
}
