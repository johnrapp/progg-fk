package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import solver.SudokuSolver;

import java.util.function.Consumer;

public class Controller {
    private static int SIZE = 600;
    private static int GRID_GAP = 3;

    @FXML
    private GridPane grid;

    @FXML
    private Button solveButton;

    @FXML
    private Button clearButton;

    private SudokuCell[][] cells;
    private int n = 9;

    @FXML
    public void initialize() {
        solveButton.setOnAction((event) -> onSolve());
        clearButton.setOnAction((event) -> applyOnCells(SudokuCell::clearValue));

        initGrid();
    }

    private void onSolve() {
        SudokuSolver solver = new SudokuSolver(getCellValues());
        solver.solve();
        setCellValues(solver.getCells());
    }

    private void applyOnCells(Consumer<SudokuCell> apply) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                apply.accept(cells[i][j]);
            }
        }
    }

    private int[][] getCellValues() {
        int[][] values = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                values[i][j] = cells[i][j].getValue();
            }
        }
        return values;
    }

    private void setCellValues(int[][] values) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j].setValue(values[i][j]);
            }
        }
    }

    private void initGrid() {
        grid.setPrefWidth(SIZE);
        grid.setPrefHeight(SIZE);
        grid.setVgap(GRID_GAP);
        grid.setHgap(GRID_GAP);

        cells = new SudokuCell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                SudokuCell cell = new SudokuCell(SIZE, GRID_GAP, i, j, n);
                cells[i][j] = cell;
                grid.add(cell, i, j);
            }
        }
    }

}
