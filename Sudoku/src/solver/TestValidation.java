package solver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestValidation {

    @Test
    public final void validateEmpty() {
        SudokuSolver solver = new SudokuSolver(new int[9][9]);
        assertTrue(solver.boardIsValid());
    }
    @Test
    public final void validateNonValidBoard() {
        SudokuSolver solver = new SudokuSolver(
                9,0,0, 0,1,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0,
                2,1,0, 0,0,0, 0,0,0,

                0,0,0, 0,0,0, 0,0,0,
                0,0,0, 0,2,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0,

                0,0,0, 0,0,0, 0,0,0,
                0,1,0, 0,0,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0
        );
        assertFalse(solver.boardIsValid());

        solver = new SudokuSolver(
                9,0,0, 0,1,0, 0,0,0,
                0,0,2, 0,0,0, 0,0,0,
                2,1,0, 0,0,0, 0,0,0,

                0,0,0, 0,0,0, 0,0,0,
                0,0,0, 0,2,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0,

                0,0,0, 0,0,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0
        );
        assertFalse(solver.boardIsValid());

        solver = new SudokuSolver(
                9,0,0, 0,1,0, 0,0,9,
                0,0,3, 0,0,0, 0,0,0,
                2,1,0, 0,0,0, 0,0,0,

                0,0,0, 0,0,0, 0,0,0,
                0,0,0, 0,2,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0,

                0,0,0, 0,0,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0
        );
        assertFalse(solver.boardIsValid());
    }
}
