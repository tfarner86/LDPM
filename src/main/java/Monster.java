/**
 * @author Taylor
 * <br>
 * created: 7/14/2025
 * @since VERSION
 */
// Monster.java - Abstract base class for all monster types in the battle simulator
// Defines shared fields, behaviors, and combat mechanics for subclasses

import java.util.ArrayList;
import java.util.List;

public abstract class Monster {

    // Enum for elemental types
    protected enum ElementalType {
        ELECTRIC,
        FIRE,
        GRASS,
        WATER,
    }

    // === Fields ===
    private String name = "";                        // Monster's name
    private int defenseMin = 1;                      // Minimum defense roll
    private int defenseMax = 10;                     // Maximum defense roll
    private int attackMin = 1;                       // Minimum attack roll
    private int attackMax = 10;                      // Maximum attack roll
    private final double MAX_HP = 20.0;              // Constant max health
    private List<ElementalType> elements = new ArrayList<>(); // Element types
    private String phrase = "";                      // Phrase associated with type
    protected int attackPoints = 10;                 // Calculated attack value
    protected int defensePoints = 10;                // Calculated defense value
    private boolean fainted = false;                 // True if HP ≤ 0
    private Double healthPoints = MAX_HP;            // Current health

    // === Constructor ===
    // Sets monster name, adds ElementalType, and sets phrase
    public Monster(String name, ElementalType element) {
        this.name = name;
        this.elements.add(element);
        setPhrase(this); // Assign type-specific phrase
    }

    // === Abstract Methods ===
    // Set attackPoints randomly (to be implemented by subclasses)
    public abstract void setAttackPoints();

    // Set defensePoints randomly (to be implemented by subclasses)
    public abstract void setDefensePoints();

    // Set defensePoints with a specific value
    public abstract void setDefensePoints(int value);

    // === Core Battle Method ===
    // Handles attack logic against another monster
    public double attack(Monster opponent) {
        if (this.fainted) {
            System.out.println(name + " isn't conscious… it can't attack.");
            return 0.0;
        }

        System.out.println(name + " is attacking " + opponent.getName());
        System.out.println(getPhrase());

        double attackValue = calculateAttackPoints(this, opponent.getElements());
        System.out.println(name + " attacking with " + attackValue + " attack points!");

        double damage = opponent.takeDamage(attackValue);

        if (this == opponent && damage > 0) {
            System.out.println(name + " hurt itself in the confusion.");
        }

        return damage;
    }

    // === Damage Handling ===
    // Calculates how much damage is taken, updates HP, and prints effects
    public double takeDamage(double attackValue) {
        int defenseVal = calculateDefensePoints(this);
        double damage = attackValue - defenseVal;

        if (damage > 0) {
            System.out.println(name + " is hit for " + damage + " damage!");
            healthPoints -= damage;
        } else if (damage == 0) {
            System.out.println(name + " is nearly hit!.");
        }

        if (damage < defenseVal / 2.0) {
            System.out.println(name + " shrugs off the puny attack.");
        }

        if (healthPoints <= 0) {
            System.out.println(name + " has faint-- passed out. It's passed out.");
            fainted = true;
        } else {
            System.out.println(name + " has " + healthPoints + "/" + MAX_HP + " HP remaining");
        }

        return damage;
    }

    // === Defense Logic ===
    // Rolls defense based on monster's range and applies situational modifiers
    private int calculateDefensePoints(Monster monster) {
        int roll = Dice.roll(monster.getDefenseMin(), monster.getDefenseMax());

        if (roll % 2 == 0 && roll < monster.getDefenseMax() / 2) {
            roll = (roll + 1) * 2;
            System.out.println(monster.getName() + " finds courage in the desperate situation");
        } else if (roll == monster.getDefenseMin()) {
            System.out.println(monster.getName() + " is clearly not paying attention.");
        }

        return roll;
    }

    // === Attack Logic ===
    // Rolls attack and applies elemental modifiers
    public double calculateAttackPoints(Monster attacker, List<ElementalType> enemyTypes) {
        double roll = Dice.roll(attacker.getAttackMin(), attacker.getAttackMax());
        System.out.println(attacker.getName() + " rolls a " + roll);

        double modifier = 1.0;
        for (ElementalType type : enemyTypes) {
            modifier *= attackModifier(type);
        }

        if (modifier >= 2.0) {
            System.out.println("It's su-- *cough* very effective!");
        }

        return roll * modifier;
    }

    // === Modifier Logic ===
    // Returns multiplier based on attacker and defender types
    private double attackModifier(ElementalType defending) {
        double modifier = 1.0;
        for (ElementalType attacker : elements) {
            switch (attacker) {
                case ELECTRIC:
                    modifier = (defending == WATER) ? 2.0 : (defending == GRASS || defending == ELECTRIC) ? 0.5 : 1.0;
                    break;
                case FIRE:
                    modifier = (defending == GRASS) ? 2.0 : (defending == FIRE) ? 0.5 : (defending == WATER) ? 0.5 : 1.0;
                    break;
                case GRASS:
                    modifier = (defending == WATER) ? 2.0 : (defending == GRASS || defending == FIRE) ? 0.5 : 1.0;
                    break;
                case WATER:
                    modifier = (defending == FIRE) ? 2.0 : (defending == WATER || defending == GRASS) ? 0.5 : 1.0;
                    break;
            }
        }
        return modifier;
    }

    // === Type Management ===
    // Adds elemental type with conflict detection
    public int setType(ElementalType type) {
        if (elements.contains(type)) {
            System.out.println(type + " already set!");
            return 1;
        }

        if (attackModifier(type) > 1.0) {
            System.out.println("Can't have conflicting types!");
            return -1;
        }

        elements.add(type);
        System.out.println(name + " now has " + type);
        return 0;
    }

    // === Phrase Assignment ===
    // Assigns phrase based on monster type
    private void setPhrase(Monster monster) {
        if (monster instanceof ElectricRat) {
            setPhrase("'Lectric!");
        } else if (monster instanceof FireLizard) {
            setPhrase("Deal with it.");
        } else if (monster instanceof FlowerDino) {
            setPhrase("Flowah!");
        } else if (monster instanceof WeirdTurtle) {
            setPhrase("'Urtle!");
        } else {
            setPhrase("No phrase for me!");
        }
    }

    // === toString Override ===
    // Shows HP and elements, or fainted message
    public String toString() {
        StringBuilder types = new StringBuilder();
        for (ElementalType type : elements) {
            types.append(type).append(", ");
        }

        String typeStr = types.toString().trim();
        if (typeStr.endsWith(",")) {
            typeStr = typeStr.substring(0, typeStr.length() - 1);
        }

        if (fainted) {
            return name + " has fainted.\nElemental type: [" + typeStr + "]";
        } else {
            return name + " has " + healthPoints + "/" + MAX_HP + "hp.\nElemental type: [" + typeStr + "]";
        }
    }

    // === Phrase Getter ===
    public String getPhrase() {
        return phrase + " " + phrase;
    }

    // === Setters/Getters ===
    public String getName() { return name; }
    public List<ElementalType> getElements() { return elements; }
    public Double getHealthPoints() { return healthPoints; }
    public int getAttackMax() { return attackMax; }
    public int getAttackMin() { return attackMin; }
    public int getAttackPoints() { return attackPoints; }
    public int getDefenseMax() { return defenseMax; }
    public int getDefenseMin() { return defenseMin; }
    public int getDefensePoints() { return defensePoints; }
    public boolean isFainted() { return fainted; }

    public void setAttackMax(int val) { attackMax = val; }
    public void setAttackMin(int val) { attackMin = val; }
    public void setAttackPoints(int val) { attackPoints = val; }
    public void setAttackPoints() { setAttackPoints(); } // Abstract call placeholder
    public void setDefenseMax(int val) { defenseMax = val; }
    public void setDefenseMin(int val) { defenseMin = val; }
    public void setDefensePoints(int val) { defensePoints = val; }
    public void setDefensePoints() { setDefensePoints(); } // Abstract call placeholder
    public void setFainted(boolean val) { fainted = val; }
    public void setHealthPoints(Double val) { healthPoints = val; }
    public void setPhrase(String val) { phrase = val; }
}
