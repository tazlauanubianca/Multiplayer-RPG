package warriors;

import java.util.List;
import java.util.Arrays;
import ability.Ability;

/**
 * This class is an abstract class. It will be extended by classes which
 * implement the warriors that are used in the game. This class will have the
 * commune variables and methods of all the players.
 */
public abstract class Warrior {
    private int xp, hp, level, life;
    private int hpOnUpgrade;
    private int damage;
    private int suferedShortDamage;
    private int suferedLongDamage;
    private int roundsRemained;
    private boolean enhanceAbilities;
    private boolean alive;
    private boolean canMove;
    private int posX;
    private int posY;
    private List<Ability> abilities;
    
    private final int divisor = 50;
    private final int limit = 250;
    private final int constantMax1 = 200;
    private final int constantMax2 = 40;

    /**
     * This is the class constructor.
     * @param posX the position of the warrior on the Ox ax.
     * @param posY the position of the warrior on the Oy ax.
     */
    public Warrior(final int posX, final int posY) {
        this.posX = posX;
        this.posY = posY;
        this.suferedShortDamage = 0;
        this.suferedLongDamage = 0;
        this.roundsRemained = 0;
        this.alive = true;
        this.canMove = true;
        this.level = 0;
        this.xp = 0;
        this.enhanceAbilities = false;
    }

    /**
     * This method sets the warrior abilities.
     * @param playerAbilities the abilities the warrior can invoke
     */
    public void addAbilities(final Ability... playerAbilities) {
        this.abilities = Arrays.asList(playerAbilities);
    }

    /**
     * This method return the current healing points of the warrior.
     * @return current life of the warrior
     */
    public int getLife() {
        return life;
    }

    /**
     * This method sets the current healing points of the warrior.
     * @param life the number of healing points to be set for the warrior
     */
    public void setLife(final int life) {
        this.life = life;
    }

    /**
     * This method return the damage this warrior can cause to other warrior
     * without depending on the enemy type.
     * @return damage caused by the warrior
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * This methods sets the damage the warrior can cause to other warrior
     * without depending on the enemy type.
     * @param damage
     */
    public void setDamage(final int damage) {
        this.damage = damage;
    }

    /**
     * This method returns the number of healing points the warrior gets 
     * bonus for each level.
     * @return number of bonus healing points for a level
     */
    public int getHpOnUpgrade() {
        return hpOnUpgrade;
    }

    /**
     * This method sets the number of bonus healing points the warrior
     * has for each level.
     * @param hpOnUpgrade number of bonus healing points for a level
     */
    public void setHpOnUpgrade(final int hpOnUpgrade) {
        this.hpOnUpgrade = hpOnUpgrade;
    }

    /**
     * This method sets the experience points the warrior has. Initially 
     * it is 0;
     * @param xp number of experience points the warrior has
     */
    public void setXp(final int xp) {
        this.xp = xp;
    }

    /**
     * This method sets the number of healing points for the warrior.
     * @param hp number of healing points the warrior has
     */
    public void setHp(final int hp) {
        this.hp = hp;
    }

    /**
     * This method sets the level of the warrior. Initially it
     * is 0.
     * @param level current level of the warrior
     */
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * This method returns true if the warrior can move and false if he can't.
     * @return true if canMove is set on true and false if it isn't.
     */
    public boolean getCanMove() {
        return canMove;
    }

    /**
     * This method sets the variable canMove of the warrior which indicates if he
     * can move on another coordinates in the current round or not.
     * @param canMove is false if the warrior can't move and true if he can
     */
    public void setCanMove(final boolean canMove) {
        this.canMove = canMove;
    }

    /**
     * This method returns if the warrior is still alive in the game or has been killed.
     * @return true if the warrior is alive and false otherwise
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * This method sets it the warrior is alive in the game or if he isn't.
     * @param alive true to set the warrior alive and otherwise.
     */
    public void setAlive(final boolean alive) {
        this.alive = alive;
    }

    /**
     * This method returns the number of rounds the warrior has left for DoT.
     * @return the number of rounds the warrior has to endure DoT
     */
    public int getRoundsRemained() {
        return roundsRemained;
    }

    /**
     * This method returns the level the warrior has reached.
     * @return the current level of the warrior
     */
    public int getLevel() {
        return level;
    }

    /**
     * This method returns the number of experience points the warrior
     * has accumulated.
     * @return the current number of experience points the warrior has
     */
    public int getXp() {
        return xp;
    }

    /**
     * This method returns the current number of healing points the
     * warrior has left.
     * @return current number of healing points for the warrior
     */
    public int getHp() {
        return hp;
    }

    /**
     * This method sets the number of rounds the warrior has to suffer from
     * DoT.
     * @param roundsRemained for damage of the warrior to affect the warrior
     */
    public void setRoundsRemained(final int roundsRemained) {
        this.roundsRemained = roundsRemained;
    }

    /**
     * This method returns how much damage the warrior has to suffer from
     * a short term ability that was applied on him.
     * @return damage from a short term ability that was applied on the warrior
     */
    public float getSuferedShortDamage() {
        return suferedShortDamage;
    }

    /**
     * This method sets the damage the warrior has to suffer from a short term 
     * ability that was applied on him.
     * @param suferedShortDamage damage from a short term ability
     */
    public void setSuferedShortDamage(final float suferedShortDamage) {
        this.suferedShortDamage += suferedShortDamage;
    }

    /**
     * This method returns the damage the warrior has to suffer from a long term 
     * ability that was applied on him.
     * @return damage from a long term ability that was applied on the warrior
     */
    public float getSuferedLongDamage() {
        return suferedLongDamage;
    }

    /**
     * This method sets the damage the warrior has to suffer from a long term
     * ability that was applied on him.
     * @param suferedLongDamage damage from a long term ability that was applied 
     * on the warrior
     */
    public void setSuferedLongDamage(final int suferedLongDamage) {
        this.suferedLongDamage = suferedLongDamage;
    }

    /**
     * This is an abstract method that will be overridden in each class.
     * @return the type of the character
     */
    public abstract char getType();

    /**
     * This method returns the current position of the warrior on the Ox ax.
     * @return position on Ox ax.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * This method returns the current position of the warrior on the Oy ax.
     * @return position on Oy ax.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * This method returns true if the warrior has enhanced abilities depending
     * on the type of ground he is on.
     * @return if the warrior has terrain enhanced abilities.
     */
    public boolean isEnhanceAbilities() {
        return enhanceAbilities;
    }

    /**
     * This method sets if the current warrior has enhanced abilities from
     * the ground he plays on or not.
     * @param enhanceAbilities true if it has enhanced abilities and  false 
     * otherwise
     */
    public void setEnhanceAbilities(final boolean enhanceAbilities) {
        this.enhanceAbilities = enhanceAbilities;
    }

    /**
     * This method updates the current positions of the warrior on the map.
     * @param changeX the new position on the Ox ax
     * @param changeY the new position on the Oy ax
     */
    public void updatePosition(final int changeX, final int changeY) {
        this.posX = changeX;
        this.posY = changeY;
    }

    /**
     * This method is used to call each ability on the current enemy without 
     * knowing the type of the enemy.
     * @param enemy the current warrior to apply the damage on
     */
    public void fight(final Warrior enemy) {
        for (Ability ability : this.abilities) {
            ability.visit(enemy);
        }
    }

    /**
     * This method computes the new number of healing points
     * of the warrior after a fight and verifies if it is still alive
     * or not, changing the alive parameter to false if he isn't.
     */
    public void updateState() {
        this.hp -= this.suferedShortDamage;
        this.suferedShortDamage = 0;

        if (this.hp <= 0) {
            this.alive = false;
        }
    }

    /**
     * This method computes the new number of healing points the warrior has
     * after he was affected by DoT and verifies if it is still alive
     * or not, changing the alive parameter to false if he isn't.
     */
    public void updateStateAfterRound() {
        if (this.roundsRemained > 0) {
            this.roundsRemained--;
            this.hp -= this.suferedLongDamage;
        } else {
            this.suferedLongDamage = 0;
            this.setCanMove(true);
        }

        if (this.hp <= 0) {
            this.alive = false;
        }
    }

    /**
     * This method computes the new number of experience points the warrior
     * has if he won a battle and verifies if he got to a new level.
     * @param loser the enemy the current warrior has defeated 
     */
    public void checkLevelUp(final Warrior loser) {
        this.xp += Math.max(0, constantMax1 - (this.level - loser.getLevel())
                                                               * constantMax2);
        int newLevel = 0;

        if (this.xp >= limit) {
            newLevel = (this.xp - limit) / divisor + 1;
        }

        if (newLevel > this.level) {
            this.hp = this.life + newLevel * this.hpOnUpgrade;
            this.level = newLevel;
        }
    }

    /**
     * This method is an abstract method that will be overridden in every class
     * to compute the number of damage the warrior can cause to another warrior.
     * @param player the current warrior to compute the damage
     */
    public abstract void getEnemyDamage(Warrior player);

    /**
     * This method is an abstract method that will be overridden in every class
     * to call the given ability.
     * @param ability the current ability to be invoked
     */
    public abstract void accept(Ability ability);

}
