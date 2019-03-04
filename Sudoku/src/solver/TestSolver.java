package solver;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestSolver {
    private int n = 9;

    @Test
    public final void correctRegionIndex() {
        SudokuSolver solver = new SudokuSolver(new int[n][n]);

        assertEquals(solver.regionIndex(0, 0), 0);
        assertEquals(solver.regionIndex(3, 1), 3);
        assertEquals(solver.regionIndex(3, 4), 4);
        assertEquals(solver.regionIndex(8, 5), 7);
    }

    @Test
    public final void solveEmpty() {
        SudokuSolver solver = new SudokuSolver(new int[n][n]);
        assertTrue(solver.solve());
        assertTrue(solver.boardIsValid());
    }

    @Test
    public final void solveEasy() {
        SudokuSolver solver = new SudokuSolver(
     5,4,8, 1,7,9, 3,6,2,
            3,7,6, 8,2,4, 9,0,5,
            1,9,2, 5,6,3, 8,7,4,

            7,8,4, 2,1,6, 5,9,3,
            2,0,9, 3,0,7, 6,4,1,
            6,3,1, 9,4,5, 7,2,8,

            4,1,0, 6,9,8, 2,3,7,
            8,6,7, 4,3,2, 1,5,9,
            0,2,3, 7,5,1, 4,0,0 //<-- Fill in
        );
        assertTrue(solver.solve());
        assertTrue(solver.boardIsValid());
        solver.printBoard();
    }
    @Test
    public final void solveTestCase() {
        // Given in the assignment
        SudokuSolver solver = new SudokuSolver(
     0,0,8, 0,0,9, 0,6,2,
            0,0,0, 0,0,0, 0,0,5,
            1,0,2, 5,0,0, 0,0,0,

            0,0,0, 2,1,0, 0,9,0,
            0,5,0, 0,0,0, 6,0,0,
            6,0,0, 0,0,0, 0,2,8,

            4,1,0, 6,0,8, 0,0,0,
            8,6,0, 0,3,0, 1,0,0,
            0,0,0, 0,0,0, 4,0,0
        );
        assertTrue(solver.solve());
        assertTrue(solver.boardIsValid());
        solver.printBoard();
    }

    @Test
    public final void solveNoSolutionEasy() {
        SudokuSolver solver = new SudokuSolver(
     0,0,8, 0,0,9, 0,6,2,
            0,0,0, 0,0,0, 0,0,5,
            1,0,2, 5,0,0, 0,0,0,

            0,0,0, 2,1,0, 0,9,0,
            0,5,0, 0,0,0, 6,0,0,
            6,0,0, 0,0,0, 0,2,8,

            4,1,0, 6,0,8, 0,0,0,
            8,6,0, 0,3,0, 1,1,0, //<--- Duplicate 1 on this row
            0,0,0, 0,0,0, 4,0,0
        );
        assertFalse(solver.solve());
        assertFalse(solver.boardIsValid());
        solver.printBoard();
    }

    @Test
    public final void solveNoSolutionHard() {
        SudokuSolver solver = new SudokuSolver(
     5,5,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,

            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,

            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0
        );
        assertFalse(solver.solve());
        assertFalse(solver.boardIsValid());
        solver.printBoard();
    }

    @Test
    public final void solveNoSolutionHarder() {
        SudokuSolver solver = new SudokuSolver(
     1,2,3, 0,0,0, 0,0,0,
            4,5,6, 0,0,0, 0,0,0,
            0,0,0, 7,0,0, 0,0,0,

            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,

            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0
        );
        assertFalse(solver.solve());
        assertTrue(solver.boardIsValid());
        solver.printBoard();
    }
}