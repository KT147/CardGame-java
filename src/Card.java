
import java.util.ArrayList;
import java.util.List;

public record Card(Suit suit,
                   String faceField,
                   int rank) {
    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;

        public char getImage() {
            return (new char[]{9827, 9830, 9829, 9824})[this.ordinal()];
        }

    }

    @Override
    public String toString() {
        int index = faceField.equals("10") ? 2 : 1;
        String faceString = faceField.substring(0, index);
        return "%s%s(%d)".formatted(faceString, suit.getImage(), rank);
    }

    public static Card getNumericCard(Suit suit, int cardNumber) {

        if (cardNumber > 1 && cardNumber < 11) {
            return new Card(suit, String.valueOf(cardNumber), cardNumber - 2);
        }
        System.out.println("Invalid Numeric card");
        return null;
    }

    public static Card getFaceCard(Suit suit, char abbrev) {

        int charIndex = "JQKA".indexOf(abbrev);
        if (charIndex > -1) {
            return new Card(suit, "" + abbrev, charIndex + 9);
        }
        System.out.println("Invalid Face card");
        return null;
    }

    public static List<Card> getStandardDeck() {
        List<Card> deck = new ArrayList<>(52);
        for (Suit suit : Suit.values()) {
            for (int i = 2; i <= 10; i++) {
                deck.add(getNumericCard(suit, i));
            }
            for (char c : new char[]{'J', 'Q', 'K', 'A'}) {
                deck.add(getFaceCard(suit, c));
            }
        }
        return deck;
    }


    public static void printDeck(List<Card> deck, String description) {
        System.out.println("--------------");
        if (description != null) {
            System.out.println(description);
        }

        for (Card card : deck) {
            System.out.print(card + " ");
        }
        System.out.println();
    }

}
