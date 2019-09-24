package ability;

import warriors.Rogue;
import warriors.Warrior;
import warriors.Knight;
import warriors.Pyromancer;
import warriors.Wizard;

/**
 * This class implements the Ability interface.
 * It implements the specific methods for the Deflect game
 * abilities and sets the specific variables in the constructor.
 */
public final class Deflect implements Ability {
    private Wizard invoker;
    private final int baseDamage = 35;
    private final int bonusLevel = 2;
    private final float bonusTerrain = 1.1f;
    private final int limit = 70;
    private final float divisor = 100f;

    public Deflect(final Wizard invoker) {
        this.invoker = invoker;
    }

    public void fight(final float modifier, final Warrior enemy) {
        float damageFromEnemy = enemy.getDamage();
        float procent = baseDamage + this.invoker.getLevel() * bonusLevel;
        float damage;

        if (procent > limit) {
            procent = limit;
        }

        damage = damageFromEnemy * procent / divisor;

        if (this.invoker.isEnhanceAbilities()) {
            damage *= bonusTerrain;
        }

        damage *= (1 + modifier);
        enemy.setSuferedShortDamage(Math.round(damage));
    }

    @Override
    public void visit(final Warrior player) {
        player.accept(this);
    }

    @Override
    public void visit(final Knight player) {
        final float modifier = 0.40f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Pyromancer player) {
        final float modifier = 0.30f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Rogue player) {
        final float modifier = 0.20f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Wizard player) {
        final float modifier = -1.0f;
        fight(modifier, player);
    }
}

