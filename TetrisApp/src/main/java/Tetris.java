
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

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
        // board.spawnTetromino();
        board.spawnSpecificTetromino(Shape.S, 6, 0);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                time += 0.017;
                if (time >= 0.7) {
                    board.updateBoard(root);
                    scene.setOnKeyPressed(e -> {
                        if (e.getCode() == KeyCode.RIGHT) {
                            board.move(Board.Direction.RIGHT);
                        } else if (e.getCode() == KeyCode.LEFT) {
                            board.move(Board.Direction.LEFT);
                        } else if (e.getCode() == KeyCode.DOWN) {
                            board.move(Board.Direction.DOWN);
                        }
                        board.updateBoard(root);
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