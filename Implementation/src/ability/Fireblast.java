package ability;

import warriors.Rogue;
import warriors.Warrior;
import warriors.Knight;
import warriors.Pyromancer;
import warriors.Wizard;

/**
 * This class implements the Ability interface.
 * It implements the specific methods for the Fireblast game
 * abilities and sets the specific variables in the constructor.
 */
public final class Fireblast implements Ability {
    private final Pyromancer invoker;
    private final int baseDamage = 350;
    private final int bonusLevel = 50;
    private final float bonusTerrain = 1.25f;

    public Fireblast(final Pyromancer invoker) {
        this.invoker = invoker;
    }

    public void fight(final float modifier, final Warrior enemy) {
        float damage = baseDamage + this.invoker.getLevel() * bonusLevel;

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

