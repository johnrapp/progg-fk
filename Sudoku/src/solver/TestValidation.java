package solver;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestValidation {

    @Test
    public final void validateEmpty() {
        SudokuSolver solver = new SudokuSolver(new int[9][9]);
        assertTrue(solver.boardIsValid());
    }

    @Test
    public final void onePerRow() {
        SudokuSolver solver = new SudokuSolver(
     0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 1,0,0, 0,0,1,

            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,

            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0
        );
        assertFalse(solver.boardIsValid());

        solver = new SudokuSolver(
     0,0,0, 0,5,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,3,

            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,2,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,

            1,0,0, 0,0,0, 0,0,0,
            9,0,0, 0,0,0, 0,9,0,
            0,0,0, 0,0,0, 0,0,0
        );
        assertFalse(solver.boardIsValid());
    }
    @Test
    public final void onePerCol() {
        SudokuSolver solver = new SudokuSolver(
     0,1,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,

            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,

            0,0,0, 0,0,0, 0,0,0,
            0,1,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0
        );
        assertFalse(solver.boardIsValid());

        solver = new SudokuSolver(
     0,0,0, 0,5,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,3,

            0,0,0, 0,0,0, 0,9,0,
            0,0,0, 0,2,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,

            1,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,9,0,
            0,0,0, 0,0,0, 0,0,0
        );
        assertFalse(solver.boardIsValid());
    }
    @Test
    public final void onePerRegion() {
        SudokuSolver solver = new SudokuSolver(
     0,1,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,1, 0,0,0, 0,0,0,

            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,

            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0
        );
        assertFalse(solver.boardIsValid());

        solver = new SudokuSolver(
     0,0,0, 0,5,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,3,

            0,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,2,0, 0,0,0,
            0,0,0, 0,0,0, 0,0,0,

            1,0,0, 0,0,0, 0,0,0,
            0,0,0, 0,0,0, 0,9,0,
            0,0,0, 0,0,0, 9,0,0
        );
        assertFalse(solver.boardIsValid());
    }

    @Test
    public final void validBoard() {
        SudokuSolver solver = new SudokuSolver(
     5,4,8, 1,7,9, 3,6,2,
            3,7,6, 8,2,4, 9,0,5,
            1,9,2, 5,6,3, 8,7,4,

            7,8,4, 2,1,6, 5,9,3,
            2,0,9, 3,0,7, 6,4,1,
            6,3,1, 9,4,5, 7,2,8,

            4,1,0, 6,9,8, 2,3,7,
            8,6,7, 4,3,2, 1,5,9,
            0,2,3, 7,5,1, 4,0,0
        );
        assertTrue(solver.boardIsValid());
    }

    @Test
    public final void nonValidBoard() {
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
        assertFalse(solver.boardIsValid());
    }
}
