package map;

import java.util.ArrayList;
import java.util.HashMap;
import warriors.Warrior;

/**
 * This class contains the current state of the game: the map of the game
 * and a list with all the characters that are playing. It also contains
 * specific methods for the game to change the round, to activate the warrior
 * abilities and to make the warrior fight in the game.
 */
public final class GameState {
    private static final HashMap<Character, Integer[]> MOVES = new HashMap<Character, Integer[]>() {
        /**
         * SerialVersionUID = 1L
         */
        private static final long serialVersionUID = 1L;

        {
            put('R', new Integer[] {0, 1});
            put('R', new Integer[] {0, 1});
            put('L', new Integer[] {0, -1});
            put('U', new Integer[] {-1, 0});
            put('D', new Integer[] {1, 0});
            put('_', new Integer[] {0, 0});
        }
    };

    private int numberOfCharacters;
    private int numberOfRounds;
    private Map map;
    private ArrayList<Warrior> characters;
    private ArrayList<char[]> rounds;

    public GameState(final int numberOfCharacters, final int numberOfRounds,
                      final Map map, final ArrayList<Warrior> characters,
                      final ArrayList<char[]> rounds) {

        this.numberOfCharacters = numberOfCharacters;
        this.numberOfRounds = numberOfRounds;
        this.map = map;
        this.characters = characters;
        this.rounds = rounds;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public int getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public ArrayList<Warrior> getCharacters() {
        return characters;
    }

    public void nextRound(final int round) {
        char[] currentRound = rounds.get(round);
        Warrior currentWarrior;
        int i;

        for (i = 0; i < this.numberOfCharacters; i++) {

            currentWarrior = characters.get(i);
            currentWarrior.updateStateAfterRound();

            if (currentWarrior.isAlive() && currentWarrior.getCanMove()) {
                int changeX = currentWarrior.getPosX() + MOVES.get(currentRound[i])[0];
                int changeY = currentWarrior.getPosY() + MOVES.get(currentRound[i])[1];
                currentWarrior.updatePosition(changeX, changeY);
            }
        }
    }

    public void activateInhanceAbility() {
        int i;
        InhanceAbility inhanceAbility = new InhanceAbility();
        Warrior currentWarrior;

        for (i = 0; i < this.numberOfCharacters; i++) {

            currentWarrior = characters.get(i);

            if (currentWarrior.isAlive()) {
                boolean ability = inhanceAbility.getInhanceAbility(
                                              map.getCellType(currentWarrior.getPosX(),
                                                              currentWarrior.getPosY()),
                                                              currentWarrior.getType());

                currentWarrior.setEnhanceAbilities(ability);
            }
        }
    }

    public void updateGameState() {
        int i, j;

        for (i = 0; i < numberOfCharacters; i++) {
            Warrior currentWarrior = characters.get(i);

            for (j = i + 1; j < numberOfCharacters; j++) {
                Warrior currentEnemy = characters.get(j);

                if (currentWarrior.getPosX() == currentEnemy.getPosX()
                    && currentWarrior.getPosY() == currentEnemy.getPosY()
                    && currentWarrior.isAlive() && currentEnemy.isAlive()) {

                    currentWarrior.getEnemyDamage(currentEnemy);
                    currentEnemy.getEnemyDamage(currentWarrior);

                    currentWarrior.fight(currentEnemy);
                    currentEnemy.fight(currentWarrior);

                    currentWarrior.updateState();
                    currentEnemy.updateState();

                    if (currentWarrior.isAlive() && (!currentEnemy.isAlive())) {
                        currentWarrior.checkLevelUp(currentEnemy);
                    } else if ((!currentWarrior.isAlive()) && currentEnemy.isAlive()) {
                        currentEnemy.checkLevelUp(currentWarrior);
                    }
                }
            }
        }
    }
}

