import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Block {

    public int x;
    public int y;
    public Color color;
    public Color[] colors = {
        Color.CYAN, Color.DARKBLUE, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.PURPLE, Color.RED
    };
    Random rand = new Random();

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = colors[rand.nextInt(7)];
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public void draw(GraphicsContext gc) {
        gc.setFill(this.color);
        gc.fillRect(this.x * Board.BLOCK_SIZE, this.y * Board.BLOCK_SIZE, Board.BLOCK_SIZE, Board.BLOCK_SIZE);
    }
}
