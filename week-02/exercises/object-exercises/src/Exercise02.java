public class Exercise02 {

    public static void main(String[] args) {

        // 1. Add a getter for the rating field in Musician.

        Musician ocean = new Musician("Frank Ocean", 10);
        System.out.println(ocean.getName());
        // 2. Uncomment the line below and insure that it compiles and runs.
        System.out.println(ocean.getRating());

        Musician sinatra = new Musician("Frank Sinatra", 10);
        System.out.println(sinatra.getName());
        System.out.println(sinatra.getRating());

        Musician armstrong = new Musician("Louis Armstrong", 10);
        System.out.println(armstrong.getName());
        System.out.println(armstrong.getRating());


        // 3. Instantiate two musicians and assign them to new variables.
        // 4. Print each musicians' name and rating on a single line.
    }
}
