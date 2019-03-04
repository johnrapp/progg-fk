package solver;

public class SudokuSolver {
    private int[][] cells;
    private int n = 9;

    private int[][] rowValueCounts = new int[n][n + 1];
    private int[][] colValueCounts = new int[n][n + 1];
    private int[][] regionValueCounts = new int[n][n + 1];

    public SudokuSolver(int[][] cells) {
        this.cells = cells;
        initCounts();
    }

    SudokuSolver(int... cells) {
        this.cells = new int[n][n];
        for(int i = 0; i < n*n; i++) {
            this.cells[i / n][i % n] = cells[i];
        }

        initCounts();
    }

    private void initCounts() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowValueCounts[i][cells[i][j]]++;
                colValueCounts[j][cells[i][j]]++;
                regionValueCounts[regionIndex(i, j)][cells[i][j]]++;
            }
        }
    }

    public int regionIndex(int i, int j) {
        return ((i / 3) % 3) * 3 + (j / 3) % 3;
    }

    public boolean boardIsValid() {
        return (
            countsIsValid(rowValueCounts) &&
            countsIsValid(colValueCounts) &&
            countsIsValid(regionValueCounts)
        );
    }

    private boolean countsIsValid(int[][] counts) {
        for (int i = 0; i < n ; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (counts[i][j] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public void solve() {
        boolean couldSolve = solve(0, 0);
        printCells();
    }

    private boolean solve(int i, int j) {
        boolean alreadyContainsValue = cells[i][j] != 0;
        if (alreadyContainsValue) {
            return solveNext(i, j);
        }

        for (int attempt = 1; attempt <= n; attempt++) {
            if (validateMove(i, j, attempt)) {
                makeMove(i, j, attempt);
                printCells();
                if (solveNext(i, j)) {
                    return true;
                }
                undoMove(i, j, attempt);
            }
        }

        return false;
    }
    private boolean solveNext(int i, int j) {
        boolean lastCol = j == n - 1;
        boolean lastCell = lastCol && i == n - 1;

        return lastCell || solve(lastCol ? i + 1 : i, (j + 1) % n);
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
        colValueCounts[i][attempt]++;
        regionValueCounts[i][attempt]++;
    }

    private void undoMove(int i, int j, int attempt) {
        rowValueCounts[i][attempt]--;
        colValueCounts[i][attempt]--;
        regionValueCounts[i][attempt]--;
    }

    public int[][] getCells() {
        return cells;
    }

    public void printCells() {
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
    }
}
