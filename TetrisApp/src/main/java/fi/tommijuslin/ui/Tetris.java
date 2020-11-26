package fi.tommijuslin.ui;

import fi.tommijuslin.blocks.Shape;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import fi.tommijuslin.logic.Board;

public class Tetris extends Application {

    private double time;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Canvas canvas = new Canvas(Board.BOARD_WIDTH * Board.BLOCK_SIZE, Board.BOARD_HEIGHT * Board.BLOCK_SIZE);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Board board = new Board();
        board.spawn(Shape.getRandomShape());

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                time += 0.017;
                
                scene.setOnKeyPressed(e -> {
                        if (e.getCode() == KeyCode.RIGHT) {
                            board.move(1, 0);
                        }
                        
                        if (e.getCode() == KeyCode.LEFT) {
                            board.move(-1, 0);
                        }
                        
                        if (e.getCode() == KeyCode.DOWN) {
                            board.move(0, 1);
                        }
                        
                        if (e.getCode() == KeyCode.SPACE) {
                            board.rotate();
                        }
                        
                        board.updateBoard(root);
                    });
                
                if (time >= 0.7) {
                    board.updateBoard(root);
                    board.move(0,1);
                    time = 0;
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        launch();
    }

}