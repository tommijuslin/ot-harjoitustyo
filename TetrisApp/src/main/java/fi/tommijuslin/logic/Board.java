
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Group;

public class Board {

    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;
    public static final int BLOCK_SIZE = 40;
    public static int[][] grid = new int[BOARD_WIDTH][BOARD_HEIGHT];
    private final List<Tetromino> tetrominos = new ArrayList<>();
    private Tetromino currentTetromino;
    private boolean isValid;
    private int c1, c2, c3, c4, c5, c6, c7, c8;
    
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

        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
    }
    
    public void spawnTetromino() {
        Block[] block = new Block[4];
        Shape shape = Shape.getRandomShape();

        c1 = shape.coords[0][0];
        c2 = shape.coords[0][1];
        c3 = shape.coords[1][0];
        c4 = shape.coords[1][1];
        c5 = shape.coords[2][0];
        c6 = shape.coords[2][1];
        c7 = shape.coords[3][0];
        c8 = shape.coords[3][1];
        
        block[0] = new Block(c1,c2);
        block[1] = new Block(c3,c4);
        block[2] = new Block(c5,c6);
        block[3] = new Block(c7,c8);
        
        Tetromino tetromino = new Tetromino(block);
        tetrominos.add(tetromino);
        currentTetromino = tetromino;
        
        for (int i = 0; i < block.length; i++) {
            addBlock(block[i]);
        }
        
        move(Direction.RIGHT);
        move(Direction.RIGHT);
        move(Direction.RIGHT);
    }
    
    public void spawnSpecificTetromino(Shape shape, int x, int y) {
        Block[] block = new Block[4];

        c1 = shape.coords[0][0];
        c2 = shape.coords[0][1];
        c3 = shape.coords[1][0];
        c4 = shape.coords[1][1];
        c5 = shape.coords[2][0];
        c6 = shape.coords[2][1];
        c7 = shape.coords[3][0];
        c8 = shape.coords[3][1];
        
        block[0] = new Block(c1+x,c2+y);
        block[1] = new Block(c3+x,c4+y);
        block[2] = new Block(c5+x,c6+y);
        block[3] = new Block(c7+x,c8+y);
        
        Tetromino tetromino = new Tetromino(block);
        tetrominos.add(tetromino);
        currentTetromino = tetromino;
        
        for (int i = 0; i < block.length; i++) {
            addBlock(block[i]);
        }
    }

    public void addBlock(Block block) {
        grid[block.getX()][block.getY()]++;
    }

    public void removeBlock(Block block) {
        grid[block.getX()][block.getY()]--;
    }
    
    public void move(Direction direction) {
        for (int i = 0; i < currentTetromino.blocks.length; i++) {
            removeBlock(currentTetromino.blocks[i]);
        }
        
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
        
        for (Block block : currentTetromino.blocks) {
            isValid = checkCollisions(x, y, block);
            if (!isValid) { break; }
        }
        
        if (isValid) {
            for (Block block : currentTetromino.blocks) {
                block.setX(block.getX() + x);
                block.setY(block.getY() + y);
            }
        }
        
        for (int i = 0; i < currentTetromino.blocks.length; i++) {
            addBlock(currentTetromino.blocks[i]);
        }
        
        if (y == 1 && !isValid) {
            spawnTetromino();
        }
        
    }
    
        public boolean checkCollisions(int x, int y, Block block) {
        if (x == 1) {
            if (block.getX() == BOARD_WIDTH - 1) {
                return false;
            }

            if (grid[block.getX() + 1][block.getY()] > 0) {
                return false;
            }
        }

        if (x == -1) {
            if (block.getX() == 0) {
                return false;
            }

            if (grid[block.getX() - 1][block.getY()] > 0) {
                return false;
            }
        }

        if (y == 1) {
            if (block.getY() == BOARD_HEIGHT - 1) {
                return false;
            }

            if (grid[block.getX()][block.getY() + 1] > 0) {
                return false;
            }
        }

        return true;
    }
}
