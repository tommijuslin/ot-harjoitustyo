package fi.tommijuslin.blocks;

import fi.tommijuslin.logic.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tetromino {
    
    public List<Block> blocks = new ArrayList<>();
    private int x, y;
    private Color color;
    private final Color[] colors = {
        Color.CYAN, Color.DODGERBLUE, Color.ORANGE, Color.YELLOW,
        Color.LIGHTGREEN, Color.BLUEVIOLET, Color.RED
    };
    private final String[] shapes = {
        "I", "J", "L", "O", "S", "T", "Z"
    };

    public Tetromino(List<Block> blocks, Shape shape) {
        this.blocks = blocks;
        pickColor(shape);
        this.x = 0;
        this.y = 0;
    }
    
    private void pickColor(Shape shape) {
        for (int i = 0; i < shapes.length; i++) {
            if (shape.name().equals(shapes[i])) {
                color = colors[i];
            }
        }
    }
    
    /**
     * Metodi piirtää tetrominon neliö kerrallaan peliruudulle.
     * 
     * @param   pane    Alusta, jolle tetromino piirretään
     */
    
    public void draw(Pane pane) {
        for (int i = 0; i < blocks.size(); i++) {
            Rectangle r = new Rectangle();
            r.setHeight(Board.BLOCK_SIZE);
            r.setWidth(Board.BLOCK_SIZE);
            r.setTranslateX(blocks.get(i).getX() * Board.BLOCK_SIZE);
            r.setTranslateY(blocks.get(i).getY() * Board.BLOCK_SIZE);
            r.setFill(color);
            pane.getChildren().add(r);
        }
    }
    
    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}