package map;

/**
 * This class is used as a unit cell in the game map
 * and stores the type of that cell.
 */
public final class Cell {
    private char type;

    public Cell() {
        this.type = ' ';
    }

    public char getType() {
        return type;
    }

    public void setType(final char type) {
        this.type = type;
    }
}

