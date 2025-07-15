/**
 * @author Taylor
 * <br>
 * created: 7/14/2025
 * @since VERSION
 */
/**
 * WeirdTurtle.java - WATER-type Monster implementation
 * Sets custom defense and attack bounds and overrides stat methods
 */
public class WeirdTurtle extends Monster {

    // === Stat boundaries specific to WeirdTurtle ===
    private final int DEFENSE_MIN = 3;
    private final int DEFENSE_MAX = 8;
    private final int ATTACK_MIN = 3;
    private final int ATTACK_MAX = 8;

    /**
     * Constructor for WeirdTurtle
     * @param name the monster’s nickname
     * Initializes with ElementalType.WATER and sets stat ranges
     */
    public WeirdTurtle(String name) {
        super(name, ElementalType.WATER);
        setAttackMin(ATTACK_MIN);
        setAttackMax(ATTACK_MAX);
        setDefenseMin(DEFENSE_MIN);
        setDefenseMax(DEFENSE_MAX);
    }

    /**
     * Randomizes attackPoints within turtle’s defined range
     */
    @Override
    public void setAttackPoints() {
        int roll = Dice.roll(ATTACK_MIN, ATTACK_MAX);
        setAttackPoints(roll);
    }

    /**
     * Randomizes defensePoints within turtle’s defined range
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

