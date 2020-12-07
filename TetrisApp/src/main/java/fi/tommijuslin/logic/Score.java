package fi.tommijuslin.logic;

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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Score {
    private final String lblScore;
    private final IntegerProperty score = new SimpleIntegerProperty();
    
//    public Score(Label lblScore) {
//        this.lblScore = lblScore;
//    }
    
    public Score(String lblScore) {
        this.lblScore = lblScore;
    }
    
    /**
     * Lisää pisteitä tuhottavien rivien määrän mukaisesti
     */
    
    public void incrementScore(int numberOfRows) {
        switch (numberOfRows) {
            case 4:
                this.score.set(score.get() + 1200);
                break;
            case 3:
                this.score.set(score.get() + 300);
                break;
            case 2:
                this.score.set(score.get() + 100);
                break;
            case 1:
                this.score.set(score.get() + 40); 
        }
    }
    
    public IntegerProperty scoreProperty() {
        return score;
    }
    
    public void initScore() {
        this.score.set(0);
    }
    
    /**
     * Luo tekstitiedoston pistetilanteen seuraamista varten
     */
    
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
    
    /**
     * Etsii pienimmän pistemäärän score.txt-tiedostosta
     * @return pienin pistemäärä
     */
    
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
            System.out.println("An error occurred: ");
            e.printStackTrace();
        }
        
        return lowestScore;
    }
    
    /**
     * Laskee syötettyjen pisteiden määrän score.txt-tiedostosta
     * 
     * @return syötettyjen pisteiden määrä
     */
    
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
    
    /**
     * Tallentaa saavutetun pistemäärän tiedostoon. Pisteet tallennetaan vain
     * jos pisteitä on syötetty yhteensä alle 10 kappaletta tai saavutettu
     * pistemäärä on suurempi kuin listalta löytyvä pienin pistemäärä.
     */
    
    public void saveScore() {
        int lowestScore = getLowestScore();
        int lines = getNumberOfLines();
        
        if (score.get() > lowestScore && lines == 10) {
            try {
                List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("score.txt")));
                for (int i = 0; i < fileContent.size(); i++) {
                    if (fileContent.get(i).equals(Integer.toString(lowestScore))) {
                        fileContent.set(i, Integer.toString(score.get()));
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
                if (!Integer.toString(score.get()).equals("0")) {
                    fw.write(Integer.toString(score.get()) + System.lineSeparator());
                }
                fw.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Palauttaa kaikki scores.txt-tiedosta löytyvät pisteet taulukossa
     * 
     * @return pisteet
     */
    
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
    
    /**
     * Päivittää ja järjestää pisteet
     * 
     * @param vboxScores alusta jolle pisteet tallennetaan
     */
    
    public void updateAndListScores(VBox vboxScores) {
        vboxScores.getChildren().removeIf(n -> n instanceof Text);
        List<Integer> scores = getScores();
        Collections.sort(scores);
        Collections.reverse(scores);
        int i = 1;
        for (Integer score : scores) {
            Text text = new Text(Integer.toString(i) + ".\t\t\t" + Integer.toString(score));
            vboxScores.getChildren().add(text);
            i++;
        }
    }
    
    public int getScore() {
        return this.score.get();
    }
}
