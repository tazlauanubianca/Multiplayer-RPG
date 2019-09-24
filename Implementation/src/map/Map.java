package map;

/**
 * This class is used to store the game map and the types of each cell.
 * It will be used to determine if two characters are on the same cell and
 * if one of the characters has terrain enhanced abilities.
 */
public final class Map {
    private Cell[][] map;
    private int rows, columns;

    public int getColumns() {
        return this.columns;
    }

    public int getRows() {
        return this.rows;
    }

    public Map(final int rows, final int columns) {
        int i, j;
        map = new Cell[rows][columns];

        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                map[i][j] = new Cell();
            }
        }

        this.rows = rows;
        this.columns = columns;
    }

    public void setLine(final int line, final String terrainTypes) {
        int i;
        char[] terrainTypesArray = terrainTypes.toCharArray();

        for (i = 0; i < this.columns; i++) {
            map[line][i].setType(terrainTypesArray[i]);
        }
    }

    public char getCellType(final int posX, final int posY) {
        return map[posX][posY].getType();
    }
}

