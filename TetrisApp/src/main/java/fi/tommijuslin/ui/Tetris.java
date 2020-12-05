package fi.tommijuslin.ui;

import fi.tommijuslin.logic.Board;
import fi.tommijuslin.score.Score;
import java.util.Collections;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Tetris extends Application {

    private double time;
    private final Pane root = new Pane();
    private final Board board = new Board(root);
    private final int width = Board.BOARD_WIDTH * Board.BLOCK_SIZE;
    private final int height = Board.BOARD_HEIGHT * Board.BLOCK_SIZE;
    private final Label lblScore = new Label();
    private final VBox vboxScores = new VBox(10);
    private final Score score = new Score(lblScore, vboxScores);
    
    @Override
    public void start(Stage stage) {
        score.createScoreFile();
        score.updateAndListScores(vboxScores);
        
        GridPane gridPane = new GridPane();
        
        gridPane.add(new Label("Score: "), 0, 0);
        gridPane.add(lblScore, 1, 0);
        lblScore.textProperty().bind(board.scoreProperty().asString());
        
        Button btnResume = new Button("Resume");
        Button btnStart = new Button("Start");
        Button btnExit = new Button("Exit");
        Button btnScore = new Button("High Score");
        Button btnBack = new Button("Back");
        btnResume.setVisible(false);
        
        VBox vbox = new VBox(10, btnResume, btnStart, btnScore, btnExit);
        vbox.setAlignment(Pos.CENTER);

        vboxScores.getChildren().add(btnBack);
        btnBack.toFront();
        vboxScores.setAlignment(Pos.CENTER);
        
        Scene gameScene = new Scene(root, width, height);
        Scene menuScene = new Scene(vbox, width, height);
        Scene scoreScene = new Scene(vboxScores, width, height);
        
        root.getChildren().add(gridPane);
        
        stage.setScene(menuScene);
        stage.show();

        AnimationTimer t = new AnimationTimer() {
            @Override
            public void handle(long now) {
                time += 0.017;

                if (board.gameOver) {
                    stop();
                    score.saveScore();
                    score.updateAndListScores(vboxScores);
                    btnBack.toFront();
                }
                
                if (!board.gameOver) {
                    gameScene.setOnKeyPressed(e -> {
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
                        
                        if (e.getCode() == KeyCode.ESCAPE) {
                            stage.setScene(menuScene);
                            if (board.gameOver) {
                                btnResume.setVisible(false);
                            } else {
                                btnResume.setVisible(true);
                            }
                            stop();
                        }

                        board.updateBoard();
                    });
                }
                
                if (time >= 0.7) {
                    board.updateBoard();
                    board.move(0,1);
                    time = 0;
                }
            }
        };

        btnResume.setOnMouseClicked(e -> {
            stage.setScene(gameScene);
            t.start();
        });
        
        btnStart.setOnMouseClicked(e -> {
            board.initGame(root);
            stage.setScene(gameScene);
            t.start();
        });
        
        btnScore.setOnMouseClicked(e -> {
            stage.setScene(scoreScene);
        });
        
        btnBack.setOnMouseClicked(e -> {
            stage.setScene(menuScene);
        });
        
        btnExit.setOnMouseClicked(e -> {
            Platform.exit();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}