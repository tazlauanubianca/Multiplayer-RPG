package warriors;

import ability.Ability;

/**
 * This class extends the Warrior class.
 * It implements the specific methods for the Rogue game
 * character and sets the specific variables in the constructor.
 */
public final class Rogue extends Warrior {
    private static final char TYPE = 'R';
    private int cicle;
    private final int hp = 600;
    private final int hpUpgrade = 40;
    private final int baseDamageShortAbility = 200;
    private final int bonusLevelShortAbility = 20;
    private final int baseDamageLongAbility = 40;
    private final int bonusLevelLongtAbility = 10;
    private final float bonusTerrain = 1.15f;
    private final float bonusCicle = 1.5f;
    private final int resetCicle = 3;

    public Rogue(final int posX, final int posY) {
        super(posX, posY);
        this.setHp(hp);
        this.setLife(hp);
        this.setHpOnUpgrade(hpUpgrade);

        this.cicle = 0;
    }

    public void getEnemyDamage(final Warrior enemy) {
        float backstab = baseDamageShortAbility + this.getLevel() * bonusLevelShortAbility;
        float paralysis = baseDamageLongAbility + this.getLevel() * bonusLevelLongtAbility;

        if (cicle % resetCicle == 0) {
            if (this.isEnhanceAbilities()) {
                backstab *= bonusCicle;
            }
        }

        if (this.isEnhanceAbilities()) {
            backstab *= bonusTerrain;
            backstab = Math.round(backstab);

            paralysis *= bonusTerrain;
            paralysis = Math.round(paralysis);
        }

        this.setDamage((int) (paralysis + backstab));
    }

    public int getCicle() {
        return cicle;
    }

    public void setCicle(final int cicle) {
        this.cicle = cicle;
    }

    public void accept(final Ability ability) {
        ability.visit(this);
    }

    @Override
    public char getType() {
        return TYPE;
    }
}

