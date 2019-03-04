package solver;

public class SudokuSolver {
    private int[][] cells;
    private int n = 9;

    public SudokuSolver(int[][] cells) {
        this.cells = cells;
    }

    public SudokuSolver(int... cells) {
        this.cells = new int[n][n];
        for(int i = 0; i < n*n; i++) {
            this.cells[i % n][i / n] = cells[i];
        }
    }

    public boolean boardIsValid() {
        int[] rowChecksums = new int[n];
        int[] colChecksums = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 1 << n = 2^n
                int val = cells[i][j] == 0 ? 0 : (int) Math.pow(10, cells[i][j] - 1);
                rowChecksums[j] += val;
                colChecksums[i] += val;
            }
        }

        return checksumsAreValid(rowChecksums) && checksumsAreValid(colChecksums);
    }

    private boolean checksumsAreValid(int[] checksums) {
        for (int i = 0; i < n; i++) {
            if (!checksumIsValid(checksums[i])) {
                return false;
            }
        }
        return true;
    }
    private boolean checksumIsValid(int checksum) {
        if (checksum == 0) {
            return true;
        }

        for (int i = 0; i < n; i++) {
            if (checksum % 10 > 1) {
                return false;
            }
            checksum /= 10;
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
            cells[i][j] = attempt;
            if (validateMove(i, j, attempt) && solveNext(i, j)) {
                return true;
            }
        }

        return false;
    }
    private boolean solveNext(int i, int j) {
        boolean lastRow = i == n - 1;
        boolean lastCell = lastRow && j == n - 1;
        return lastCell || solve((i + 1) % n, lastRow ? j + 1 : j);
    }

    private boolean validateMove(int i, int j, int attempt) {
        return true;
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
                System.out.print(cells[j][i]);
                if (j > 0 && (j + 1) % 3 == 0) {
                    System.out.print(" ");
                }
            }
        }
    }
}
