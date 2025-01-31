# Coding Problems Tracker Application

## 📋 Overview

The **Coding Problems Tracker Application** is a handy tool designed for competitive programmers and coding enthusiasts who want to practice coding problems randomly from a list. The application randomly selects a problem from your provided list and opens the corresponding link in your browser. It also allows you to track your progress by marking problems as **Solved**, **Unsolved**, or **Attempted** and measures the time you spent solving each problem. With this app, you can improve your problem-solving skills by challenging yourself with random problems, without relying on topic tags or hints.

---

## 🚀 Features

- **Random Problem Selection:** Automatically selects a random unsolved problem from your list.
- **Progress Tracking:** Mark problems as **Solved**, **Attempted**, or **Unsolved**.
- **Time Tracking:** Records the time taken to solve a problem and displays it.
- **Session Summary:** Displays the number of problems solved in the current session.
- **File Export & Import:** Save your progress to a file and reload it when you restart the application.
- **Custom Problem List:** Load your own list of problem URLs from a `.txt` file.

---

## 🧰 File Structure

```
CodingProblemSelectorApp/
│
├── src/
│   ├── com/
│       ├── vivek/
│           ├── codingapp/
│               ├── Application.java
│               ├── models/
│                   └── ProblemLink.java
│               ├── repository/
│                   └── ProblemRepository.java
│               ├── selectors/
│                   └── RandomProblemSelector.java
│
├── exported_problems.txt
└── README.md
```

---

## 📂 Input File Format

The application loads problems from an input file named `problems.txt`. The format of each line in the file should be:

```
<Problem URL>,<Status>,<Time Taken>
```

Example:

```
https://www.geeksforgeeks.org/factorial-of-a-number/,Unsolved,0
https://leetcode.com/problems/two-sum/,Solved,30
```

Place the `problems.txt` file in the root directory before starting the application.

---

## 🖥️ How to Run

1. Clone this repository:
   ```
   git clone https://github.com/Vive007/Coding-Problems-Tracker.git
   ```
2. Navigate to the project directory:
   ```
   cd Coding-Problems-Tracker
   ```
3. Compile the Java files:
   ```
   javac src/com/vivek/codingapp/Application.java
   ```
4. Run the application:
   ```
   java src/com/vivek/codingapp/Application
   ```

---

## 📝 How to Use

1. **Click the 'Random' button** to select a random problem from your list.
2. **Click 'Mark as Solved'** after solving the problem. The application will ask for the time taken to solve the problem.
3. **Click 'Export'** to save your progress to a file.
4. **Restart the application**, and it will reload your progress from the exported file.
5. **Track your session summary** to see how many problems you solved in the current session.

---

## ✅ Benefits

- Improves your problem-solving ability by presenting problems randomly.
- Helps you track your progress and time management.
- Encourages solving problems without relying on topic tags or hints.
- Keeps your practice sessions organized and efficient.

---

## 💡 Future Enhancements

- Add more filters to select problems based on difficulty.
- Integrate with online coding platforms to fetch problems automatically.
- Provide graphical reports for time spent on solving problems.

---

## 🧑‍💻 Design Pattern Used

The **Coding Problem Selector Application** leverages the **Singleton Pattern** and **Strategy Pattern** to manage the problem selection and progress tracking:

1. **Singleton Pattern:** The application uses the Singleton pattern for the `ProblemRepository` class to ensure that there is only one instance responsible for loading, saving, and updating problem data throughout the entire application lifecycle. This helps in managing data consistency and avoiding duplicate instances.

2. **Strategy Pattern:** The **RandomProblemSelector** class implements the Strategy pattern to select problems randomly. This allows the flexibility to change the problem selection algorithm easily in the future, such as by adding filters for difficulty or topics, without affecting other parts of the application.

---

## 🤝 Contributing

Contributions are welcome! Feel free to open issues or submit pull requests to improve the application.

---

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for more details.

---

## 👨‍💻 Developed By

**Vivek Kumar**

---

## 📝 Notes

- Make sure to load the `exported_problems.txt` file before starting the application to retain your progress.
- Ensure that your input file follows the correct format to avoid errors.

---

Happy Coding! 🎉

