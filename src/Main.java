import userInterface.*;
import model.*;

/* Main Class takes in input line arguments, creates valid grid, and starts game */

public class Main {
    private static final int GRID_SIZE = 10;
    private static int numOpponents = 5;
    private static boolean cheating = false;

    public static void main(String[] args) {
        if (args.length > 0) {
            numOpponents = Integer.parseInt(args[0]);
        }
        if (args.length > 1 && args[1].equals("--cheat")) {
            cheating = true;
        }
        Grid gameGrid;
        while (true) {
            try {
                gameGrid = new Grid(numOpponents, GRID_SIZE);
            } catch (RuntimeException error) {
                if (numOpponents > GRID_SIZE) {
                    System.out.println("Not enough space to randomly place forts. Please Run Again!");
                    return;
                }
                continue;
            }
            break;
        }
        UserInterface gameInterface = new UserInterface(gameGrid, cheating);
        gameInterface.gamePlay();
    }
}