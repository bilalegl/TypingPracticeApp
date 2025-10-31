package application;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class TypingApp {
    private Label wordLabel;
    private TextField inputField;
    private Label timerLabel;
    private Label wpmLabel;
    private Label accuracyLabel;
    private ComboBox<String> difficultyBox;

    private WordManager wordManager;
    private ResultManager resultManager;
    private ChartManager chartManager;

    private int correctWords = 0;
    private int totalWords = 0;
    private int timeLeft = 60;
    private Timer timer;

    public TypingApp(Stage stage) {
        wordManager = new WordManager("data/words.txt");
        resultManager = new ResultManager("results/history.csv");
        chartManager = new ChartManager(resultManager);

        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 30; -fx-background-color: linear-gradient(to bottom, #1e1e2f, #282845);");

        Label title = new Label("Typing Practice App");
        title.setFont(Font.font("Consolas", FontWeight.BOLD, 36));
        title.setTextFill(Color.LIGHTBLUE);

        wordLabel = new Label("Press Start to Begin!");
        wordLabel.setTextFill(Color.WHITE);
        wordLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 40));

        inputField = new TextField();
        inputField.setPromptText("Type the word here...");
        inputField.setFont(Font.font("Consolas", 20));
        inputField.setAlignment(Pos.CENTER);
        inputField.setDisable(true);
        inputField.textProperty().addListener((obs, oldText, newText) -> checkTyping(newText));

        timerLabel = new Label("Time: 60s");
        timerLabel.setTextFill(Color.YELLOW);
        wpmLabel = new Label("WPM: 0");
        wpmLabel.setTextFill(Color.LIGHTGREEN);
        accuracyLabel = new Label("Accuracy: 100%");
        accuracyLabel.setTextFill(Color.LIGHTBLUE);

        HBox statsBox = new HBox(20, timerLabel, wpmLabel, accuracyLabel);
        statsBox.setAlignment(Pos.CENTER);

        difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("Easy", "Medium", "Hard");
        difficultyBox.setValue("Easy");
        difficultyBox.setStyle("-fx-font-size: 16px;");

        Button startButton = new Button("Start Test");
        startButton.setOnAction(e -> startTest());
        startButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 18px;");

        Button chartButton = new Button("View Progress");
        chartButton.setOnAction(e -> chartManager.showChartWindow());
        chartButton.setStyle("-fx-background-color: #2196f3; -fx-text-fill: white; -fx-font-size: 18px;");

        HBox controlBox = new HBox(20, difficultyBox, startButton, chartButton);
        controlBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title, wordLabel, inputField, statsBox, controlBox);

        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Typing Practice App");
        stage.show();
    }

    private void startTest() {
        correctWords = 0;
        totalWords = 0;
        inputField.setDisable(false);
        inputField.setText("");
        inputField.requestFocus();

        String difficulty = difficultyBox.getValue();
        if (difficulty.equals("Easy")) timeLeft = 60;
        else if (difficulty.equals("Medium")) timeLeft = 45;
        else timeLeft = 30;

        timerLabel.setText("Time: " + timeLeft + "s");
        wordLabel.setText(wordManager.getRandomWord(difficulty));

        if (timer != null) timer.cancel();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    timeLeft--;
                    timerLabel.setText("Time: " + timeLeft + "s");
                    if (timeLeft <= 0) {
                        timer.cancel();
                        endTest();
                    }
                });
            }
        }, 1000, 1000);
    }

private void checkTyping(String typed) {
    String target = wordLabel.getText();

    if (typed.equals(target)) {
        correctWords++;
        totalWords++;

        Platform.runLater(() -> inputField.setText("")); // ✅ safe reset
        wordLabel.setText(wordManager.getRandomWord(difficultyBox.getValue()));
        wpmLabel.setText("WPM: " + correctWords);
        accuracyLabel.setText("Accuracy: " + ((correctWords * 100) / totalWords) + "%");

    } else if (typed.length() >= target.length()) {
        totalWords++;

        Platform.runLater(() -> inputField.setText("")); // ✅ safe reset
        wordLabel.setText(wordManager.getRandomWord(difficultyBox.getValue()));
        accuracyLabel.setText("Accuracy: " + ((correctWords * 100) / totalWords) + "%");
    }
}


    private void endTest() {
        inputField.setDisable(true);
        double accuracy = totalWords == 0 ? 0 : (correctWords * 100.0 / totalWords);
        resultManager.saveResult(correctWords, accuracy);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Test Over!\nWPM: " + correctWords + "\nAccuracy: " + String.format("%.2f", accuracy) + "%");
        alert.showAndWait();
    }
}
