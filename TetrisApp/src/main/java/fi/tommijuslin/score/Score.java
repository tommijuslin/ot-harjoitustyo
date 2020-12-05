
package fi.tommijuslin.score;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Score {
    private final Label lblScore;
    
    public Score(Label lblScore, VBox vbox) {
        this.lblScore = lblScore;
    }
    
    public void createScoreFile() {
        try {
            File file = new File("score.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public int getLowestScore() {
        int lowestScore = Integer.MAX_VALUE;
        String s = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("score.txt"));
            while ((s = reader.readLine()) != null) {
                if (lowestScore > Integer.parseInt(s)) {
                    lowestScore = Integer.parseInt(s);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return lowestScore;
    }
    
    public int getNumberOfLines() {
        int lines = 0;
        String s = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("score.txt"));
            while ((s = reader.readLine()) != null) {
                lines++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return lines;
    }
    
    public void saveScore() {
        int lowestScore = getLowestScore();
        int lines = getNumberOfLines();
        
        if (Integer.parseInt(lblScore.getText()) > lowestScore && lines == 10) {
            try {
                List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("score.txt")));
                for (int i = 0; i < fileContent.size(); i++) {
                    if (fileContent.get(i).equals(Integer.toString(lowestScore))) {
                        fileContent.set(i, lblScore.getText());
                        break;
                    }
                }
                Files.write(Paths.get("score.txt"), fileContent);
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        
        if (lines < 10) {
            try {
                String filename = "score.txt";
                FileWriter fw = new FileWriter(filename, true);
                if (!lblScore.getText().equals("0")) {
                    fw.write(lblScore.getText() + System.lineSeparator());
                }
                fw.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
    
    public List<Integer> getScores() {
        List<Integer> scores = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get("score.txt"))) {
            while (scanner.hasNextLine()) {
                scores.add(Integer.parseInt(scanner.nextLine()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return scores;
    }
    
    public void updateAndListScores(VBox vboxScores) {
        vboxScores.getChildren().removeIf(n -> n instanceof Text);
        List<Integer> scores = getScores();
        Collections.sort(scores);
        Collections.reverse(scores);
        for (Integer score : scores) {
            Text text = new Text(Integer.toString(score));
            vboxScores.getChildren().add(text);
        }
    }
}
