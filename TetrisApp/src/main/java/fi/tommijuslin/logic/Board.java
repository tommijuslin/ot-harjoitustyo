package fi.tommijuslin.logic;

import fi.tommijuslin.blocks.Tetromino;
import fi.tommijuslin.blocks.Shape;
import fi.tommijuslin.blocks.Block;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Board {

    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;
    public static final int BLOCK_SIZE = 40;
    private final List<Tetromino> tetrominos = new ArrayList<>();
    public Tetromino currentTetromino;
    public int patternIndex = 0;
    public boolean gameOver = false;
    private int[][] pattern;
    private Shape shape;
    private Shape nextShape = null;
    private final Pane pane;
    private final Grid grid;
    private final Score score;
    
    public Board(Pane pane, Grid grid, Score score) {
        this.pane = pane;
        this.grid = grid;
        this.score = score;
    }
    
    /**
     * Pelin alustaminen. Tyhjentää peliruudun ja nollaa pisteet
     */

    public void initGame() {
        grid.initGrid();
        grid.initRowsToClear();
        tetrominos.clear();
        pane.getChildren().removeIf(n -> n instanceof Rectangle);
        score.initScore();
        gameOver = false;
        spawn(Shape.getRandomShape());
        updateBoard();
    }
    
    /**
     * Päivittää peliruudun poistamalla kaikki tetrominot ja piirtämällä
     * ne uudestaan uusien koordinaattien mukaisesti
     */
    
    public void updateBoard() {
        pane.getChildren().removeIf(n -> n instanceof Rectangle);
        tetrominos.forEach(t -> t.draw(pane));
    }
    
    /**
     * Spawnaa tetrominon pelialueelle
     * 
     * @param shape Tetrominon muoto
     */
    
    public void spawn(Shape shape) {
//        setNextShape(shape);
        this.shape = shape;
        
        List<Block> tetrominoBlocks = buildTetromino();
        
        Tetromino tetromino = new Tetromino(tetrominoBlocks, this.shape);
        tetrominos.add(tetromino);
        currentTetromino = tetromino;
        
        if (shape == Shape.O) {
            move(4, 0);
        } else {
            move(3, 0);
        }  
    }
    
    private void setNextShape(Shape shape) {
        if (nextShape != null) {
            this.shape = nextShape;
        } else {
            this.shape = shape;
        }
        nextShape = Shape.getRandomShape();
    }
    
    /**
     * Rakentaa tetrominon yksittäisistä palikoista
     * 
     * @return rakennettu tetromino
     */
    
    private List<Block> buildTetromino() {
        patternIndex = 0;
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
        
        return tetrominoBlocks;
    }
    
    /**
     * Liikuttaa tetrominoa annettujen parametrien mukaisesti
     * 
     * @param x liikkeen määrä vaakasuunnassa
     * @param y liikkeen määrä pystysuunnassa
     */
    
    public void move(int x, int y) {
        boolean isValid = moveIsValid(x, y);
        
        if (isValid) {
            for (Block b : currentTetromino.blocks) {
                b.setX(b.getX() + x);
                b.setY(b.getY() + y);
            }
            currentTetromino.setX(currentTetromino.getX() + x);
            currentTetromino.setY(currentTetromino.getY() + y);
        }
        
        if (y == 1 && !isValid) {
            landTetromino();
        }
    }
    
    private boolean moveIsValid(int x, int y) {
        for (Block b : currentTetromino.blocks) {
            if (collides(x, y, b)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean collides(int x, int y, Block block) {
        if (x == 1) {
            if (block.getX() == BOARD_WIDTH - 1) {
                return true;
            }
            
            if (grid.getGrid()[block.getY()][block.getX() + 1] > 0) {
                return true;
            }
        }
        
        if (x == -1) {
            if (block.getX() == 0) {
                return true;
            }

            if (grid.getGrid()[block.getY()][block.getX() - 1] > 0) {
                return true;
            }
        }

        if (y == 1) {
            if (block.getY() == BOARD_HEIGHT - 1) {
                return true;
            }
            
            if (grid.getGrid()[block.getY() + 1][block.getX()] > 0) {
                return true;
            }
        }
        
        return false;
    }
    
    private void landTetromino() {
        for (Block b : currentTetromino.blocks) {
            grid.addToGrid(b);
            if (b.getY() == 0) {
                gameOver = true;
            }
        }
        
        if (!gameOver) {
            grid.handleFullRows(tetrominos);
            score.incrementScore(grid.rowsToDelete.size());
            grid.initRowsToClear();
            spawn(Shape.getRandomShape());
        }
    }
   
    
    /**
     * Pyörittää tetrominoa myötäpäivään 90 astetta
     */
    
    public void rotate() {
        if (shape == Shape.O) {
            return;
        }

        List<Block> newRotation = getNextRotation();
        
        if (newRotation != null) {
            currentTetromino.blocks = newRotation;
        } else {
            patternIndex--;
        }
    }
    
    private List<Block> getNextRotation() {
        List<Block> rotatedBlocks = new ArrayList<>();
        nextPattern();
        int[][] potentialRotation = shape.array[patternIndex];
        for (int row = 0; row < potentialRotation.length; row++) {
            for (int col = 0; col < potentialRotation[row].length; col++) {
                if (potentialRotation[row][col] == 1) {
                    if (currentTetromino.getX() + col < 0 || currentTetromino.getX() + col > BOARD_WIDTH - 1) {
                        return null;
                    } else if (grid.getGrid()[currentTetromino.getY() + row][currentTetromino.getX() + col] == 1) {
                        return null;
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
}
