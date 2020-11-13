import javafx.scene.paint.Color;
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

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

    public void draw(Group g) {
        Rectangle r = new Rectangle();
        r.setHeight(Board.BLOCK_SIZE);
        r.setWidth(Board.BLOCK_SIZE);
        r.setTranslateX(this.x * Board.BLOCK_SIZE);
        r.setTranslateY(this.y * Board.BLOCK_SIZE);
        r.setFill(color);
        g.getChildren().add(r);
    }
}
