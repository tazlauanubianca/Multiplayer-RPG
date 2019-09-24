package main;

import map.GameState;
import map.Map;
import warriors.Rogue;
import warriors.Warrior;
import warriors.Knight;
import warriors.Pyromancer;
import warriors.Wizard;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import ability.Backstab;
import ability.Deflect;
import ability.Drain;
import ability.Execute;
import ability.Fireblast;
import ability.Ignite;
import ability.Paralysis;
import ability.Slam;
import fileio.implementations.FileReader;

/**
 * This method is used to read data from the specified file.
 */
public final class ReadData {
    private FileReader fileReader;
    private int rows;
    private int columns;
    private int numberOfCharacters;
    private int numberOfRounds;
    private Map map;
    private ArrayList<Warrior> characters;
    private ArrayList<char[]> rounds;
    private GameState gameState;

    public ReadData(final String fileName) {
        try {
            this.fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFound:" + e.getMessage());
        }
    }

    public void readDataFromFile() throws IOException {
        int i;
        String terrainTypes;

        this.rows = fileReader.nextInt();
        this.columns = fileReader.nextInt();

        this.map = new Map(this.rows, this.columns);

        for (i = 0; i < this.rows; i++) {
            terrainTypes = fileReader.nextWord();
            map.setLine(i, terrainTypes);
        }

        numberOfCharacters = fileReader.nextInt();
        this.characters = new ArrayList<Warrior>(numberOfCharacters);

        for (i = 0; i < numberOfCharacters; i++) {
            char type = fileReader.nextWord().toCharArray()[0];
            int posX = fileReader.nextInt();
            int posY = fileReader.nextInt();
            Warrior tmpWarrior = null;

            if (type == 'W') {
                Wizard w = new Wizard(posX, posY);
                w.addAbilities(new Drain(w), new Deflect(w));
                tmpWarrior = w;
            } else if (type == 'R') {
                Rogue w = new Rogue(posX, posY);
                w.addAbilities(new Backstab(w), new Paralysis(w));
                tmpWarrior = w;
            } else if (type == 'K') {
                Knight w = new Knight(posX, posY);
                w.addAbilities(new Execute(w), new Slam(w));
                tmpWarrior = w;
            } else if (type == 'P') {
                Pyromancer w = new Pyromancer(posX, posY);
                w.addAbilities(new Fireblast(w), new Ignite(w));
                tmpWarrior = w;
            }
            characters.add(tmpWarrior);
        }

        numberOfRounds = fileReader.nextInt();
        this.rounds = new ArrayList<char[]>(numberOfRounds);
        char[] roundArray;
        String round;

        for (i = 0; i < numberOfRounds; i++) {
            round = fileReader.nextWord();
            roundArray = round.toCharArray();
            rounds.add(roundArray);
        }

        fileReader.close();
    }

    public GameState getGameState() {
        gameState = new GameState(numberOfCharacters, numberOfRounds,
                                  map, characters, rounds);
        return gameState;
    }
}

