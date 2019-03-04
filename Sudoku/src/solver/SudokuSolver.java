package solver;

public class SudokuSolver {
    private int[][] cells;
    private int n = 9;

    // Matrices that for each row/col/region keeps track if how many of each
    // value is in that particular row/col/region
    // Indexed as such: counts[row/col/region][value]
    private int[][] rowValueCounts = new int[n][n + 1];
    private int[][] colValueCounts = new int[n][n + 1];
    private int[][] regionValueCounts = new int[n][n + 1];

    /**
     * Create instance with given matrix of cell values
     */
    public SudokuSolver(int[][] cellValues) {
        this.cells = cellValues;
        initCounts();
    }

    // Package-private for tests
    SudokuSolver(int... cellValues) {
        this.cells = new int[n][n];
        for(int i = 0; i < n*n; i++) {
            this.cells[i / n][i % n] = cellValues[i];
        }
        initCounts();
    }

    private void initCounts() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = cells[i][j];
                rowValueCounts[i][value]++;
                colValueCounts[j][value]++;
                regionValueCounts[regionIndex(i, j)][value]++;
            }
        }
    }

    // Converts row and column to a region index, where 0 is the top-left region and 8 is the bottom-right region
    // Package-private for tests
    int regionIndex(int i, int j) {
        return ((i / 3) % 3) * 3 + (j / 3) % 3;
    }

    // Package-private for tests
    boolean boardIsValid() {
        return (
            noDuplicateValues(rowValueCounts) &&
            noDuplicateValues(colValueCounts) &&
            noDuplicateValues(regionValueCounts)
        );
    }

    // If there are more than one of any value > 0, the counts are not valid
    private boolean noDuplicateValues(int[][] counts) {
        for (int i = 0; i < n ; i++) {
            // From 1 through 9
            for (int j = 1; j <= n ; j++) {
                if (counts[i][j] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Attempts to solve the given Sudoku.<br>
     * If the Sudoku is solvable, the cells will be mutated and the solution may be read using {@link #getCellValues() getCellValues()}.
     * @return true if the Sudoku is solvable, otherwise false
     */
    public boolean solve() {
        return boardIsValid() && solve(0, 0);
    }

    private boolean solve(int i, int j) {
        boolean cellAlreadyContainsValue = cells[i][j] != 0;
        if (cellAlreadyContainsValue) {
            return solveNext(i, j);
        }

        for (int attempt = 1; attempt <= n; attempt++) {
            if (validateMove(i, j, attempt)) {
                makeMove(i, j, attempt);
                //System.out.println("");
                //printBoard();

                if (solveNext(i, j)) {
                    return true;
                } else {
                    undoMove(i, j, attempt);
                }
            }
        }

        return false;
    }

    private boolean solveNext(int i, int j) {
        boolean lastCol = j == n - 1;
        boolean lastRow = i == n - 1;
        boolean lastCell = lastCol && lastRow;

        int nextRow = lastCol ? i + 1 : i;
        int nextCol = (j + 1) % n;

        return lastCell || solve(nextRow, nextCol);
    }

    private boolean validateMove(int i, int j, int attempt) {
        boolean validRow = rowValueCounts[i][attempt] < 1;
        boolean validCol = colValueCounts[j][attempt] < 1;
        boolean validRegion = regionValueCounts[regionIndex(i, j)][attempt] < 1;

        return validRow && validCol && validRegion;
    }

    private void makeMove(int i, int j, int attempt) {
        cells[i][j] = attempt;
        rowValueCounts[i][attempt]++;
        colValueCounts[j][attempt]++;
        regionValueCounts[regionIndex(i, j)][attempt]++;
    }

    private void undoMove(int i, int j, int attempt) {
        cells[i][j] = 0;
        rowValueCounts[i][attempt]--;
        colValueCounts[j][attempt]--;
        regionValueCounts[regionIndex(i, j)][attempt]--;
    }

    /**
     * Gets a matrix of cell values.<br>
     * @return The cell values given in the constructor if {@link #solve() solve()} has not been called.
     * <br> If {@link #solve() solve()} has been called and there exists a solution, returns the solution.
     */
    public int[][] getCellValues() {
        return cells;
    }

    // Package-private for tests
    void printBoard() {
        for (int i = 0; i < n + 2; i++) {
            System.out.print("-");
        }

        for (int i = 0; i < n; i++) {
            System.out.print("\n");
            if (i > 0 && i % 3 == 0) {
                System.out.print("\n");
            }
            for (int j = 0; j < n; j++) {
                System.out.print(cells[i][j]);
                if (j > 0 && (j + 1) % 3 == 0) {
                    System.out.print(" ");
                }
            }
        }
        System.out.print("\n");
        for (int i = 0; i < n + 2; i++) {
            System.out.print("-");
        }
    }
}
