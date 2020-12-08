package fi.tommijuslin.ui;

import fi.tommijuslin.logic.Board;
import fi.tommijuslin.logic.Grid;
import fi.tommijuslin.logic.Score;
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
import javafx.scene.text.Font;

public class Tetris extends Application {

    private double time;
    private final int width = Board.BOARD_WIDTH * Board.BLOCK_SIZE;
    private final int height = Board.BOARD_HEIGHT * Board.BLOCK_SIZE;
    private final Label lblScore = new Label();
    private final VBox vboxScores = new VBox(10);
    private final Score score = new Score(lblScore.getText());
    private final Pane root = new Pane();
    private final Grid grid = new Grid();
    private final Board board = new Board(root, grid, score);
    
    @Override
    public void start(Stage stage) {
        score.createScoreFile();
        score.updateAndListScores(vboxScores);   
        
        GridPane gridPane = new GridPane();
        
        Label lblScoreText = new Label("Score: ");
        lblScoreText.setFont(new Font("Arial", 24));
        
        gridPane.add(lblScoreText, 0, 0);
        gridPane.add(lblScore, 1, 0);
        
        lblScore.setFont(new Font("Arial", 24));
        lblScore.textProperty().bind(score.scoreProperty().asString());
        
        Button btnResume = new Button("Resume");
        Button btnStart = new Button("Start");
        Button btnExit = new Button("Exit");
        Button btnScore = new Button("High Score");
        Button btnBack = new Button("Back");
        btnResume.setVisible(false);
        
        Label title = new Label("T E T R I S");
        title.setStyle("-fx-font-size: 50px;");
        
        VBox vbox = new VBox(10, title, btnResume, btnStart, btnScore, btnExit);
        vbox.setAlignment(Pos.CENTER);

        vboxScores.getChildren().add(btnBack);
        btnBack.toFront();
        vboxScores.setAlignment(Pos.CENTER);
        
        Scene gameScene = new Scene(root, width, height);
        Scene menuScene = new Scene(vbox, width, height);
        Scene scoreScene = new Scene(vboxScores, width, height);
        
        gameScene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
        menuScene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
        scoreScene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
        
        root.getChildren().add(gridPane);
        
        stage.setScene(menuScene);
        stage.show();

        AnimationTimer t = new AnimationTimer() {
            @Override
            public void handle(long now) {
                time += 0.017;

                if (board.gameOver) {
                    stop();
                    stage.setScene(menuScene);
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

                        if (e.getCode() == KeyCode.UP) {
                            board.rotate();
                        }
                        
                        if (e.getCode() == KeyCode.ESCAPE) {
                            stage.setScene(menuScene);
                            btnResume.setVisible(!board.gameOver);
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
            board.initGame();
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