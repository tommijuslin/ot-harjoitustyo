package fi.tommijuslin.logic;

import fi.tommijuslin.blocks.Tetromino;
import fi.tommijuslin.blocks.Shape;
import fi.tommijuslin.blocks.Block;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Board {

    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;
    public static final int BLOCK_SIZE = 40;
    public static int[][] grid = new int[BOARD_HEIGHT][BOARD_WIDTH];
    private final List<Tetromino> tetrominos = new ArrayList<>();
    private final List<Integer> rowsToDelete = new ArrayList<>();
    public Tetromino currentTetromino;
    public int patternIndex = 0;
    public boolean gameOver = false;
    private int[][] pattern;
    private Shape shape;
    private boolean isValid;
    private IntegerProperty score = new SimpleIntegerProperty();
    private Pane pane;
    
    public Board(Pane pane) {
        this.pane = pane;
    }
    
    public void initGame(Pane p) {
        initBoard();
        tetrominos.clear();
        rowsToDelete.clear();
        p.getChildren().removeIf(n -> n instanceof Rectangle);
        this.score.set(0);
        gameOver = false;
        
        spawn(Shape.getRandomShape());
        updateBoard();
    }
        
    public void initBoard() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }
    }
    
    public void updateBoard() {
        pane.getChildren().removeIf(n -> n instanceof Rectangle);
        tetrominos.forEach(t -> t.draw(pane));
//        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
    }
    
    public void spawn(Shape s) {
        patternIndex = 0;
        shape = s;
        pattern = shape.array[0];
        List<Block> tetrominoBlocks = new ArrayList<>();
        
        for (int row = 0; row < pattern.length; row++) {
            for (int col = 0; col < pattern[row].length; col++) {
                if (pattern[row][col] == 1) {
                    Block block = new Block(col, row);
                    tetrominoBlocks.add(block);
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
            isValid = movementCheck(x, y, b);
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
                if (b.getY() == 0) {
                    gameOver = true;
                }
            }
            
            if (!gameOver) {
                checkRows();

                if (!rowsToDelete.isEmpty()) {
                    deleteRows();
                }

                spawn(Shape.O);
            }
        }  
    }
    
    public boolean movementCheck(int x, int y, Block block) {
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
        
        List<Block> rotatedBlocks = rotationCheck();
        
        if (isValid) {
            currentTetromino.blocks = rotatedBlocks;
        } else {
            patternIndex--;
        }
    }
    
    private List<Block> rotationCheck() {
        List<Block> rotatedBlocks = new ArrayList<>();
        nextPattern();
        int[][] potentialRotation = shape.array[patternIndex];
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
                        rotatedBlocks.add(block);
                    }
                }
            }
        }
        return rotatedBlocks;
    }
    
    private void nextPattern() {
        patternIndex++;
        if (patternIndex == 4) {
            patternIndex = 0;
        }
    }
    
    private void checkRows() {
        boolean lines = true;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] != 1) {
                    lines = false;
                    continue;
                }
                
                if (lines && col == grid[row].length - 1) {
                    rowsToDelete.add(row);
                }
            }
            lines = true;
        }
    }
    
    private void deleteRows() {
        for (int row = 0; row < rowsToDelete.size(); row++) {
            for (Tetromino t : tetrominos) {
                List<Block> temp = t.blocks;
                List<Block> blocksToRemove = new ArrayList<>();
                for (Block b : temp) {
                    if (b.getY() == rowsToDelete.get(row)) {
                        removeFromGrid(b);
                        blocksToRemove.add(b);
                    } else if (b.getY() < rowsToDelete.get(row)) {
                        Block tempBlock = b;
                        removeFromGrid(b);
                        tempBlock.setY(tempBlock.getY() + 1);
                        addToGrid(tempBlock);
                    }
                }
                t.blocks.removeAll(blocksToRemove);
            }
        }
        incrementScore();
        rowsToDelete.clear();
    }
    
    private void incrementScore() {
        switch (rowsToDelete.size()) {
            case 4:
                this.score.set(score.get() + 1200);
                break;
            case 3:
                this.score.set(score.get() + 300);
                break;
            case 2:
                this.score.set(score.get() + 100);
                break;
            case 1:
                this.score.set(score.get() + 40); 
        }
    }
    
    public IntegerProperty scoreProperty() {
        return score;
    }
}
