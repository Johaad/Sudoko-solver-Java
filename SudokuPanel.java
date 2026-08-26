package solver;

import solver.SudokuSolver;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class SudokuPanel extends JPanel {
    private static final int SIZE = 9;
    private static final Font CELL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 22);
    private static final Color LIGHT_BOX = new Color(245, 245, 245);
    private static final Color DARK_BOX = new Color(225, 225, 225);

    private final SudokuSolver solver;
    private final JTextField[][] cells;
    private final JLabel statusLabel;

    public SudokuPanel(SudokuSolver solver) {
        this.solver = solver;
        this.cells = new JTextField[SIZE][SIZE];
        this.statusLabel = new JLabel("Fyll i sudoku och klicka på Solve.", SwingConstants.CENTER);

        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        add(createBoardPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel createBoardPanel() {
        JPanel boardPanel = new JPanel(new GridLayout(SIZE, SIZE));
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField field = new JTextField();
                field.setHorizontalAlignment(SwingConstants.CENTER);
                field.setFont(CELL_FONT);
                field.setBorder(BorderFactory.createMatteBorder(
                        row % 3 == 0 ? 2 : 1,
                        col % 3 == 0 ? 2 : 1,
                        row == SIZE - 1 ? 2 : (row % 3 == 2 ? 2 : 1),
                        col == SIZE - 1 ? 2 : (col % 3 == 2 ? 2 : 1),
                        Color.GRAY));
                field.setBackground(((row / 3) + (col / 3)) % 2 == 0 ? LIGHT_BOX : DARK_BOX);
                cells[row][col] = field;
                boardPanel.add(field);
            }
        }

        return boardPanel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        JPanel buttonPanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        JButton clearButton = new JButton("Clear");

        solveButton.addActionListener(e -> solveSudoku());
        clearButton.addActionListener(e -> clearSudoku());

        buttonPanel.add(clearButton);
        buttonPanel.add(solveButton);

        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));

        panel.add(statusLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void solveSudoku() {
        try {
            readBoardFromView();

            if (!solver.isAllValid()) {
                showMessage("Sudokut bryter mot reglerna och går inte att lösa.");
                statusLabel.setText("Ogiltigt sudoku.");
                return;
            }

            if (solver.solve()) {
                writeBoardToView();
                statusLabel.setText("Sudokut löstes.");
                showMessage("Sudokut löstes.");
            } else {
                statusLabel.setText("Sudokut går inte att lösa.");
                showMessage("Sudokut går inte att lösa.");
            }
        } catch (IllegalArgumentException ex) {
            statusLabel.setText("Felaktig inmatning.");
            showMessage(ex.getMessage());
        }
    }

    private void clearSudoku() {
        solver.clearAll();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col].setText("");
            }
        }
        statusLabel.setText("Sudokut tömdes.");
    }

    private void readBoardFromView() {
        solver.clearAll();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                String text = cells[row][col].getText().trim();
                if (text.isEmpty()) {
                    solver.set(row, col, 0);
                } else {
                    int value;
                    try {
                        value = Integer.parseInt(text);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException(
                                "Felaktig inmatning i ruta (" + (row + 1) + "," + (col + 1) + "). Ange en siffra 1-9 eller lämna rutan tom.");
                    }

                    if (value < 1 || value > 9) {
                        throw new IllegalArgumentException(
                                "Felaktig inmatning i ruta (" + (row + 1) + "," + (col + 1) + "). Ange en siffra 1-9 eller lämna rutan tom.");
                    }
                    solver.set(row, col, value);
                }
            }
        }
    }

    private void writeBoardToView() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = solver.get(row, col);
                cells[row][col].setText(value == 0 ? "" : Integer.toString(value));
            }
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Sudoku", JOptionPane.INFORMATION_MESSAGE);
    }
}
