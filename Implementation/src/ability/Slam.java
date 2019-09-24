package ability;

import warriors.Knight;
import warriors.Pyromancer;
import warriors.Rogue;
import warriors.Warrior;
import warriors.Wizard;

/**
 * This class implements the Ability interface.
 * It implements the specific methods for the Slam game
 * abilities and sets the specific variables in the constructor.
 */
public final class Slam implements Ability {
    private final Knight invoker;
    private final int baseDamage = 100;
    private final int bonusLevel = 40;
    private final float bonusTerrain = 1.15f;

    public Slam(final Knight invoker) {
        this.invoker = invoker;
    }

    public void fight(final float modifier, final Warrior enemy) {
        float damage = baseDamage + enemy.getLevel() * bonusLevel;

        if (this.invoker.isEnhanceAbilities()) {
            damage *= bonusTerrain;
            damage = Math.round(damage);
        }
        damage *= (1 + modifier);
        damage = Math.round(damage);

        enemy.setSuferedShortDamage(Math.round(damage));
        enemy.setSuferedLongDamage(0);

        enemy.setRoundsRemained(1);
        enemy.setCanMove(false);
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

