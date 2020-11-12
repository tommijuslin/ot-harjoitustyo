package tetrisapp;

import javafx.scene.canvas.GraphicsContext;

public class Block {

    public int x;
    public int y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
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
        gc.fillRect(this.x * Board.BLOCK_SIZE, this.y * Board.BLOCK_SIZE, Board.BLOCK_SIZE, Board.BLOCK_SIZE);
    }
}
