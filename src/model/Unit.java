package model;

/* Unit Class holds all information about a specific grid including
    where it is in the grid, what fort it is a part of, and whether it is hit or not. */

public class Unit {
    private final int index;
    private Fort fort = null;
    private boolean isHit = false;

    // Update Properties
    public Unit(int index) {
        this.index = index;
    }

    public void unitHit() {
        isHit = true;
    }

    // Get Properties.
    public boolean isHit() {
        return isHit;
    }

    public boolean isInFort() {
        return fort != null;
    }

    public int getIndex() {
        return index;
    }

    public Fort getFort() {
        return fort;
    }

    public void setFort(Fort fort) {
        this.fort = fort;
    }

}
