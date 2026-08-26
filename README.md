
# Java 17 Sudoku Solver & GUI

An object-oriented Java application featuring a 9x9 Sudoku solver using recursive backtracking, a Java Swing interface, and automated unit tests.

## Key Highlights
* **Algorithm:** Recursive backtracking with early-state pruning.
* **Architecture:** Swing GUI decoupled from core domain logic via interface abstraction.
* **Data Safety:** Defensive array copying (`setGrid`, `getGrid`) preventing external state mutations.
* **Testing:** Comprehensive JUnit test suite covering boundary limits, unsolvable boards, and state safety.

## Tech Stack
* **Language:** Java 17
* **Testing:** JUnit 5
* **GUI:** Java Swing
