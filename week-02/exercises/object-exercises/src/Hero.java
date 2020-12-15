public class Hero {
    // 1. Create a new method in the Hero class.
    // Name: toLine
    // Inputs: none
    // Output: String
    // Description: returns the Hero's name and powers as a single line of text.

    public String toLine(){

        return String.format("name: %s, powers: %s", name, powers);
    }

    private String name;

    private Power[] powers;

    public String getName() {
        return name;
    }

    public Power[] getPowers() {
        return powers;
    }

    public Hero(String name, Power[] powers) {
        this.name = name;
        this.powers = powers;
    }
}
