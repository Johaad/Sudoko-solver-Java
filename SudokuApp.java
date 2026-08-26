package solver;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SudokuApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sudoku Solver");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new SudokuPanel(new MySudokuSolver()));
            frame.pack();
            frame.setSize(700, 760);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
