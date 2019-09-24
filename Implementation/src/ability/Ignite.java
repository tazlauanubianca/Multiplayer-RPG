package ability;

import warriors.Knight;
import warriors.Pyromancer;
import warriors.Rogue;
import warriors.Warrior;
import warriors.Wizard;

/**
 * This class implements the Ability interface.
 * It implements the specific methods for the Ignite game
 * abilities and sets the specific variables in the constructor.
 */
public final class Ignite implements Ability {
    private final Pyromancer invoker;
    private final int baseDamage = 150;
    private final int baseDoT = 50;
    private final int bonusLevelDoT = 30;
    private final int bonusLevel = 20;
    private final float bonusTerrain = 1.25f;

    public Ignite(final Pyromancer invoker) {
        this.invoker = invoker;
    }

    public void fight(final float modifier, final Warrior enemy) {
        float damage = baseDamage + this.invoker.getLevel() * bonusLevel;
        float dot = baseDoT + this.invoker.getLevel() * bonusLevelDoT;

        if (this.invoker.isEnhanceAbilities()) {
            damage *= bonusTerrain;
            dot *= bonusTerrain;

            damage = Math.round(damage);
            dot = Math.round(dot);
        }

        damage *= (1 + modifier);
        dot *= (1 + modifier);

        enemy.setSuferedShortDamage(Math.round(damage));
        enemy.setSuferedLongDamage(Math.round(dot));
        enemy.setRoundsRemained(2);
    }

    @Override
    public void visit(final Warrior player) {
        player.accept(this);
    }

    @Override
    public void visit(final Rogue player) {
        final float modifier = -0.20f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Knight player) {
        final float modifier = 0.20f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Pyromancer player) {
        final float modifier = -0.10f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Wizard player) {
        final float modifier = 0.05f;
        fight(modifier, player);
    }
}

