package gui;

class SudokuCell extends SingleDigitTextField {
    SudokuCell(int containerSize, int containerGap, int i, int j, double n) {
        double size = (containerSize - (n + 1)*containerGap) / n;
        setPrefSize(size, size);

        applyRegionColors(i, j);
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

    void clearValue() {
        setText("");
    }

    int getValue() {
        String text = getText();

        boolean empty = text.length() == 0;

        return empty ? 0 : Integer.valueOf(text);
    }

    void setValue(int value) {
        setText(value == 0 ? "" : Integer.toString(value));
    }
}
