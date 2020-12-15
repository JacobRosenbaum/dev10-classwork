public class Musician {

    private String name;
    private int rating;

    public Musician() {

    }

    /**
     * @param name   The name of the musician.
     * @param rating A number representing how much a musician is loved relative to other musicians.
     */
    public Musician(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
//        setName("Frank Sinatra");
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

}

