package solver;

public interface SudokuSolver {
    boolean solve();
    void set(int row, int col, int digit);
    int get(int row, int col);
    void clear(int row, int col);
    void clearAll();
    boolean isValid(int row, int col);
    boolean isAllValid();
    void setGrid(int[][] m);
    int[][] getGrid();
}
