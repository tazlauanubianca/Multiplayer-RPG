package ability;

import warriors.Knight;
import warriors.Pyromancer;
import warriors.Rogue;
import warriors.Warrior;
import warriors.Wizard;

/**
 * This class implements the Ability interface.
 * It implements the specific methods for the Drain game
 * abilities and sets the specific variables in the constructor.
 */
public final class Drain implements Ability {
    private final Wizard invoker;
    private final int baseDamage = 20;
    private final int bonusLevel = 5;
    private final float bonusTerrain = 1.1f;
    private final float divisor = 100f;
    private final float dividend = 30f;

    public Drain(final Wizard invoker) {
        this.invoker = invoker;
    }

    public void fight(final float modifier, final Warrior enemy) {
        float procent = baseDamage + this.invoker.getLevel() * bonusLevel;
        float min = (float) Math.min((dividend / divisor) * enemy.getLife(), enemy.getHp());
        float damage;

        if (this.invoker.isEnhanceAbilities()) {
            procent *= bonusTerrain;
        }

        procent *= (1 + modifier);
        damage = procent * min / divisor;
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

