package ability;

import warriors.Knight;
import warriors.Pyromancer;
import warriors.Rogue;
import warriors.Warrior;
import warriors.Wizard;

/**
 * This class implements the Ability interface.
 * It implements the specific methods for the Paralysis game
 * abilities and sets the specific variables in the constructor.
 */
public final class Paralysis implements Ability {
    private final Rogue invoker;
    private final int baseDamage = 40;
    private final int bonusLevel = 10;
    private final float bonusTerrain = 1.15f;
    private final int roundsOnInhanced = 6;
    private final int roundsNotInhanced = 3;

    public Paralysis(final Rogue invoker) {
        this.invoker = invoker;
    }

    public void fight(final float modifier, final Warrior enemy) {
        float damage = baseDamage + this.invoker.getLevel() * bonusLevel;

        if (this.invoker.isEnhanceAbilities()) {
            damage *= bonusTerrain;
            damage = Math.round(damage);
        }

        damage *= (1 + modifier);
        damage = Math.round(damage);

        if (this.invoker.isEnhanceAbilities()) {
            enemy.setRoundsRemained(roundsOnInhanced);
        } else {
            enemy.setRoundsRemained(roundsNotInhanced);
        }

        enemy.setSuferedShortDamage(Math.round(damage));
        enemy.setSuferedLongDamage(Math.round(damage));
        enemy.setCanMove(false);
    }

    @Override
    public void visit(final Warrior player) {
        player.accept(this);
    }

    @Override
    public void visit(final Rogue player) {
        final float modifier = -0.10f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Knight player) {
        final float modifier = -0.20f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Pyromancer player) {
        final float modifier = 0.20f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Wizard player) {
        final float modifier = 0.25f;
        fight(modifier, player);
    }
}

