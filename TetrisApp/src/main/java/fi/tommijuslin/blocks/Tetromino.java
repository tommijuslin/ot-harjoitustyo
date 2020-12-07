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
    private final Random rand = new Random();
    private int x, y;
    private final Color color;
    private final Color[] colors = {
        Color.CYAN, Color.DODGERBLUE, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUEVIOLET, Color.RED
    };

    public Tetromino(List<Block> blocks) {
        this.blocks = blocks;
        this.color = colors[rand.nextInt(7)];
        this.x = 0;
        this.y = 0;
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