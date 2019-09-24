package main;

import java.io.IOException;
import map.GameState;

/**
 * This class is the main class in which the game will be
 * played.
 */
public final class Main {
    private Main() {
    }

    public static void main(final String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];

        ReadData readData = new ReadData(inputFile);
        WriteData writeData = new WriteData(outputFile);

        try {
            readData.readDataFromFile();
        } catch (Exception e) {
            System.err.println("IOExceptio: " + e.getMessage());
        }

        GameState gameState = readData.getGameState();
        int numberOfRounds = gameState.getNumberOfRounds();

        for (int i = 0; i < numberOfRounds; i++) {
            gameState.nextRound(i);
            gameState.activateInhanceAbility();
            gameState.updateGameState();
        }

        try {
            writeData.writeDataInFile(gameState);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }

        writeData.closeStream();
    }
}

