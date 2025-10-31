package application;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;

public class ChartManager {
    private final String csvPath;

    public ChartManager(ResultManager rm) {
        this.csvPath = rm == null ? "results/history.csv" : "results/history.csv";
    }

    public void showChartWindow() {
        Stage stage = new Stage();
        stage.setTitle("Typing Progress");

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Test Number");
        yAxis.setLabel("WPM");

        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("Typing Speed Over Time");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("WPM History");

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line;
            br.readLine(); // skip header
            int index = 1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    series.getData().add(new XYChart.Data<>(index++, Integer.parseInt(parts[1])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        chart.getData().add(series);
        stage.setScene(new Scene(chart, 600, 400));
        stage.show();
    }
}
