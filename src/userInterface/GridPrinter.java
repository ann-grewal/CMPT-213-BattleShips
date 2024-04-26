package userInterface;

import model.*;

/* Grid Printer Class prints formatted grid to console of Units depending on hit/not hit and fort/not fort.
    If asked, prints the cheating grid with all information revealed.
    Uses AsciiConverter Class for common ASCII Conversions. */

public class GridPrinter {
    private final Grid gameGrid;
    private final int GRID_SIZE;
    private final String spacing = "  ";
    private final AsciiConverter converter = new AsciiConverter();

    public GridPrinter(Grid gameGrid) {
        this.gameGrid = gameGrid;
        this.GRID_SIZE = gameGrid.getGridSize();
    }

    // Format Grid.
    public void printGrid(boolean cheating) {
        System.out.println();
        // Columns.
        System.out.print(" ");
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.print(spacing + (i + 1));
        }
        // Rows.
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.println();
            System.out.print(converter.intToUppercase(i) + spacing);
            for (int j = 0; j < GRID_SIZE; j++) {
                if (cheating) {
                    printCheatingUnit(i * GRID_SIZE + j);
                } else {
                    printUnit(i * GRID_SIZE + j);
                }
            }
        }
        System.out.println();
    }

    // Format Unit.
    private void printCheatingUnit(int index) {
        Unit currentUnit = gameGrid.getGridUnit(index);
        if (currentUnit.isInFort()) {
            int fortIndex = currentUnit.getFort().getIndex();
            if (currentUnit.isHit()) {
                System.out.print(converter.intToLowercase(fortIndex) + spacing);
            } else {
                System.out.print(converter.intToUppercase(fortIndex) + spacing);
            }
        } else {
            if (currentUnit.isHit()) {
                System.out.print(" " + spacing);
            } else {
                System.out.print("." + spacing);
            }
        }
    }

    private void printUnit(int index) {
        Unit currentUnit = gameGrid.getGridUnit(index);
        if (currentUnit.isHit()) {
            if (currentUnit.isInFort()) {
                System.out.print("X" + spacing);
            } else {
                System.out.print(" " + spacing);
            }
        } else {
            System.out.print("~" + spacing);
        }
    }
}