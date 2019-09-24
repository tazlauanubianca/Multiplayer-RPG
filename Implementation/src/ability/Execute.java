package ability;

import warriors.Knight;
import warriors.Pyromancer;
import warriors.Rogue;
import warriors.Warrior;
import warriors.Wizard;

/**
 * This class implements the Ability interface.
 * It implements the specific methods for the Execute game
 * abilities and sets the specific variables in the constructor.
 */
public final class Execute implements Ability {
    private final Knight invoker;
    private final int baseProcent = 20;
    private final int baseDamage = 200;
    private final int bonusLevel = 30;
    private final float limit = 40;
    private final float divisor = 100f;
    private final float bonusTerrain = 1.15f;

    public Execute(final Knight invoker) {
        this.invoker = invoker;
    }

    public void fight(final float modifier, final Warrior enemy) {
        float procent = baseProcent + this.invoker.getLevel();
        float damage;

        if (procent > limit) {
            procent = limit;
        }

        if (procent * enemy.getLife() / divisor >= enemy.getHp()) {
            enemy.setAlive(false);
            return;
        }

        damage = baseDamage + this.invoker.getLevel() * bonusLevel;

        if (this.invoker.isEnhanceAbilities()) {
            damage *= bonusTerrain;
            damage = Math.round(damage);
        }

        damage *= (1 + modifier);
        enemy.setSuferedShortDamage(Math.round(damage));
    }

    @Override
    public void visit(final Warrior player) {
        player.accept(this);
    }

    @Override
    public void visit(final Rogue player) {
        final float modifier = 0.15f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Knight player) {
        final float modifier = 0.0f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Pyromancer player) {
        final float modifier = 0.10f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Wizard player) {
        final float modifier = -0.20f;
        fight(modifier, player);
    }
}

