package solver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSudoku {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

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
                9,1,0, 0,0,0, 0,0,0,

                0,0,0, 0,0,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0,

                0,0,0, 0,0,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0,
                0,0,0, 0,0,0, 0,0,0
        );
        assertFalse(solver.boardIsValid());
    }

    @Test
    public final void solveEasy() {
        SudokuSolver solver = new SudokuSolver(
         5,4,8, 1,7,9, 3,6,2,
                3,7,6, 8,2,4, 9,1,5,
                1,9,2, 5,6,3, 8,7,4,

                7,8,4, 2,1,6, 5,9,3,
                2,5,9, 3,8,7, 6,4,1,
                6,3,1, 9,4,5, 7,2,8,

                4,1,5, 6,9,8, 2,3,7,
                8,6,7, 4,3,2, 1,5,9,
                9,2,3, 7,5,1, 4,8,6
        );
        assertTrue(solver.boardIsValid());
        solver.solve();
        solver.printCells();
    }
    /*
    @Test
    public final void solveEmpty() {
        SudokuSolver solver = new SudokuSolver(new int[9][9]);
        solver.solve();
        solver.printCells();
    }*/
}