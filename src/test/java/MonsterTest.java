/**
 * @author Taylor
 * <br>
 * created: 7/14/2025
 * @since VERSION
 */
/**
 * MonsterTest.java - Test class for simulating monster battles
 * Instantiates all monster types, sets stats, and triggers combat
 */
public class MonsterTest {

    public static void main(String[] args) {
        // === Create Monsters ===
        ElectricRat er = new ElectricRat("Zappy");
        FireLizard fl = new FireLizard("Charblazer");
        FlowerDino fd = new FlowerDino("Bloomzilla");
        WeirdTurtle wt = new WeirdTurtle("Squibby");

        // === Set Stats ===
        er.setAttackPoints();
        er.setDefensePoints();
        fl.setAttackPoints();
        fl.setDefensePoints();
        fd.setAttackPoints();
        fd.setDefensePoints();
        wt.setAttackPoints();
        wt.setDefensePoints();

        // === Print Initial Stats ===
        System.out.println("=== Initial Monster Stats ===");
        System.out.println(er);
        System.out.println(fl);
        System.out.println(fd);
        System.out.println(wt);
        System.out.println();

        // === Simulated Attacks ===
        System.out.println("=== Monster Battles ===");
        er.attack(fl);
        System.out.println();

        fl.attack(fd);
        System.out.println();

        fd.attack(wt);
        System.out.println();

        wt.attack(er);
        System.out.println();

        // === Self-Attack for Confusion Check ===
        System.out.println("=== Self-Attack Test ===");
        fd.attack(fd);
        System.out.println();

        // === Force Fainting with High Damage ===
        System.out.println("=== Fainting Test ===");
        wt.setHealthPoints(5.0);           // Lower Squibby’s HP
        fl.attack(wt);                     // Likely to faint
        System.out.println();

        // === Phrase Check ===
        System.out.println("=== Phrase Repetition Check ===");
        System.out.println(er.getPhrase());
        System.out.println(fl.getPhrase());
        System.out.println(fd.getPhrase());
        System.out.println(wt.getPhrase());

        // === Type Change Check ===
        System.out.println("=== Type Setting Test ===");
        er.setType(Monster.ElementalType.WATER); // May conflict
        fd.setType(Monster.ElementalType.FIRE);  // May conflict
    }
}

