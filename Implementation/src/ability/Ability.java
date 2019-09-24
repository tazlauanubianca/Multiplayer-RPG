package ability;

import warriors.Rogue;
import warriors.Warrior;
import warriors.Knight;
import warriors.Pyromancer;
import warriors.Wizard;

public interface Ability {
    void visit(Warrior player);

    void visit(Rogue player);

    void visit(Knight player);

    void visit(Pyromancer player);

    void visit(Wizard player);
}
