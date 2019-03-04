package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import solver.SudokuSolver;

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
        clearButton.setOnAction((event) -> onClear());

        initGrid();
    }

    private void onSolve() {
        SudokuSolver solver = new SudokuSolver(getCellValues());
        boolean existsSolution = solver.solve();
        if (existsSolution) {
            setCellValues(solver.getCellValues());
        } else {
            showNoSolutionError();
        }
    }

    private void onClear() {
        applyOnCells((cell, i, j) -> cell.clearValue());
    }

    private int[][] getCellValues() {
        int[][] values = new int[n][n];

        applyOnCells((cell, i, j) -> values[i][j] = cell.getValue());

        return values;
    }

    private void setCellValues(int[][] values) {
        applyOnCells((cell, i, j) -> cell.setValue(values[i][j]));
    }

    private void applyOnCells(CellApplicator applicator) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                applicator.apply(cells[i][j], i, j);
            }
        }
    }

    private void showNoSolutionError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Solve error");
        alert.setHeaderText(null);
        alert.setContentText("There is no solution to the entered Sudoku! Please enter in another one :)");

        alert.showAndWait();
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
                grid.add(cell, j, i);
            }
        }
    }

    private interface CellApplicator {
        void apply(SudokuCell cell, int i, int j);
    }

}
