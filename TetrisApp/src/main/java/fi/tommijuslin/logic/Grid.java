package fi.tommijuslin.logic;

import fi.tommijuslin.blocks.Block;
import fi.tommijuslin.blocks.Tetromino;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    public static int[][] grid = new int[Board.BOARD_HEIGHT][Board.BOARD_WIDTH];
    public final List<Integer> rowsToDelete = new ArrayList<>();
    
    /**
     * Tetrominojen kulkua seuraavan taulukon alustus
     */
    
    public void initGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }
    }
    
    /**
     * Lisää annetun palikan koordinaatit taulukkoon
     * f
     * @param block palikka
     */
    
    public void addToGrid(Block block) {
        grid[block.getY()][block.getX()]++;
    }
    
    /**
     * Poistaa annetun palikan koordinaatit taulukosta
     * 
     * @param block palikka
     */
    
    public void removeFromGrid(Block block) {
        grid[block.getY()][block.getX()]--;
    }
    
    /**
     * Käsittelee täydet rivit
     * 
     * @param tetrominos Kaikki tetrominot sisältävä taulukko 
     */
    
    public void handleFullRows(List<Tetromino> tetrominos) {
        checkRows();
        if (!rowsToDelete.isEmpty()) {
            deleteRows(tetrominos);
        }
    }
    
    private void checkRows() {
        boolean lines = true;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] != 1) {
                    lines = false;
                    continue;
                }
                
                if (lines && col == grid[row].length - 1) {
                    rowsToDelete.add(row);
                }
            }
            lines = true;
        }
    }
    
    private void deleteRows(List<Tetromino> tetrominos) {
        for (int row = 0; row < rowsToDelete.size(); row++) {
            for (Tetromino t : tetrominos) {
                List<Block> temp = t.blocks;
                List<Block> blocksToRemove = new ArrayList<>();
                for (Block b : temp) {
                    if (b.getY() == rowsToDelete.get(row)) {
                        removeFromGrid(b);
                        blocksToRemove.add(b);
                    } else if (b.getY() < rowsToDelete.get(row)) {
                        Block tempBlock = b;
                        removeFromGrid(b);
                        tempBlock.setY(tempBlock.getY() + 1);
                        addToGrid(tempBlock);
                    }
                }
                t.blocks.removeAll(blocksToRemove);
            }
        }
    }
    
    public void initRowsToClear() {
        rowsToDelete.clear();
    }
    
    public int[][] getGrid() {
        return this.grid;
    }
}
