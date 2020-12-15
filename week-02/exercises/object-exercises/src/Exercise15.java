public class Exercise15 {

    // 1. Create a new method in the Hero class.
    // Name: toLine
    // Inputs: none
    // Output: String
    // Description: returns the Hero's name and powers as a single line of text.

    public static void main(String[] args) {
        Hero[] heroes = {
                new Hero("Hulk", new Power[]{new Power("Super Strength"), new Power("Big Muscles")}),
                new Hero("Spider Man", new Power[]{new Power("Sticky fingers"), new Power("Shoot webs")}),
                new Hero("SpongeBob", new Power[]{new Power("Delicious Krabby Patties"), new Power("Great attitude")}),
        };

        // 2. Instantiate your three favorite super heroes with appropriate powers.
        // 3. Use the `toLine` method to print each hero's details to the console.
        for (Hero hero : heroes) {
            System.out.println(hero.toLine());

        }
    }
}