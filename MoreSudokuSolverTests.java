package solver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import solver.MySudokuSolver;
import solver.SudokuSolver;

public class MoreSudokuSolverTests {
    private SudokuSolver solver;

    @BeforeEach
    void setUp() {
        solver = new MySudokuSolver();
    }

    @Test
    void testSetAndGetSingleCell() {
        solver.set(2, 3, 7);
        assertEquals(7, solver.get(2, 3));
    }

    @Test
    void testClearCell() {
        solver.set(1, 1, 5);
        solver.clear(1, 1);
        assertEquals(0, solver.get(1, 1));
    }

    @Test
    void testClearAll() {
        solver.set(0, 0, 9);
        solver.set(8, 8, 1);
        solver.clearAll();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                assertEquals(0, solver.get(row, col));
            }
        }
    }

    @Test
    void testIndexOutOfBoundsInSetGetAndClear() {
        assertThrows(IndexOutOfBoundsException.class, () -> solver.set(-1, 0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> solver.get(0, 9));
        assertThrows(IndexOutOfBoundsException.class, () -> solver.clear(9, 0));
    }

    @Test
    void testIllegalDigitInSet() {
        assertThrows(IllegalArgumentException.class, () -> solver.set(0, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> solver.set(0, 0, 10));
    }

    @Test
    void testIsValidForRowConflict() {
        solver.set(0, 0, 5);
        solver.set(0, 4, 5);
        assertFalse(solver.isValid(0, 0));
        assertFalse(solver.isValid(0, 4));
    }

    @Test
    void testIsValidForColumnConflict() {
        solver.set(0, 0, 7);
        solver.set(6, 0, 7);
        assertFalse(solver.isValid(0, 0));
        assertFalse(solver.isValid(6, 0));
    }

    @Test
    void testIsValidForBoxConflict() {
        solver.set(0, 0, 3);
        solver.set(1, 1, 3);
        assertFalse(solver.isValid(0, 0));
        assertFalse(solver.isValid(1, 1));
    }

    @Test
    void testSolveEmptySudoku() {
        assertTrue(solver.solve());
        assertTrue(solver.isAllValid());
    }

    @Test
    void testUnsolvableSudoku() {
        solver.set(0, 0, 5);
        solver.set(0, 1, 5);
        assertFalse(solver.solve());
    }

    @Test
    void testSolvableSudoku() {
        int[][] board = {
                {0, 0, 8, 0, 0, 9, 0, 6, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 5},
                {1, 0, 2, 5, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 9, 0},
                {0, 5, 0, 0, 0, 0, 6, 0, 0},
                {6, 0, 0, 0, 0, 0, 0, 2, 8},
                {4, 1, 0, 6, 0, 8, 0, 0, 0},
                {8, 6, 0, 0, 3, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0}
        };

        solver.setGrid(board);
        assertTrue(solver.solve());
        assertTrue(solver.isAllValid());
    }
}
