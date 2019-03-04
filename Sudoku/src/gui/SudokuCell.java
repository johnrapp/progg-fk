package gui;

class SudokuCell extends SingleDigitTextField {

    /**
     * @param containerSize The size of the Sudoku container
     * @param containerGap  The gap between the Sudoku cells
     * @param row           The row of this Sudoku cell
     * @param col           The column of this Sudoku cell
     * @param n             The amount of rows/columns of cells
     */
    SudokuCell(int containerSize, int containerGap, int row, int col, double n) {
        double size = (containerSize - (n + 1)*containerGap) / n;
        setPrefSize(size, size);

        applyRegionColors(row, col);
    }

    private void applyRegionColors(int i, int j) {
        boolean oddHorizontalRegion = (i / 3) % 2 == 0;
        boolean oddVerticalRegion = (j / 3) % 2 == 0;

        boolean oddRegion = oddHorizontalRegion && oddVerticalRegion;
        boolean centerRegion = !(oddHorizontalRegion || oddVerticalRegion);

        if (oddRegion || centerRegion) {
            getStyleClass().add("alternative");
        }
    }

    /**
     * Clears value displayed in the cell
     */
    void clearValue() {
        setText("");
    }

    /**
     * Gets the value of the cell
     * @return The value displayed in the cell
     */
    int getValue() {
        String text = getText();

        boolean empty = text.length() == 0;

        return empty ? 0 : Integer.valueOf(text);
    }

    /**
     * Sets the value of the cell
     * @param value The value to be displayed in the cell
     */
    void setValue(int value) {
        setText(value == 0 ? "" : Integer.toString(value));
    }
}
