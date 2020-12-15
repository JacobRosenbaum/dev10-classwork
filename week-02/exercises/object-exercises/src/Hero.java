public class Hero {
    // 1. Create a new method in the Hero class.
    // Name: toLine
    // Inputs: none
    // Output: String
    // Description: returns the Hero's name and powers as a single line of text.

    private String name;
    private Power[] powers;

    public Hero(String name, Power[] powers) {
        this.name = name;
        this.powers = powers;
    }

    public String toLine() {
        Power[] powers = getPowers();
        String displayPowers = "";

        for (int i = 0; i < powers.length; i++) {
            displayPowers += (i == 0 ? "" : ", ") + powers[i].getName();
        }

        return String.format("name: %s, powers: %s", getName(), displayPowers);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Power[] getPowers() {

        return powers;
    }

    public void setPowers(Power[] powers) {
        this.powers = powers;
    }
}
