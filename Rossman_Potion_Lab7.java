/*
 * Potion Lab: Brewing Up Java Objects
 * -------------------------------------
 *
 * Complete the TODOs below to:
 * 1. Define a Potion class
 * 2. Give it attributes and methods
 * 3. Create Potion objects and test them in main()
 *
 * Save both this file and your Potion.java file before submitting.
* @author- Cameron Rossman
* @version- 11/3/2025
* @class- COSC-111
 */

public class Rossman_Potion_Lab7 {
    public static void main(String[] args) {
        // TODO 1: Create three Potion objects below.
        // Example:
        // Potion healing = new Potion("Healing Draught", "green", 2);
        Rossman_Potion_Template sparkle = new Rossman_Potion_Template("sparkle", "light yellow", 5);
        Rossman_Potion_Template strength = new Rossman_Potion_Template("strength", "green", 3);
        Rossman_Potion_Template destiny = new Rossman_Potion_Template("destiny", "blue", 6);

        // TODO 2: Call each potion's describe() method to print out their details.
        sparkle.describe();
        strength.describe();
        destiny.describe();

        // TODO 3: Stir one or more potions to increase their potency.
        sparkle.stir();
        strength.stir();
        sparkle.describe();
        strength.describe();

        // TODO 4: (Optional) Call brewSpell() to see if any potion is powerful enough!
        sparkle.brewSpell();
        strength.brewSpell();
        destiny.brewSpell();

    }
}
/* Terminal Output
Potion: sparkle (light yellow), Potency: 5
Potion: strength (green), Potency: 3
Potion: destiny (blue), Potency: 6
sparkle has been stirred! Its potency is now 6.
strength has been stirred! Its potency is now 4.
Potion: sparkle (light yellow), Potency: 6
Potion: strength (green), Potency: 4
sparkle sparkles with power!
strength bubbles quietly... needs more stirring
destiny sparkles with power!
 */