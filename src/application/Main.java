package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        TypingApp app = new TypingApp();
        app.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
