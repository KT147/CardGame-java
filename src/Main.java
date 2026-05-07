import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Card> cards = Card.getStandardDeck();
        Collections.shuffle(cards);
        Card.printDeck(cards, "First shuffle");

        List<Card> playerOneCards = new ArrayList<>(cards.subList(0, 7));
        cards.subList(0, 7).clear();
        List<Card> playerTwoCards = new ArrayList<>(cards.subList(0, 7));
        cards.subList(0, 7).clear();
        Card.printDeck(playerOneCards, "Player One");
        Card.printDeck(playerTwoCards, "Player Two");

        Card cardOnTable = cards.remove(0);
        System.out.println("Card on table: " + cardOnTable);

        evaluateHand(playerOneCards, cardOnTable);



    }

    public static Card evaluateHand(List<Card> hand, Card cardOnTable) {
        Card found = null;

        for (Card card : hand) {
            if (card.rank() > cardOnTable.rank() && card.suit().equals(cardOnTable.suit())) {
                found = card;
                break;
            }
        }
        if (found != null) {
            hand.remove(found);
            System.out.println("Card on table " + found);
        }
        return found;

    }
}
