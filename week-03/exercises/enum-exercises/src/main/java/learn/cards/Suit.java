package learn.cards;

public enum Suit {

    HEARTS ("Hearts"),
    DIAMONDS ("Diamonds"),
    CLUBS ("Clubs"),
    SPADES ("Spades");

    private final String lowerCase;

    Suit(String lowercase) {
        this.lowerCase = lowercase;
    }

    public String getLowerCase() {
        return lowerCase;
    }
}
