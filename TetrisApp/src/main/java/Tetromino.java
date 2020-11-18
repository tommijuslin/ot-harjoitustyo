
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class Tetromino {
    
    public Block[] blocks = new Block[4];
    private final Color color;
    private final Color[] colors = {
        Color.CYAN, Color.DARKBLUE, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.PURPLE, Color.RED
    };
    Random rand = new Random();
    
    public Tetromino(Block[] blocks) {
        this.blocks = blocks;
        this.color = colors[rand.nextInt(7)];
    }
    
    public void draw(Group g) {
        for (int i = 0; i < blocks.length; i++) {
            Rectangle r = new Rectangle();
            r.setHeight(Board.BLOCK_SIZE);
            r.setWidth(Board.BLOCK_SIZE);
            r.setTranslateX(blocks[i].getX() * Board.BLOCK_SIZE);
            r.setTranslateY(blocks[i].getY() * Board.BLOCK_SIZE);
            r.setFill(color);
            g.getChildren().add(r);
        }
    }
}
