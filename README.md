# Typing Practice App (JavaFX)

A simple yet feature-rich **offline Typing Practice App** built completely in **JavaFX (no FXML)** using pure Java code.  
The goal is to help users improve their typing speed, accuracy, and consistency — all in real time.

---

## 🚧 Project Status
✅ **Currently Working On:**  
- Real-time typing test  
- Per-character feedback (Green for correct, Red for wrong)  
- Blinking cursor (under active letter)  
- Timer, WPM, and Accuracy tracking  

🧩 **Next Planned Features:**
- Difficulty modes (Easy / Medium / Hard)
- Sentence randomization (from offline text file)
- Progress saving and result history
- Charts for performance visualization
- Multi-session statistics dashboard

---

## 🖥️ Current UI Preview (Stage 1)
- Dark background with cyan title  
- Sentence display using `TextFlow`
- Typing input handled via `KeyTyped` events (no text box)
- Blinking cyan cursor below the active character
- Real-time WPM and Accuracy updates

---

## ⚙️ Tech Stack
- **Language:** Java  
- **Framework:** JavaFX (no SceneBuilder / no FXML)  
- **IDE:** Visual Studio Code  
- **Java Version:** JDK 23  
- **JavaFX Version:** JavaFX 21  

---

## 🧠 How It Works (Current Stage)
1. Click **Start Test** to begin.
2. The app shows a predefined sentence (offline).
3. Type directly — letters turn **green** if correct or **red** if wrong.
4. The cursor blinks under the current character.
5. When the sentence is complete, the test stops and shows:
   - **WPM (Words Per Minute)**
   - **Accuracy %**
   - **Elapsed Time**

## 🚀 How to Run

### 1️⃣ Clone or Download
```bash
git clone https://github.com/<your-username>/TypingPracticeApp.git
cd TypingPracticeApp
