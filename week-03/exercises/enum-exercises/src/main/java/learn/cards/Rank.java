package learn.cards;

public enum Rank {

    ACE (1, "Ace"),
    TWO (2, "Two"),
    THREE (3, "Three"),
    FOUR (4, "Four"),
    FIVE (5, "Five"),
    SIX (6, "Six"),
    SEVEN (7, "Seven"),
    EIGHT (8, "Eight"),
    NINE (9, "Nine"),
    TEN (10, "Ten"),
    JACK (11, "Jack"),
    QUEEN (12, "Queen"),
    KING (13, "King");

    private final int number;
    private final String lowerCase;

    Rank(int number, String lowerCase) {
        this.number = number;
        this.lowerCase = lowerCase;
    }

    public String getLowerCase() {
        return lowerCase;
    }

    public int getNumber() {
        return number;
    }

    public Rank findByNumber(int number) {
        for (Rank rank : Rank.values()) {
            if (rank.getNumber() == number) {
                return rank;
            }
        }
        String message = String.format("No Rank with number: %s.", number);
        throw new RuntimeException(message);
    }
}
