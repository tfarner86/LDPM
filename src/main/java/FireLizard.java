/**
 * @author Taylor
 * <br>
 * created: 7/14/2025
 * @since VERSION
 */
/**
 * FireLizard.java - FIRE-type Monster implementation
 * High attack range with low defense stats
 */
public class FireLizard extends Monster {

    // === FIRE-specific stat ranges ===
    private final int DEFENSE_MIN = 1;   // Fragile defense
    private final int DEFENSE_MAX = 8;
    private final int ATTACK_MIN = 8;    // Heavy-hitter
    private final int ATTACK_MAX = 16;

    /**
     * Constructor for FireLizard
     * @param name nickname of the monster
     * Initializes with ElementalType.FIRE and sets stat bounds
     */
    public FireLizard(String name) {
        super(name, ElementalType.FIRE);
        setAttackMin(ATTACK_MIN);
        setAttackMax(ATTACK_MAX);
        setDefenseMin(DEFENSE_MIN);
        setDefenseMax(DEFENSE_MAX);
    }

    /**
     * Rolls attackPoints within FIRE’s high attack range
     */
    @Override
    public void setAttackPoints() {
        int roll = Dice.roll(ATTACK_MIN, ATTACK_MAX);
        setAttackPoints(roll);
    }

    /**
     * Rolls defensePoints within FIRE’s low defense range
     */
    @Override
    public void setDefensePoints() {
        int roll = Dice.roll(DEFENSE_MIN, DEFENSE_MAX);
        setDefensePoints(roll);
    }

    /**
     * Sets defensePoints explicitly to a given value
     * @param value stat value to assign
     */
    @Override
    public void setDefensePoints(int value) {
        setDefensePoints(value);
    }
}

