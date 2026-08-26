# 🧩 Java Sudoku Solver & Swing GUI

An object-oriented Java application implementing a 9x9 Sudoku solver using a recursive backtracking algorithm, an interactive Swing graphical interface, and an automated JUnit test suite.

## Features
* **Recursive Backtracking Solver:** Dynamically solves valid 9x9 Sudoku puzzles or identifies unsolvable board configurations.
* **Interactive Swing GUI:** Custom 9x9 visual board with distinct 3x3 sub-grid borders, real-time status messaging, clear action buttons, and error popups[cite: 5].
* **Strict Domain Validation:** Validates row, column, and sub-grid constraints along with input range checks (`1-9` or empty)[cite: 2, 5].
* **Defensive Data Handling:** Uses deep array copying (`setGrid`, `getGrid`) to protect internal grid state against external mutations[cite: 2].
* **Comprehensive Test Suite:** Includes automated JUnit test coverage for boundary checks, index exceptions, unsolvable puzzles, and memory safety[cite: 1, 7].

## Engineering Process
1. **Interface & Model Design:** Defined the core contract via `SudokuSolver` interface[cite: 6] and implemented the algorithmic logic inside `MySudokuSolver` using backtracking[cite: 2].
2. **Automated Verification:** Wrote and integrated JUnit 5 test cases (`TestSolver`[cite: 7] and `MoreSudokuSolverTests`[cite: 1]) to verify edge cases, exception handling, and deep copy guarantees.
3. **UI Implementation:** Built `SudokuPanel` using Java Swing[cite: 5], keeping user interaction and validation completely separated from the underlying solver logic.
4. **Refactoring & Polish:** Integrated defensive copying to enforce encapsulation[cite: 2] and replaced console error outputs with user-friendly GUI dialogs[cite: 3, 5].

## Key Takeaways & What I Learned
* **Algorithm Optimization & State Pruning:** Gained hands-on experience structuring recursive backtracking algorithms and implementing early constraint checks to minimize unnecessary recursive search depth[cite: 2].
* **Software Architecture & Decoupling:** Mastered separation of concerns by isolating domain model logic (`MySudokuSolver`[cite: 2]) from presentation elements (`SudokuPanel`[cite: 5]) using interface abstractions[cite: 6].
* **Data Safety & Encapsulation:** Learned the importance of defensive copying using `System.arraycopy`[cite: 2] to prevent external callers from mutating private internal grid references.
* **Test-Driven Development (TDD):** Strengthened testing skills with JUnit 5, testing for edge cases like out-of-bounds indices, illegal digits, and unsolvable board states[cite: 1, 7].

## Tech Stack
* **Language:** Java
* **UI Framework:** Java Swing (`JFrame`, `JPanel`, `JTextField`)[cite: 4, 5]
* **Testing:** JUnit 5 (Jupiter API)[cite: 1]
* **Paradigm:** Object-Oriented Programming (OOP)
