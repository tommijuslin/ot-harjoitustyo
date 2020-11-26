package fi.tommijuslin.blocks;

import fi.tommijuslin.logic.Board;
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Tetromino {
    
    public Block[] blocks = new Block[4];
//    private final Image color;
    Random rand = new Random();
    int x, y;
    
//    Image blueBlock = new Image("file:images/Blue_Block.png");
//    Image cyanBlock = new Image("file:images/Cyan_Block.png");
//    Image greenBlock = new Image("file:images/Green_Block.png");
//    Image orangeBlock = new Image("file:images/Orange_Block.png");
//    Image purpleBlock = new Image("file:images/Purple_Block.png");
//    Image redBlock = new Image("file:images/Red_Block.png");
//    Image yellowBlock = new Image("file:images/Yellow_Block.png");
    
//    private final Image[] colors = {
//        blueBlock, cyanBlock, greenBlock, orangeBlock, purpleBlock, redBlock, yellowBlock
//    };
    
    private final Color color;
    private final Color[] colors = {
        Color.CYAN, Color.DARKBLUE, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.PURPLE, Color.RED
    };

    public Tetromino(Block[] blocks) {
        this.blocks = blocks;
        this.color = colors[rand.nextInt(7)];
        this.x = 0;
        this.y = 0;
    }
    
    public void draw(Group g) {
        for (int i = 0; i < blocks.length; i++) {
            Rectangle r = new Rectangle();
            r.setHeight(Board.BLOCK_SIZE);
            r.setWidth(Board.BLOCK_SIZE);
            r.setTranslateX(blocks[i].getX() * Board.BLOCK_SIZE);
            r.setTranslateY(blocks[i].getY() * Board.BLOCK_SIZE);
//            r.setFill(new ImagePattern(color));
//            r.setStyle("-fx-stroke: black; -fx-stroke-width: 3");
            r.setFill(color);
            g.getChildren().add(r);
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