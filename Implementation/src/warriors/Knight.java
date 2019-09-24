package warriors;

import ability.Ability;

/**
 * This class extends the Warrior class.
 * It implements the specific methods for the Knight game
 * character and sets the specific variables in the constructor.
 */
public final class Knight extends Warrior {
    private static final char TYPE = 'K';
    private final int baseDamageShortAbility = 200;
    private final int bonusLevelShortAbility = 30;
    private final int baseDamageLongAbility = 100;
    private final int bonusLevelLongtAbility = 40;
    private final float bonusTerrain = 1.15f;
    private final int hp = 900;
    private final int hpOnUpgrade = 80;

    public Knight(final int posX, final int posY) {
        super(posX, posY);
        this.setHp(hp);
        this.setLife(hp);
        this.setHpOnUpgrade(hpOnUpgrade);
    }

    public void getEnemyDamage(final Warrior enemy) {
        float execute = baseDamageShortAbility + this.getLevel() * bonusLevelShortAbility;
        float slam = baseDamageLongAbility + this.getLevel() * bonusLevelLongtAbility;

        if (this.isEnhanceAbilities()) {
            execute *= bonusTerrain;
            execute = Math.round(execute);
            slam *= bonusTerrain;
            slam = Math.round(slam);
        }

        this.setDamage((int) (execute + slam));
    }

    public void accept(final Ability ability) {
        ability.visit(this);
    }

    @Override
    public char getType() {
        return TYPE;
    }
}

