package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import map.GameState;
import warriors.Warrior;
import fileio.implementations.FileWriter;

/**
 * This class is used to write the final data in the
 * specified file. The information will be displayed
 * about each warrior on a line.
 */
public final class WriteData {
    private FileWriter fileWriter;
    private ArrayList<Warrior> characters;
    private int numberOfCharacters;

    public WriteData(final String fileName) {
        try {
            this.fileWriter = new FileWriter(fileName);
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFound:" + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException:" + e.getMessage());
        }
    }

    public void writeDataInFile(final GameState gameState) throws IOException {
        int i;
        characters = gameState.getCharacters();
        numberOfCharacters = gameState.getNumberOfCharacters();
        Warrior currentWarrior;

        for (i = 0; i < numberOfCharacters; i++) {
            currentWarrior = characters.get(i);

            if (!currentWarrior.isAlive()) {
                fileWriter.writeWord(currentWarrior.getType() + " dead");
            } else {
                String write = ""
                        + currentWarrior.getType() + " " + currentWarrior.getLevel() + " "
                        + currentWarrior.getXp() + " " + currentWarrior.getHp() + " "
                        + currentWarrior.getPosX() + " " + currentWarrior.getPosY();

                fileWriter.writeWord(write);
            }

            fileWriter.writeNewLine();
        }
    }

    public void closeStream() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

