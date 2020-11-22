package fi.tommijuslin.blocks;


import java.util.Random;

public class Block {

    private int x;
    private int y;
    Random rand = new Random();

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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}