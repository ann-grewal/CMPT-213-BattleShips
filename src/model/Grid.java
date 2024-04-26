package model;

import java.util.*;

/* Grid Class handles and updates the current state of the grid game.
 *   Holds List of all Units to Hit for User Turn.
 *   Holds List of all Forts to collect Points for Opponent Turn.
 *   Checks if anyone has won yet, but User Interface start and end game. */

public class Grid {
    private final int GRID_SIZE;
    private final List<Unit> gridUnits = new ArrayList<>();
    private final List<Fort> gridForts = new ArrayList<>();
    private final int winnerPoints = 2500;
    private int totalPoints = 0;

    public Grid(int numOpponents, int gridSize) throws RuntimeException {
        this.GRID_SIZE = gridSize;
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            gridUnits.add(new Unit(i));
        }
        for (int i = 0; i < numOpponents; i++) {
            gridForts.add(new Fort(this, i));
        }
    }

    // Getters.
    public Unit getGridUnit(int index) {
        return gridUnits.get(index);
    }

    public int getGridSize() {
        return GRID_SIZE;
    }

    public int getNumUnits() {
        return GRID_SIZE * GRID_SIZE;
    }

    public int getNumForts() {
        return gridForts.size();
    }

    public int getTotalPoint() {
        return totalPoints;
    }

    public int getFortPoints(int index) {
        int fortPoints = gridForts.get(index).getPoints();
        totalPoints += fortPoints;
        return fortPoints;
    }

    // Check Win.
    public boolean checkOpponentWin() {
        return totalPoints >= winnerPoints;
    }

    public boolean checkUserWin() {
        int totalPoints = 0;
        for (Fort forts : gridForts) {
            totalPoints = forts.getPoints();
        }
        return ((totalPoints == 0));
    }

    // Update Unit Hit By User.
    public boolean hitUnit(int index) {
        Unit hitUnit = getGridUnit(index);
        hitUnit.unitHit();
        return hitUnit.isInFort();
    }
}
