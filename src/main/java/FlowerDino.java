/**
 * @author Taylor
 * <br>
 * created: 7/14/2025
 * @since VERSION
 */
/**
 * FlowerDino.java - GRASS-type Monster implementation
 * Sets custom stat bounds and overrides combat stat logic
 */
public class FlowerDino extends Monster {

    // === GRASS-specific stat ranges ===
    private final int DEFENSE_MIN = 4;   // GRASS type favors defense
    private final int DEFENSE_MAX = 8;
    private final int ATTACK_MIN = 3;    // Lower attack stats
    private final int ATTACK_MAX = 6;

    /**
     * Constructor for FlowerDino
     * @param name the name of the monster
     * Initializes with GRASS ElementalType and sets stat boundaries
     */
    public FlowerDino(String name) {
        super(name, ElementalType.GRASS);
        setAttackMin(ATTACK_MIN);
        setAttackMax(ATTACK_MAX);
        setDefenseMin(DEFENSE_MIN);
        setDefenseMax(DEFENSE_MAX);
    }

    /**
     * Rolls FlowerDino’s attack stat using GRASS range
     * Overrides Monster’s abstract method
     */
    @Override
    public void setAttackPoints() {
        int roll = Dice.roll(ATTACK_MIN, ATTACK_MAX);
        setAttackPoints(roll);
    }

    /**
     * Rolls FlowerDino’s defense stat using GRASS range
     * Overrides Monster’s abstract method
     */
    @Override
    public void setDefensePoints() {
        int roll = Dice.roll(DEFENSE_MIN, DEFENSE_MAX);
        super.setDefensePoints(roll);
    }

    @Override
    public void setDefensePoints(int value) {
        super.setDefensePoints(value);
    }

}

