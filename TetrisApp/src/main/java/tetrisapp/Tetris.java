package tetrisapp;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Tetris extends Application {

    public GraphicsContext gc;

    private double time;

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Canvas canvas = new Canvas(Board.BOARD_WIDTH * Board.BLOCK_SIZE, Board.BOARD_HEIGHT * Board.BLOCK_SIZE);
        gc = canvas.getGraphicsContext2D();
        pane.getChildren().add(canvas);

        Board board = new Board();

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();

        board.spawnBlock(4, 0);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                time += 0.017;
                if (time >= 0.7) {
                    board.updateBoard(gc);
                    scene.setOnKeyPressed(e -> {
                        if (e.getCode() == KeyCode.RIGHT) {
                            board.move(Board.Direction.RIGHT);
                        } else if (e.getCode() == KeyCode.LEFT) {
                            board.move(Board.Direction.LEFT);
                        } else if (e.getCode() == KeyCode.DOWN) {
                            board.move(Board.Direction.DOWN);
                        }
                        board.updateBoard(gc);
                    });
                    board.move(Board.Direction.DOWN);
                    time = 0;
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        launch();
    }

}