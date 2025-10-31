# 🧠 Typing Practice App (JavaFX)

A simple yet feature-rich **offline Typing Practice App** built completely in **JavaFX (no FXML)** using pure Java code.  
The goal is to help users improve their **typing speed**, **accuracy**, and **consistency** — all in real time.

---

## 🚧 Project Status
✅ **Currently Working On:**  
- Real-time typing test  
- Word-by-word accuracy tracking (Green = correct, Red = incorrect)  
- Difficulty levels (**Easy / Medium / Hard**)  
- Timer, WPM, and Accuracy tracking  
- Local CSV-based progress saving  
- Performance visualization using charts  

🧩 **Next Planned Features:**  
- Sentence typing mode  
- Multi-session statistics dashboard  
- Progress analytics and leaderboard  
- Custom test durations and UI themes  

---

## 🖥️ Current UI Preview (Stage 2)
- Dark gradient background with cyan title  
- Word display using `Label`  
- Input handled via `TextField` (auto-cleared per word)  
- Real-time WPM and Accuracy updates  
- Difficulty selector and Start button  
- “View Progress” button showing performance chart  

---

## ⚙️ Tech Stack
- **Language:** Java  
- **Framework:** JavaFX (no SceneBuilder / no FXML)  
- **IDE:** Visual Studio Code  
- **Java Version:** JDK 23  
- **JavaFX Version:** JavaFX 21  

---

## 🧠 How It Works
1️⃣ Click **Start Test** to begin.  
2️⃣ Choose your **difficulty level** — Easy, Medium, or Hard.  
3️⃣ The app shows a random word from the offline word file (`data/words.txt`).  
4️⃣ Type the word correctly to earn points — next word appears instantly.  
5️⃣ The timer runs based on difficulty:  
   - Easy = 60s  
   - Medium = 45s  
   - Hard = 30s  
6️⃣ When time runs out, you’ll see:  
   - **WPM (Words Per Minute)**  
   - **Accuracy %**  
7️⃣ All test results are saved automatically in `results/history.csv`.  
8️⃣ You can view your past performance anytime via the **Progress Chart**.

---

## 📊 Example Result File (`results/history.csv`)
```csv
DateTime,WPM,Accuracy
2025-11-01 14:23:15,42,95.83
2025-11-01 14:27:09,51,97.00
```

#Project Structure
TypingPracticeApp/
├── src/application/
│   ├── Main.java
│   ├── TypingApp.java
│   ├── WordManager.java
│   ├── ResultManager.java
│   └── ChartManager.java
├── data/
│   └── words.txt
├── results/
│   └── history.csv
└── README.md

git clone https://github.com/bilalegl/TypingPracticeApp.git
cd TypingPracticeApp

javac --module-path "path-to-javafx-lib" --add-modules javafx.controls,javafx.fxml src/application/*.java
java --module-path "path-to-javafx-lib" --add-modules javafx.controls,javafx.fxml application.Main

#👨‍💻 Author

Muhammad Bilal
Software Engineering Student | Java Developer

2025-11-01 14:23:15,42,95.83
2025-11-01 14:27:09,51,97.00
