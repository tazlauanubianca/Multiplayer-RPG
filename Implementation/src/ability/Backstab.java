package ability;

import warriors.Rogue;
import warriors.Warrior;
import warriors.Knight;
import warriors.Pyromancer;
import warriors.Wizard;

/**
 * This class implements the Ability interface.
 * It implements the specific methods for the Backstab game
 * abilities and sets the specific variables in the constructor.
 */
public final class Backstab implements Ability {
    private final Rogue invoker;
    private final int baseDamage = 200;
    private final int bonusLevel = 20;
    private final float bonusTerrain = 1.15f;
    private final float bonusCicle = 1.5f;
    private final int resetCicle = 3;

    public Backstab(final Rogue player) {
        this.invoker = player;
    }

    public void fight(final float modifier, final Warrior enemy) {
        int cicle = this.invoker.getCicle();
        this.invoker.setCicle((cicle + 1) % resetCicle);

        float damage = baseDamage + this.invoker.getLevel() * bonusLevel;

        if (cicle == 0) {
            if (this.invoker.isEnhanceAbilities()) {
                damage *= bonusCicle;
            }
        }

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
    public void visit(final Knight player) {
        final float modifier = -0.10f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Pyromancer player) {
        final float modifier = 0.25f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Rogue player) {
        final float modifier = 0.20f;
        fight(modifier, player);
    }

    @Override
    public void visit(final Wizard player) {
        final float modifier = 0.25f;
        fight(modifier, player);
    }
}
