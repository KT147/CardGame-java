import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Card> cards = startGame();

        List<Card> playerOneCards = getPlayerCards(cards, "Player One");
        List<Card> playerTwoCards = getPlayerCards(cards, "Player Two");

        Card cardOnTable = cards.remove(0);


        while (!playerOneCards.isEmpty() && !playerTwoCards.isEmpty()) {

            Card p1 = null;
            Card p2 = null;

            System.out.println("\nCard on table: " + cardOnTable);

            if (checkIfCardIsValid(playerOneCards, cardOnTable)) {

                p1 = playCard(playerOneCards, cardOnTable);

                if (p1 != null) {
                    cardOnTable = p1;
                }

            } else if (!cards.isEmpty()) {

                Card drawn = cards.remove(0);
                playerOneCards.add(drawn);

                System.out.println("P1 draws: " + drawn);
            }

            if (checkIfCardIsValid(playerTwoCards, cardOnTable)) {

                p2 = playCard(playerTwoCards, cardOnTable);

                if (p2 != null) {
                    cardOnTable = p2;
                }

            } else if (!cards.isEmpty()) {

                Card drawn = cards.remove(0);
                playerTwoCards.add(drawn);

                System.out.println("P2 draws: " + drawn);
            }

            System.out.println("P1 plays: " + p1);
            System.out.println("P2 plays: " + p2);

            if (p1 == null && p2 == null && cards.isEmpty()) {
                System.out.println("No one can play anymore!");
                break;
            }
        }


    }

    public static List<Card> startGame() {
        List<Card> cards = Card.getStandardDeck();
        Collections.shuffle(cards);
        Card.printDeck(cards, "First shuffle");
        return cards;
    }

    public static List<Card> getPlayerCards(List<Card> cards, String playerName) {
        List<Card> playerCards = new ArrayList<>(cards.subList(0, 7));
        cards.subList(0, 7).clear();
        Card.printDeck(playerCards, playerName);
        return playerCards;
    }

    public static boolean checkIfCardIsValid(List<Card> hand, Card cardOnTable) {
        for (Card card : hand) {
            if (card.suit().equals(cardOnTable.suit()) &&
                    card.rank() > cardOnTable.rank()) {
                return true;
            }
        }

        return false;
    }

    public static Card playCard(List<Card> hand, Card cardOnTable) {

        for (Card card : hand) {
            if (card.suit().equals(cardOnTable.suit()) &&
                    card.rank() > cardOnTable.rank()) {

                hand.remove(card);
                return card;
            }
        }

        return null;
    }
}
