package userInterface;

import model.*;

import java.util.Scanner;

/* User Interface starts and ends the Grid Game till a winner is found given a valid Grid.
    Uses the AsciiConverter Class for common ASCII Conversions.
    Uses theScanner Class to get User Input for User Turn.
    Uses Grid Printer to print formatted grid to screen. */

public class UserInterface {
    private final Grid gameGrid;
    private final int GRID_SIZE;
    private final GridPrinter gameGridPrinter;
    private final AsciiConverter converter = new AsciiConverter();
    private final Scanner scanner = new Scanner(System.in);

    public UserInterface(Grid gameGrid, boolean cheating) {
        this.gameGrid = gameGrid;
        this.gameGridPrinter = new GridPrinter(gameGrid);
        this.GRID_SIZE = gameGrid.getGridSize();
        if (cheating) {
            gameGridPrinter.printGrid(cheating);
        }
    }

    // Runs Game until Winner found.
    public void gamePlay() {
        System.out.println();
        System.out.print("""
                ------------------------
                Welcome to Fort Defense!
                   By Anureet Grewal
                ------------------------
                """);
        String winner;
        while (true) {
            gameGridPrinter.printGrid(false);
            boolean userWon = userTurn();
            if (userWon) {
                winner = "User";
                break;
            }
            boolean opponentWon = opponentTurn();
            if (opponentWon) {
                winner = "Opponent";
                break;
            }
        }
        System.out.println("The Opponent had " + gameGrid.getTotalPoint() + " points.");
        System.out.println("Game Over! " + winner + " Won!");
        gameGridPrinter.printGrid(true);
    }

    private boolean opponentTurn() {
        System.out.println();
        for (int i = 0; i < gameGrid.getNumForts(); i++) {
            int points = gameGrid.getFortPoints(i);
            System.out.println("Fort " + (i + 1) + " scored " + points + " points this round!");
        }
        System.out.println("Opponents have scored " + gameGrid.getTotalPoint() + " points in total!");
        System.out.println();
        return gameGrid.checkOpponentWin();
    }

    private boolean userTurn() {
        boolean hitFort = gameGrid.hitUnit(unitInput());
        if (hitFort) {
            System.out.println("HIT!");
        } else {
            System.out.println("MISSED");
        }
        return gameGrid.checkUserWin();
    }

    // Collects input for User Turn.
    @SuppressWarnings("UnusedAssignment")
    private int unitInput() {
        final int INVALID = -1;
        String userInput;
        int row = INVALID;
        int column = INVALID;

        while (true) {
            System.out.print("Please enter a valid Unit: ");
            userInput = scanner.nextLine();
            userInput = userInput.replaceAll("\\s", "");
            // Get Row By Alphabet.
            if (userInput.isEmpty() || userInput.isBlank()) {
                continue;
            }
            row = converter.charToInt(userInput.charAt(0));
            if (row < 0 || row > GRID_SIZE - 1) {
                row = INVALID;
            }
            // Get Column by Number.
            if (userInput.length() == 2) {
                column = converter.charIntToInt(userInput.charAt(1));
            }
            if (userInput.substring(1).equals("10")) {
                column = GRID_SIZE - 1;
            }
            // Check Valid.
            if (column != INVALID && row != INVALID) {
                break;
            }
        }
        return (row * GRID_SIZE + column);
    }
}
