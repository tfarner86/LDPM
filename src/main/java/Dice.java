/**
 * @author Taylor
 * <br>
 * created: 7/14/2025
 * @since VERSION
 */
/**
 * Dice.java - Utility class for simulating dice rolls
 * Used by Monster classes to determine randomized attack/defense values
 */
public class Dice {

    /**
     * Simulates rolling a random integer between min and max (inclusive)
     * @param min the minimum value to roll
     * @param max the maximum value to roll
     * @return a random number between min and max
     */
    public static int roll(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}

