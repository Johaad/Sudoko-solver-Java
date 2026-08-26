package solver;

public class MySudokuSolver implements SudokuSolver {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;

    private final int[][] grid;

    public MySudokuSolver() {
        this.grid = new int[SIZE][SIZE];
    }

    @Override
    public boolean solve() {
        if (!isAllValid()) {
            return false;
        }
        return solveFromCell(0, 0);
    }

    @Override
    public void set(int row, int col, int digit) {
        validatePosition(row, col);
        validateDigit(digit);
        grid[row][col] = digit;
    }

    @Override
    public int get(int row, int col) {
        validatePosition(row, col);
        return grid[row][col];
    }

    @Override
    public void clear(int row, int col) {
        validatePosition(row, col);
        grid[row][col] = EMPTY;
    }

    @Override
    public void clearAll() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = EMPTY;
            }
        }
    }

    @Override
    public boolean isValid(int row, int col) {
        validatePosition(row, col);

        int value = grid[row][col];
        if (value == EMPTY) {
            return true;
        }

        return isRowValid(row, col, value)
                && isColumnValid(row, col, value)
                && isBoxValid(row, col, value);
    }

    @Override
    public boolean isAllValid() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (!isValid(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void setGrid(int[][] matrix) {
        validateMatrix(matrix);

        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(matrix[row], 0, grid[row], 0, SIZE);
        }
    }

    @Override
    public int[][] getGrid() {
        int[][] copy = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(grid[row], 0, copy[row], 0, SIZE);
        }
        return copy;
    }

    private boolean solveFromCell(int row, int col) {
        if (row == SIZE) {
            return true;
        }

        int nextRow = col == SIZE - 1 ? row + 1 : row;
        int nextCol = col == SIZE - 1 ? 0 : col + 1;

        if (grid[row][col] != EMPTY) {
            return solveFromCell(nextRow, nextCol);
        }

        for (int digit = 1; digit <= SIZE; digit++) {
            grid[row][col] = digit;
            if (isValid(row, col) && solveFromCell(nextRow, nextCol)) {
                return true;
            }
        }

        grid[row][col] = EMPTY;
        return false;
    }

    private boolean isRowValid(int row, int currentCol, int value) {
        for (int col = 0; col < SIZE; col++) {
            if (col != currentCol && grid[row][col] == value) {
                return false;
            }
        }
        return true;
    }

    private boolean isColumnValid(int currentRow, int col, int value) {
        for (int row = 0; row < SIZE; row++) {
            if (row != currentRow && grid[row][col] == value) {
                return false;
            }
        }
        return true;
    }

    private boolean isBoxValid(int row, int col, int value) {
        int boxStartRow = (row / 3) * 3;
        int boxStartCol = (col / 3) * 3;

        for (int boxRow = boxStartRow; boxRow < boxStartRow + 3; boxRow++) {
            for (int boxCol = boxStartCol; boxCol < boxStartCol + 3; boxCol++) {
                if ((boxRow != row || boxCol != col) && grid[boxRow][boxCol] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    private void validatePosition(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IndexOutOfBoundsException("Row and column must be in interval [0..8].");
        }
    }

    private void validateDigit(int digit) {
        if (digit < 0 || digit > SIZE) {
            throw new IllegalArgumentException("Digit must be in interval [0..9].");
        }
    }

    private void validateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length != SIZE) {
            throw new IllegalArgumentException("Grid must be a 9x9 matrix.");
        }

        for (int row = 0; row < SIZE; row++) {
            if (matrix[row] == null || matrix[row].length != SIZE) {
                throw new IllegalArgumentException("Grid must be a 9x9 matrix.");
            }
            for (int col = 0; col < SIZE; col++) {
                validateDigit(matrix[row][col]);
            }
        }
    }
}
