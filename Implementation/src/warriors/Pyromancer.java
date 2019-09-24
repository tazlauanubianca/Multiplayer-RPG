package warriors;

import ability.Ability;

/**
 * This class extends the Warrior class.
 * It implements the specific methods for the Pyromancer game
 * character and sets the specific variables in the constructor.
 */
public final class Pyromancer extends Warrior {
    private static final char TYPE = 'P';
    private final int baseDamageShortAbility = 150;
    private final int bonusLevelShortAbility = 20;
    private final int baseDamageLongAbility = 350;
    private final int bonusLevelLongtAbility = 50;
    private final float bonusTerrain = 1.25f;
    private final int hp = 500;
    private final int hpOnUpgrade = 50;

    public Pyromancer(final int posX, final int posY) {
        super(posX, posY);
        this.setHp(hp);
        this.setLife(hp);
        this.setHpOnUpgrade(hpOnUpgrade);
    }

    public void getEnemyDamage(final Warrior enemy) {
        float ignite = baseDamageShortAbility + this.getLevel() * bonusLevelShortAbility;
        float fireblast = baseDamageLongAbility + this.getLevel() * bonusLevelLongtAbility;

        if (this.isEnhanceAbilities()) {
            fireblast *= bonusTerrain;
            fireblast = Math.round(fireblast);
            ignite *= bonusTerrain;
            ignite = Math.round(ignite);
        }

        this.setDamage((int) (ignite + fireblast));
    }

    public void accept(final Ability ability) {
        ability.visit(this);
    }

    @Override
    public char getType() {
        return TYPE;
    }
}

