/**
 * @author Taylor
 * <br>
 * created: 7/14/2025
 * @since VERSION
 */
/**
 * ElectricRat.java - Concrete implementation of a Monster with ELECTRIC elemental type
 * Sets unique attack/defense ranges and implements stat roll methods
 */
public class ElectricRat extends Monster {

    // === Stat Boundaries Specific to ElectricRat ===
    private final int DEFENSE_MIN = 5;   // Minimum defense roll
    private final int DEFENSE_MAX = 8;   // Maximum defense roll
    private final int ATTACK_MIN = 5;    // Minimum attack roll
    private final int ATTACK_MAX = 8;    // Maximum attack roll

    /**
     * Constructor for ElectricRat
     * @param name the name of the monster
     * Passes name and ElementalType.ELECTRIC to the parent constructor
     * Sets specific attack and defense boundaries using inherited setters
     */
    public ElectricRat(String name) {
        super(name, ElementalType.ELECTRIC);
        setAttackMin(ATTACK_MIN);
        setAttackMax(ATTACK_MAX);
        setDefenseMin(DEFENSE_MIN);
        setDefenseMax(DEFENSE_MAX);
    }

    /**
     * Overrides abstract method from Monster to set attackPoints
     * Rolls random value using Dice within ATTACK_MIN to ATTACK_MAX
     */
    @Override
    public void setAttackPoints() {
        int roll = Dice.roll(ATTACK_MIN, ATTACK_MAX);
        setAttackPoints(roll);
    }

    /**
     * Overrides abstract method from Monster to set defensePoints
     * Rolls random value using Dice within DEFENSE_MIN to DEFENSE_MAX
     */
    @Override
    public void setDefensePoints() {
        int roll = Dice.roll(DEFENSE_MIN, DEFENSE_MAX);
        setDefensePoints(roll);
    }

    /**
     * Overrides abstract method from Monster to set defensePoints explicitly
     * @param value the value to set defensePoints to
     */
    @Override
    public void setDefensePoints(int value) {
        setDefensePoints(value);
    }
}

