package warriors;

import ability.Ability;

/**
 * This class extends the Warrior class.
 * It implements the specific methods for the Wizard game
 * character and sets the specific variables in the constructor.
 */
public final class Wizard extends Warrior {
    private static final char TYPE = 'W';
    private final int hp = 400;
    private final int hpUpgrade = 30;
    private final int baseDamage = 20;
    private final int bonusLevel = 5;
    private final float bonusTerrain = 1.1f;
    private final float divisor = 100f;
    private final float dividend = 30f;

    public Wizard(final int posX, final int posY) {
        super(posX, posY);
        this.setHp(hp);
        this.setLife(hp);
        this.setHpOnUpgrade(hpUpgrade);
    }

    public void getEnemyDamage(final Warrior enemy) {
        float procent = baseDamage + this.getLevel() * bonusLevel;
        float min = (float) Math.min(dividend / divisor * enemy.getLife(), enemy.getHp());
        float drain = procent * min / divisor;

        if (this.isEnhanceAbilities()) {
            drain *= bonusTerrain;
        }

        this.setDamage(Math.round(drain));
    }

    public void accept(final Ability ability) {
        ability.visit(this);
    }

    @Override
    public char getType() {
        return TYPE;
    }
}

