package map;

import java.util.HashMap;

/**
 * This class is used to determine if a character has enhanced terrain abilities
 * depending on his current position on the map and his race type.
 */
public final class InhanceAbility {
    private HashMap<Character, Character> inhanceAbilities = new HashMap<Character, Character>() {
        /**
         * SerialVersionUID = 1L
         */
        private static final long serialVersionUID = 1L;

        {
            put('L', 'K');
            put('V', 'P');
            put('D', 'W');
            put('W', 'R');
        }
    };

    public InhanceAbility() {
    }

    public boolean getInhanceAbility(final char land, final char type) {
        if (inhanceAbilities.get(land).equals(type)) {
            return true;
        }
        return false;
    }
}

