package application;

import java.io.*;
import java.util.*;

public class WordManager {
    private List<String> words;

    public WordManager(String filePath) {
        words = new ArrayList<>();
        loadWords(filePath);
    }

    private void loadWords(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = br.readLine()) != null) {
                words.add(word.trim());
            }
        } catch (IOException e) {
            words = Arrays.asList("java", "keyboard", "practice", "speed", "accuracy", "challenge", "performance");
        }
    }

    public String getRandomWord(String difficulty) {
        Random rand = new Random();

        List<String> filtered = new ArrayList<>();
        for (String w : words) {
            if (difficulty.equals("Easy") && w.length() <= 5) filtered.add(w);
            else if (difficulty.equals("Medium") && w.length() > 5 && w.length() <= 8) filtered.add(w);
            else if (difficulty.equals("Hard") && w.length() > 8) filtered.add(w);
        }

        if (filtered.isEmpty()) filtered = words; // fallback

        return filtered.get(rand.nextInt(filtered.size()));
    }
}
