import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ScoreBoard {
    private Map<String, Integer> scores;
    private int maxSize;

    public ScoreBoard(int maxSize) {
        this.scores = new HashMap<>();
        this.maxSize = maxSize;
    }

    public void addScore(String playerName, int score) {
        if (scores.containsKey(playerName)) {
            int existingScore = scores.get(playerName);
            scores.put(playerName, existingScore + score);
        } else {
            scores.put(playerName, score);
        }
    }

    public List<Map.Entry<String, Integer>> getTopScores() {
        List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return sortedScores.subList(0, Math.min(maxSize, sortedScores.size()));
    }

    public void saveScoresToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            List<Map.Entry<String, Integer>> topScores = getTopScores();
            for (Map.Entry<String, Integer> entry : topScores) {
                writer.printf("%s %d%n", entry.getKey(), entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error saving scores to file: " + e.getMessage());
        }
    }

    public List<Map.Entry<String, Integer>> getTopScores(int numScores) {
        List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return sortedScores.subList(0, Math.min(numScores, sortedScores.size()));
    }


}
