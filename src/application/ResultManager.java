package application;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResultManager {
    private final String csvPath;

    public ResultManager(String csvPath) {
        this.csvPath = csvPath;
        File file = new File(csvPath);
        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(file)) {
                pw.println("DateTime,WPM,Accuracy");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveResult(int wpm, double accuracy) {
        try (FileWriter fw = new FileWriter(csvPath, true)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            fw.write(dtf.format(LocalDateTime.now()) + "," + wpm + "," + String.format("%.2f", accuracy) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
