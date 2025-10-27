package application;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class TypingApp {

    private TextFlow sentenceFlow;
    private Label timerLabel, wpmLabel, accuracyLabel;
    private ComboBox<String> difficultyBox;
    private Button startButton;

    private String sentence;
    private int currentIndex = 0;
    private int totalTyped = 0;
    private int correctTyped = 0;

    private long startTime;
    private boolean testRunning = false;
    private AnimationTimer timer;

    public void start(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #1E1E2F;");

        Label title = new Label("Typing Practice App (UI Test)");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(Color.CYAN);

        difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("Easy", "Medium", "Hard");
        difficultyBox.setValue("Easy");

        sentenceFlow = new TextFlow();
        sentenceFlow.setTextAlignment(TextAlignment.CENTER);
        sentenceFlow.setPrefWidth(700);

        startButton = new Button("Start Test");
        startButton.setStyle("-fx-font-size: 16px; -fx-background-color: #007ACC; -fx-text-fill: white;");

        HBox buttonBox = new HBox(10, startButton);
        buttonBox.setAlignment(Pos.CENTER);

        timerLabel = new Label("Time: 0s");
        wpmLabel = new Label("WPM: 0");
        accuracyLabel = new Label("Accuracy: 0%");
        timerLabel.setTextFill(Color.LIGHTBLUE);
        wpmLabel.setTextFill(Color.LIGHTBLUE);
        accuracyLabel.setTextFill(Color.LIGHTBLUE);

        HBox statsBox = new HBox(20, timerLabel, wpmLabel, accuracyLabel);
        statsBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title, difficultyBox, sentenceFlow, buttonBox, statsBox);

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Typing Practice App");
        stage.show();

        // --- Start button action ---
        startButton.setOnAction(e -> startTest(scene));

        // --- Typing key events ---
        scene.setOnKeyTyped(event -> {
            if (!testRunning) return;
            if (currentIndex >= sentence.length()) return;

            char typed = event.getCharacter().charAt(0);
            Text currentChar = (Text) sentenceFlow.getChildren().get(currentIndex);
            char correctChar = sentence.charAt(currentIndex);

            totalTyped++;

            if (typed == correctChar) {
                currentChar.setFill(Color.LIMEGREEN);
                currentIndex++;
                correctTyped++;
            } else {
                currentChar.setFill(Color.RED);
            }

            updateStats();
            if (currentIndex == sentence.length()) stopTest();
        });
    }

    private void startTest(Scene scene) {
        // Sample offline sentence (later you can load random sentences from file)
        sentence = "The quick brown fox jumps over the lazy dog.";

        // Prepare sentence text flow
        sentenceFlow.getChildren().clear();
        for (char c : sentence.toCharArray()) {
            Text t = new Text(String.valueOf(c));
            t.setStyle("-fx-font-size: 24px;");
            t.setFill(Color.LIGHTGRAY);
            sentenceFlow.getChildren().add(t);
        }

        // Reset values
        currentIndex = 0;
        totalTyped = 0;
        correctTyped = 0;
        startButton.setDisable(true);
        testRunning = true;
        startTime = System.currentTimeMillis();
        startTimer();
        scene.getRoot().requestFocus(); // Focus on scene for typing
    }

    private void startTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (testRunning) {
                    long elapsed = (System.currentTimeMillis() - startTime) / 1000;
                    timerLabel.setText("Time: " + elapsed + "s");
                }
            }
        };
        timer.start();
    }

    private void stopTest() {
        testRunning = false;
        timer.stop();
        startButton.setDisable(false);
    }

    private void updateStats() {
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
        if (elapsed == 0) elapsed = 1;
        double minutes = elapsed / 60.0;
        int wpm = (int) ((correctTyped / 5.0) / minutes);
        double accuracy = totalTyped == 0 ? 0 : (correctTyped * 100.0 / totalTyped);

        wpmLabel.setText("WPM: " + wpm);
        accuracyLabel.setText(String.format("Accuracy: %.1f%%", accuracy));
    }
}
