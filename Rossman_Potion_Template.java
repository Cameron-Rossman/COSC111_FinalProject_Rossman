/*
 * Potion Class
 * ---------------
 * This class represents a magical potion in our Halloween Potion Lab.
 * Each potion has a name, color, and potency.
 *
 * TODOs:
 * - Fill in the attributes/instance variables, constructor, and methods as directed.
 * @author- Cameron Rossman
 * @version- 11/3/2025
 * @class- COSC-111
 */

public class Rossman_Potion_Template {

    // TODO 1: Declare three instance variables:
    // String name
    // String color
    // int potency
    String name;
    String color;
    int potency;

    // TODO 2: Create a constructor that accepts name, color, and potency
    // Use the "this" keyword to assign the parameters to the instance variables.
    public Rossman_Potion_Template(String name, String color, int potency) {
        this.name = name;
        this.color = color;
        this.potency = potency;
    }

    // TODO 3: Create a method called stir()
    // This method should increase the potion's potency by 1
    // and print a message such as:
    // "Healing Draught has been stirred! Its potency is now 3."
    public void stir() {
        potency++;
        System.out.println(name + " has been stirred! Its potency is now " + potency + ".");
    }

    // TODO 4: Create a method called describe()
    // This method should print something like:
    // "Potion: Healing Draught (green), Potency: 3"

    public void describe() {
        System.out.println("Potion: " + name + " (" + color + "), Potency: " + potency);
    }

    // TODO 5: Create a method called brewSpell()
    // If potency >= 5, print "<name> sparkles with power!"
    // Otherwise, print "<name> bubbles quietly... needs more stirring."
    public void brewSpell() {
        /// If the potion's potency is 5 or greater it sparkles with power. If it is
        /// less than 5, it bubbles quietly and needs more stirring
        if (potency >= 5) {
            System.out.println(name + " sparkles with power!");
        } else {
            System.out.println(name + " bubbles quietly... needs more stirring");
        }
    }
}
